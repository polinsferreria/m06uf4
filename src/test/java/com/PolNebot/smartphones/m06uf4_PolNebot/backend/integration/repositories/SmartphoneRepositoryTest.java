package com.PolNebot.smartphones.m06uf4_PolNebot.backend.integration.repositories;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import com.PolNebot.smartphone.backend.M06uf4PolNebotApplication;
import com.PolNebot.smartphone.backend.business.model.Familia;
import com.PolNebot.smartphone.backend.business.model.Smartphone;
import com.PolNebot.smartphone.backend.business.model.dtos.Smartphone1DTO;
import com.PolNebot.smartphone.backend.integration.repositores.SmartphoneRepository;

@DataJpaTest
@Sql(scripts= {"/data/h2/schema_test.sql","/data/h2/data_test.sql"})
public class SmartphoneRepositoryTest {

	@Autowired
	private SmartphoneRepository smartphoneRepository;
	
	private Smartphone smartphone1;
	private Smartphone smartphone2;
	private Smartphone smartphone3;
	private Smartphone smartphone4;
	
	@BeforeEach
	void init() {
		initObjects();
	}
	
	@Test
	void obtenemos_smartphone_entre_rango_de_precios_en_orden_ascendente() {
		
		List<Smartphone> smartphone = smartphoneRepository.findByPrecioBetween(20.0, 500.0);
		
		assertEquals(2, smartphone.size());
			
		assertTrue(smartphone4.equals(smartphone.get(0)));
		assertTrue(smartphone1.equals(smartphone.get(1)));
		
	}
	
	@Test
	void obtenermos_smartphone_descatalogados_por_familia() {
		
		List<Smartphone> smartphone = smartphoneRepository.buscarPorDescatalogadosByFamilia(Familia.GAMA_ALTA);
		
		assertEquals(1, smartphone.size());
		
		assertTrue(smartphone2.equals(smartphone.get(0)));
	}
	
	@Test
	void obtenemos_todos_los_Smartphone1DTO() {
		
		List<Smartphone1DTO> smartphones1DTO = smartphoneRepository.getAllSmartphone1DTO();
		
		for(Smartphone1DTO smartphone1DTO: smartphones1DTO) {
			System.err.println(smartphone1DTO);
		}
	}
	
	// **************************************************************
	//
	// Private Methods
	//
	// **************************************************************
	
	private void initObjects() {
		
		smartphone1 = new Smartphone("Samsung Galaxy S22 Ultra", "Samsung", 850.5, 6.8, false);
		smartphone2 = new Smartphone("OnePlus 12 Pro", "OnePlus", 999.99, 6.9, false);
		smartphone3 = new Smartphone("Google Pixel 8", "Google", 758.5, 6.8, true);
		smartphone4 = new Smartphone("Google Pixel 6", "Google", 582.50, 6.7, true);
		
		smartphone1.setFamilia(Familia.GAMA_MEDIA);
		smartphone2.setFamilia(Familia.GAMA_ALTA);
		smartphone3.setFamilia(Familia.GAMA_ALTA);
		smartphone4.setFamilia(Familia.GAMA_BAJA);
		
	}
	
}
