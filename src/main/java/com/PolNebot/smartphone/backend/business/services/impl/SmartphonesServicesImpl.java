package com.PolNebot.smartphone.backend.business.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.PolNebot.smartphone.backend.business.model.Smartphones;
import com.PolNebot.smartphone.backend.business.services.SmartphonesServices;


@Service
public class SmartphonesServicesImpl implements SmartphonesServices {

	private final TreeMap<Long, Smartphones> SMARTPHONES = new TreeMap<>();
	
	public SmartphonesServicesImpl() {
		init();
	}
	
	@Override
	public Long create(Smartphones smartphones) {
		
		Long id = SMARTPHONES.lastKey() + 1;
		
		smartphones.setId(id);
		
		SMARTPHONES.put(smartphones.getId(), smartphones);
		
		return id;
	}

	@Override
	public Optional<Smartphones> read(Long id) {
		return Optional.ofNullable(SMARTPHONES.get(id));
	}

	@Override
	public List<Smartphones> getAll() {
		return new ArrayList<>(SMARTPHONES.values());
	}
	
	// ***************************************************************
	//
	// Private Methods
	//
	// ***************************************************************

	private void init() {
		
		Smartphones p1 = new Smartphones();
		Smartphones p2 = new Smartphones();
		Smartphones p3 = new Smartphones();
		
		p1.setId(10L);
		p1.setNombre("Samsung Galaxy S21 Ultra");
		p1.setMarca("Samsung");
		p1.setPrecio(780.50);
		p1.setTamañoPulgadas(6.8);
		
		p2.setId(11L);
		p2.setNombre("OnePlus 9 Pro");
		p2.setMarca("OnePlus");
		p2.setPrecio(700.0);
		p2.setTamañoPulgadas(6.7);
		
		p3.setId(12L);
		p3.setNombre("Google Pixel 6 Pro");
		p3.setMarca("Google");
		p3.setPrecio(390.0);
		p3.setTamañoPulgadas(6.7);
		
		SMARTPHONES.put(p1.getId(), p1);
		SMARTPHONES.put(p2.getId(), p2);
		SMARTPHONES.put(p3.getId(), p3);
		
	}
}
