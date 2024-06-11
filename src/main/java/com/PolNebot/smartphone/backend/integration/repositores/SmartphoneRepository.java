package com.PolNebot.smartphone.backend.integration.repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.PolNebot.smartphone.backend.business.model.Familia;
import com.PolNebot.smartphone.backend.business.model.Smartphone;
import com.PolNebot.smartphone.backend.business.model.dtos.Smartphone1DTO;

public interface SmartphoneRepository extends JpaRepository<Smartphone, Long> {

	List<Smartphone> findByPrecioBetween(double min, double max);

	@Query("SELECT s FROM Smartphone s WHERE s.descatalogado = true AND s.familia = :familia")
    List<Smartphone> buscarPorDescatalogadosByFamilia(Familia familia);
	
	@Query("SELECT new com.PolNebot.smartphone.backend.business.model.dtos.Smartphone1DTO"
			   + "(CONCAT(s.nombre, ' - ', "
			   + "        s.marca), "
			   + "        s.precio) "
			   + "FROM Smartphone s")
	List<Smartphone1DTO> getAllSmartphone1DTO();
	
}