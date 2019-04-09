package com.b2w.desafio.domain.service;

import java.util.List;

import com.b2w.desafio.domain.model.internal.PlanetRequest;
import com.b2w.desafio.domain.model.internal.PlanetResponse;

public interface PlanetService {

	List<PlanetResponse> findAll();

	void delete(String id);

	PlanetResponse findByName(String planetName);

	PlanetResponse save(PlanetRequest planet);

	PlanetResponse findById(String planetId);

	Integer findFilmeById(Long planetId);

}
