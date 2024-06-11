package com.PolNebot.smartphone.backend.business.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PolNebot.smartphone.backend.business.model.Smartphone;
import com.PolNebot.smartphone.backend.business.services.SmartphoneServices;
import com.PolNebot.smartphone.backend.integration.repositores.SmartphoneRepository;


@Service
public class SmartphoneServicesImpl implements SmartphoneServices {

	//private final TreeMap<Long, Smartphone> SMARTPHONE = new TreeMap<>();
	
	@Autowired
	private SmartphoneRepository smartphoneRepository;
	
	@Override
	@Transactional
	public Long create(Smartphone smartphone) {
		
		if(smartphone.getId() != null) {
			throw new IllegalStateException("No se puede crear un smartphone con ID null");
		}
		
		Long id = System.currentTimeMillis();
		smartphone.setId(id);
		
		smartphoneRepository.save(smartphone);
		
		return id;
	}

	@Override
	public Optional<Smartphone> read(Long id) {
		return smartphoneRepository.findById(id);
	}

	@Override
	@Transactional
	public void update(Smartphone smartphone) {
		
		Long id = smartphone.getId();
		
		if(id == null) {
			throw new IllegalStateException("No se puede actualizar un smartphone con ID null");
		}
		
		boolean existe = smartphoneRepository.existsById(id);
		
		if(!existe) {
			throw new IllegalStateException("El smartphone con ID " + id + " no existe. No se puede actualizar.");
		}
		
		smartphoneRepository.save(smartphone);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		smartphoneRepository.deleteById(id);
	}

	@Override
	public List<Smartphone> getAll() {
		return smartphoneRepository.findAll();
	}

	@Override
	public List<Smartphone> getBetweenPriceRange(double min, double max) {
		return smartphoneRepository.findByPrecioBetween(min, max);
	}

}
