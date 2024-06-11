package com.PolNebot.smartphone.backend.business.services;

import java.util.List;
import java.util.Optional;

import com.PolNebot.smartphone.backend.business.model.Smartphone;


public interface SmartphoneServices {

	/**
	 * Devuelve el código que ha otorgado el sistema
	 * 
	 * Lanza una IllegalStateException si el código del producto no es null
	 * 
	 */
	Long create(Smartphone samartphone);	    // C
	
	Optional<Smartphone> read(Long id);   // R
	
	/**
	 * 
	 * Lanza una IllegalStateException si el código del producto es null o no existe en el sistema
	 * 
	 */
	
	void update(Smartphone samartphone);		// U
	
	/**
	 * Lanza una IllegalStateException si no existe la id en el sistema
	 * 
	 */
	void delete(Long id);				// D
	
	List<Smartphone> getAll();
	List<Smartphone> getBetweenPriceRange(double min, double max);
	
}
