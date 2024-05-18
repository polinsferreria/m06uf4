package com.PolNebot.smartphone.backend.integration.repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.PolNebot.smartphone.backend.business.model.Familia;
import com.PolNebot.smartphone.backend.business.model.Smartphones;

public interface SmartphonesRepository extends JpaRepository<Smartphones, Long> {

	List<Smartphones> findByPrecioBetween(double min, double max);

	@Query("SELECT s FROM Smartphones s WHERE s.descatalogado = true AND s.familia = :familia")
    List<Smartphones> buscarPorDescatalogadosByFamilia(Familia familia);
}