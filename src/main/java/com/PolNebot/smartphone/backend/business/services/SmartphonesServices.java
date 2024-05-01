package com.PolNebot.smartphone.backend.business.services;

import java.util.List;
import java.util.Optional;

import com.PolNebot.smartphone.backend.business.model.Smartphones;


public interface SmartphonesServices {

	Long create(Smartphones smartphones);	    // C
	Optional<Smartphones> read(Long id);   // R
	
	List<Smartphones> getAll();
	
}
