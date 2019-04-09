package com.b2w.desafio.application.controller;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.b2w.desafio.domain.model.internal.PlanetRequest;
import com.b2w.desafio.domain.model.internal.PlanetResponse;
import com.b2w.desafio.domain.service.PlanetService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("planet")
public class PlanetController {
	
    private PlanetService planetService;

    @GetMapping("/id/{planetId}")
	private ResponseEntity<PlanetResponse> findById(@PathVariable String planetId) {
		
        PlanetResponse result = planetService.findById(planetId);
        
		log.info("Busca realizada com sucesso findById. result: {}", result);
		
		return result != null ? ok(result) : notFound().build();
		
	}
    
    @GetMapping("/filme/{planetId}")
	private ResponseEntity<Integer> findFilmeById(@PathVariable Long planetId) {
		
        Integer qtdap = planetService.findFilmeById(planetId);
        
		log.info("Busca realizada com sucesso findFilmeById. result: {}", qtdap);
		
		return qtdap != null ? ok(qtdap) : notFound().build();
		
	}
    
    @GetMapping("/name/{planetName}")
	private ResponseEntity<PlanetResponse> findByName(@PathVariable String planetName) {
		
    	PlanetResponse result = planetService.findByName(planetName);
        
		log.info("Busca realizada com sucesso findByName. result: {}", result);
		
		return result != null ? ok(result) : notFound().build();
		
	}
    
    @GetMapping
	private ResponseEntity<List<PlanetResponse>> findAll() {
		
        List<PlanetResponse> result = planetService.findAll();
        
		log.info("Busca realizada com sucesso findAll. result: {}", result);
		
		return result != null ? ok(result) : notFound().build();
		
	}
	
	@PostMapping
	private ResponseEntity<PlanetResponse> save(@RequestBody PlanetRequest planetToSave) {
		
        PlanetResponse result = planetService.save(planetToSave);
        
		log.info("Planate save success. result: {}", result);
		
		return result != null ? ok(result) : notFound().build();
		
	}
	
	@DeleteMapping("{id}")
	private ResponseEntity<PlanetResponse> delete(@PathVariable String id) {
		
        planetService.delete(id);
        
		log.info("Planate deleted success. id: {}", id);
		
		return ResponseEntity.ok().build();
		
	}
}
