package com.PolNebot.smartphone.backend.business.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PolNebot.smartphone.backend.business.model.Smartphones;
import com.PolNebot.smartphone.backend.business.services.SmartphonesServices;
import com.PolNebot.smartphone.backend.integration.repositores.SmartphonesRepository;


@Service
public class SmartphonesServicesImpl implements SmartphonesServices {

	//private final TreeMap<Long, Smartphones> SMARTPHONES = new TreeMap<>();
	
	@Autowired
	private SmartphonesRepository smartphonesRepository;
	
	@Override
	@Transactional
	public Long create(Smartphones smartphones) {
		
		if(smartphones.getId() != null) {
			throw new IllegalStateException("No se puede crear un producto con código not null");
		}
		
		Long id = System.currentTimeMillis();
		smartphones.setId(id);
		
		smartphonesRepository.save(smartphones);
		
		return id;
	}

	@Override
	public Optional<Smartphones> read(Long id) {	
		return smartphonesRepository.findById(id);
	}

	@Override
	@Transactional
	public void update(Smartphones smartphones) {
		
		Long id = smartphones.getId();
		
		if(id == null) {
			throw new IllegalStateException("No se puede actualizar un producto con código not null");
		}
		
		boolean existe = smartphonesRepository.existsById(id);
		
		if(!existe) {
			throw new IllegalStateException("El producto con código " + id + " no existe. No se puede actualizar.");
		}
		
		smartphonesRepository.save(smartphones);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		smartphonesRepository.deleteById(id);
	}

	@Override
	public List<Smartphones> getAll() {
		return smartphonesRepository.findAll();
	}

	@Override
	public List<Smartphones> getBetweenPriceRange(double min, double max) {
		return smartphonesRepository.findByPrecioBetween(min, max);
	}

}
