package com.b2w.desafio.domain.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2w.desafio.domain.model.external.PlanetSwapi;
import com.b2w.desafio.domain.model.internal.Planet;
import com.b2w.desafio.domain.model.internal.PlanetRequest;
import com.b2w.desafio.domain.model.internal.PlanetResponse;
import com.b2w.desafio.domain.service.PlanetService;
import com.b2w.desafio.infrastructure.api.SwapiAPI;
import com.b2w.desafio.infrastructure.exception.RemovePLanetException;
import com.b2w.desafio.infrastructure.exception.SavedPlanetException;
import com.b2w.desafio.infrastructure.repository.PlanetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@NoArgsConstructor
@AllArgsConstructor
public class PlanetServiceImpl implements PlanetService {
	
    @Autowired
    private ObjectMapper mapper;

    @Autowired
	private SwapiAPI swapiAPI;
    
    @Autowired
    PlanetRepository planetRepository;
    
    @Override
	public List<PlanetResponse> findAll() {
    	List<Planet> planets = planetRepository.findAll();
    	if (CollectionUtils.isNotEmpty(planets)) {
    		List<PlanetResponse> planetResponses = new ArrayList<>(planets.size());
    		planets.stream().forEach(planet -> planetResponses.add(parsePlanetToPlanetResponse(planet)));
    		return planetResponses;
    	}
		return null;
	}
    
    @Override
	public PlanetResponse findByName(String planetName) {
    	Planet planetParse = planetRepository.findByNome(planetName);
    	return parsePlanetToPlanetResponse(planetParse);
	}

    @Override
  	public PlanetResponse save(PlanetRequest planet) {
    	
    	Planet planetParse = null;
    	try {
    		planetParse = planetRepository.insert(parsePlanetRequestToPlanet(planet));
		} catch (SavedPlanetException e) {
			log.error("Erro saved planet planet {} ", planet);
			throw new SavedPlanetException();
		}
      	return parsePlanetToPlanetResponse(planetParse);
  	}

    @Override
  	public void delete(String id) {
    	try {
    		planetRepository.deleteById(id);
		} catch (RemovePLanetException e) {
			log.error("Erro delete planet with id {} ", id);
			throw new RemovePLanetException();
		}
  	}

	@Override
	public PlanetResponse findById(String planetId) {
		Planet planetParse = planetRepository.findById(planetId);
		return parsePlanetToPlanetResponse(planetParse);
	}

	@Override
	public Integer findFilmeById(Long planetId) {
		PlanetSwapi qtdApp = swapiAPI.find(planetId);
		Integer qtd = null;
		if (!Objects.isNull(qtdApp) && CollectionUtils.isNotEmpty(qtdApp.getFilms())){
			qtd = qtdApp.getFilms().size();
		}
		return qtd;
	}
	
	private PlanetResponse parsePlanetToPlanetResponse(Planet planetParse) {
		PlanetResponse planet = null;
		try {
			planet =  mapper.readValue(mapper.writeValueAsString(planetParse), PlanetResponse.class);
		} catch (Exception e) {
			log.error("Erro paser Planet {} ", planetParse);
		}
		return planet;
	}

	private Planet parsePlanetRequestToPlanet(PlanetRequest planet){
		Planet planetResult = null; 
		try {
			planetResult = mapper.readValue(mapper.writeValueAsString(planet), Planet.class);
		} catch (Exception e) {
			log.error("Erro paser Planet {} ", planet);
		}
		return planetResult;
	}
}
