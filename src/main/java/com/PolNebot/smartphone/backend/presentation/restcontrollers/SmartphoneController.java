package com.PolNebot.smartphone.backend.presentation.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.PolNebot.smartphone.backend.presentation.config.RespuestaError;
import com.PolNebot.smartphone.backend.business.model.Smartphones;
import com.PolNebot.smartphone.backend.business.services.SmartphonesServices;

@RestController
public class SmartphoneController {

	@Autowired
	private SmartphonesServices SmartphoneServices;
	
	@GetMapping("/productos")
	public List<Smartphones> getAll(){
		return SmartphoneServices.getAll();
	}

	@GetMapping("/productos/{id}")
	public ResponseEntity<?> read(@PathVariable Long id) {
		
		if(id > 500) {
			throw new RuntimeException("El número " + id + " no es válido.");
		}
		
		Optional<Smartphones> optional = SmartphoneServices.read(id);
		
		if (optional.isEmpty()) {
			RespuestaError respuestaError = new RespuestaError("No se encuentra el producto con id " + id);
			return new ResponseEntity<>(respuestaError, HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(optional.get());
	}
	
	// ****************************************************
	//
	// Gestión de excepciones
	//
	// ****************************************************
	
	@ExceptionHandler({IllegalArgumentException.class, ClassCastException.class})
	public ResponseEntity<?> gestor1(Exception e){
		return ResponseEntity.badRequest().body(new RespuestaError(e.getMessage()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> gestor2(Exception e){
		return ResponseEntity.badRequest().body(new RespuestaError(e.getMessage()));
	}
	
}
