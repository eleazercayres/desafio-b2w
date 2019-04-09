package com.b2w.desafio.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.b2w.desafio.domain.model.internal.Planet;

public interface PlanetRepository extends MongoRepository<Planet, Long> {

	public Planet findByNome(String name);
	public Planet findById(String id);
	public void deleteById(String id);
	
}
