package com.PolNebot.smartphone.backend.business.services;

import java.util.List;
import java.util.Optional;

import com.PolNebot.smartphone.backend.business.model.Smartphones;


public interface SmartphonesServices {

	/**
	 * Devuelve el código que ha otorgado el sistema
	 * 
	 * Lanza una IllegalStateException si el código del producto no es null
	 * 
	 */
	Long create(Smartphones samartphones);	    // C
	
	Optional<Smartphones> read(Long id);   // R
	
	/**
	 * 
	 * Lanza una IllegalStateException si el código del producto es null o no existe en el sistema
	 * 
	 */
	void update(Smartphones samartphones);		// U
	
	/**
	 * Lanza una IllegalStateException si no existe la id en el sistema
	 * 
	 */
	void delete(Long id);				// D
	
	List<Smartphones> getAll();
	List<Smartphones> getBetweenPriceRange(double min, double max);
	
}
