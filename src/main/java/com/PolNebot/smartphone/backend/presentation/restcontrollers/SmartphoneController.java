package com.PolNebot.smartphone.backend.presentation.restcontrollers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.PolNebot.smartphone.backend.presentation.config.PresentationException;
import com.PolNebot.smartphone.backend.presentation.config.RespuestaError;
import com.PolNebot.smartphone.backend.business.model.Smartphone;
import com.PolNebot.smartphone.backend.business.services.SmartphoneServices;

@RestController
@RequestMapping("/smartphone")
public class SmartphoneController {

	@Autowired
	private SmartphoneServices smartphoneServices;
	
	@GetMapping
	public List<Smartphone> getAll(){
		return smartphoneServices.getAll();
	}

	@GetMapping("/{id}")
	public Smartphone /*ResponseEntity<?> */read(@PathVariable Long id) {
		
		Optional<Smartphone> optional = smartphoneServices.read(id);
		if(!optional.isPresent()) {
			throw new PresentationException("No se encuentra el smartphone con id " + id, HttpStatus.NOT_FOUND);
		}
		
		return optional.get();
		
	}
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Smartphone smartphone, UriComponentsBuilder ucb) {
		
		Long codigo = null;
		
		try {
			codigo = smartphoneServices.create(smartphone);
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		URI uri = ucb.path("/smartphone/{codigo}").build(codigo);
		
		return ResponseEntity.created(uri).build();
	}
		
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		
		try {
			smartphoneServices.delete(id);
		} catch(IllegalStateException e) {
			throw new PresentationException("No se encuentra el smartphone con id [" + id + "]. No se ha podido eliminar.", HttpStatus.NOT_FOUND);
		}
		
	}
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody Smartphone smartphone) {
		
		try {
			smartphoneServices.update(smartphone);
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
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
