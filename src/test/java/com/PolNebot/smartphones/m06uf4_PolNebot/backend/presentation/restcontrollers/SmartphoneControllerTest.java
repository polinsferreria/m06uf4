package com.PolNebot.smartphones.m06uf4_PolNebot.backend.presentation.restcontrollers;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.PolNebot.smartphone.backend.M06uf4PolNebotApplication;
import com.PolNebot.smartphone.backend.business.model.Familia;
import com.PolNebot.smartphone.backend.business.model.Smartphone;
import com.PolNebot.smartphone.backend.business.services.SmartphoneServices;
import com.PolNebot.smartphone.backend.presentation.config.RespuestaError;
import com.PolNebot.smartphone.backend.presentation.restcontrollers.SmartphoneController;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(controllers=SmartphoneController.class)
public class SmartphoneControllerTest {
	
	
		
		@Autowired
		private MockMvc miniPostman;
		
		@Autowired
		private ObjectMapper objectMapper;
		
		@MockBean
		private SmartphoneServices smartphoneServices;
		
		private Smartphone smartphone1;
		private Smartphone smartphone2;
		
		@BeforeEach
		void init() {
			initObjects();
		}
		
		@Test
		void pedimos_todos_los_smartphone() throws Exception {
			
			// Arrange
			
			List<Smartphone> smartphone = Arrays.asList(smartphone1, smartphone2);
			when(smartphoneServices.getAll()).thenReturn(smartphone);
			
			// Act
			
			MvcResult respuesta = miniPostman.perform(get("/smartphone").contentType("application/json"))
												.andExpect(status().isOk())
												.andReturn();
			
			String responseBody = respuesta.getResponse().getContentAsString();
			String respuestaEsperada = objectMapper.writeValueAsString(smartphone);
			
			// Assert
			
			assertThat(responseBody).isEqualToIgnoringWhitespace(respuestaEsperada);
			
		}
		
		@Test
		void pedimos_todos_los_smartphone_entre_rango_precios() throws Exception {
					
			List<Smartphone> smartphone = Arrays.asList(smartphone1, smartphone2);
			when(smartphoneServices.getBetweenPriceRange(10, 500)).thenReturn(smartphone);
				
			MvcResult respuesta = miniPostman.perform(get("/smartphone").param("min", "10")
																	   .param("max","500")
																	   .contentType("application/json"))
												.andExpect(status().isOk())
												.andReturn();
			
			String responseBody = respuesta.getResponse().getContentAsString();
			String respuestaEsperada = objectMapper.writeValueAsString(smartphone);
			
			assertThat(responseBody).isEqualToIgnoringWhitespace(respuestaEsperada);
			
		}
		
		@Test
		void obtenemos_smartphone_a_partir_de_su_id() throws Exception{
			
			when(smartphoneServices.read(100L)).thenReturn(Optional.of(smartphone1));
			
			MvcResult respuesta = miniPostman.perform(get("/smartphone/101").contentType("application/json"))
										.andExpect(status().isOk())
										.andReturn();
			
			String responseBody = respuesta.getResponse().getContentAsString();
			String respuestaEsperada = objectMapper.writeValueAsString(smartphone1);
			
			assertThat(responseBody).isEqualToIgnoringWhitespace(respuestaEsperada);
		
		}
		
		@Test
		void solicitamos_smartphone_a_partir_de_un_id_inexistente() throws Exception {
			
			when(smartphoneServices.read(100L)).thenReturn(Optional.empty());
			
			MvcResult respuesta = miniPostman.perform(get("/smartphone/101").contentType("application/json"))
										.andExpect(status().isNotFound())
										.andReturn();
			
			String responseBody = respuesta.getResponse().getContentAsString();
			String respuestaEsperada = objectMapper.writeValueAsString(new RespuestaError("No se encuentra el smartphone con id 101"));
			
			assertThat(responseBody).isEqualToIgnoringWhitespace(respuestaEsperada);
		}
		
		@Test
		void crea_smartphone_ok() throws Exception {
			
			smartphone1.setId(null);
			
			when(smartphoneServices.create(smartphone1)).thenReturn(1033L);
			
			String requestBody = objectMapper.writeValueAsString(smartphone1);
			
			miniPostman.perform(post("/smartphone").content(requestBody).contentType("application/json"))
							.andExpect(status().isCreated())
							.andExpect(header().string("Location","http://localhost/smartphone/1033"));
		}
		
		@Test
		void crear_smartphone_con_id_NO_NULL() throws Exception{
			
			when(smartphoneServices.create(smartphone1)).thenThrow(new IllegalStateException("Problema con el id..."));
			
			String requestBody = objectMapper.writeValueAsString(smartphone1);
			
			MvcResult respuesta = miniPostman.perform(post("/smartphone").content(requestBody).contentType("application/json"))
							.andExpect(status().isBadRequest())
							.andReturn();
			
			String responseBody = respuesta.getResponse().getContentAsString();
			String respuestaEsperada = objectMapper.writeValueAsString(new RespuestaError("Problema con el id..."));
			
			assertThat(responseBody).isEqualToIgnoringWhitespace(respuestaEsperada);
		}
		
		@Test
		void eliminamos_smartphone_ok() throws Exception{
			
			miniPostman.perform(delete("/smartphone/789")).andExpect(status().isNoContent());
			
			verify(smartphoneServices, times(1)).delete(789L);
		}
		
		@Test
		void eliminamos_smartphone_no_existente() throws Exception{
			
			Mockito.doThrow(new IllegalStateException("xxxx")).when(smartphoneServices).delete(789L);
			
			MvcResult respuesta = miniPostman.perform(delete("/smartphone/789"))
									.andExpect(status().isNotFound())
									.andReturn();
			
			String responseBody = respuesta.getResponse().getContentAsString();
			String respuestaEsperada = objectMapper.writeValueAsString(new RespuestaError("No se encuentra el smartphone con id [789]. No se ha podido eliminar."));
			
			assertThat(responseBody).isEqualToIgnoringWhitespace(respuestaEsperada);
			
		}
		
		// **************************************************************
		//
		// Private Methods
		//
		// **************************************************************
		
		private void initObjects() {
			
			smartphone1 = new Smartphone("Samsung Galaxy S10", "Samsung", 560.5, 6.8, true);
			smartphone2 = new Smartphone("OnePlus 8", "OnePlus", 300.5, 6.9, false);
			
			/*producto1 = new Smartphone();
			producto1.setId(106L);
			producto1.setNombre("Alfombrilla Mouse CR7");
			producto1.setDescatalogado(true);
			producto1.setPrecio(20.0);
			producto1.setFamilia(Familia.GAMA_ALTA);
			
			producto2 = new Smartphone();
			producto2.setId(107L);
			producto2.setNombre("Ordenador Epson D30");
			producto2.setDescatalogado(false);
			producto2.setPrecio(400.0);
			producto2.setFamilia(Familia.GAMA_BAJA);*/
			
		}
		
	
}
