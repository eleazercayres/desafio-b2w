package com.b2w.desafio.domain.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.b2w.desafio.domain.model.internal.Planet;
import com.b2w.desafio.domain.model.internal.PlanetRequest;
import com.b2w.desafio.domain.model.internal.PlanetResponse;
import com.b2w.desafio.infrastructure.api.SwapiAPI;
import com.b2w.desafio.infrastructure.exception.RemovePLanetException;
import com.b2w.desafio.infrastructure.exception.SavedPlanetException;
import com.b2w.desafio.infrastructure.repository.PlanetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class PlanetServiceImplTest {

	@InjectMocks
	PlanetServiceImpl planetServiceImpl;

	@Mock
	private SwapiAPI swapiAPI;

	@Mock
	PlanetRepository planetRepository;

	@Test
	public void givenFindAllThenReturnListPlanet() {

		ReflectionTestUtils.setField(planetServiceImpl, "mapper", new ObjectMapper());

		List<Planet> planets = new ArrayList<>();
		planets.add(Planet.builder().clima("clima1").nome("nome1").terreno("terreno1").build());
		planets.add(Planet.builder().clima("clima2").nome("nome2").terreno("terreno2").build());

		when(planetRepository.findAll()).thenReturn(planets);

		List<PlanetResponse> result = planetServiceImpl.findAll();

		assertNotNull(result);
		assertEquals(result.get(0).getClima(), "clima1");
		assertEquals(result.get(1).getClima(), "clima2");
	}

	@Test
	public void givenFindAllThenReturnEmptyList() {

		ReflectionTestUtils.setField(planetServiceImpl, "mapper", new ObjectMapper());

		List<Planet> planets = new ArrayList<>();

		when(planetRepository.findAll()).thenReturn(planets);

		List<PlanetResponse> result = planetServiceImpl.findAll();

		assertNull(result);
	}

	@Test
	public void givenFindByNameThenReturnPlanet() {

		ReflectionTestUtils.setField(planetServiceImpl, "mapper", new ObjectMapper());

		when(planetRepository.findByNome("name"))
				.thenReturn(Planet.builder().clima("clima1").nome("nome1").terreno("terreno1").build());

		PlanetResponse result = planetServiceImpl.findByName("name");

		assertNotNull(result);
		assertEquals(result.getClima(), "clima1");
	}

	@Test
	public void givenFindByNameThenReturnNull() {

		ReflectionTestUtils.setField(planetServiceImpl, "mapper", new ObjectMapper());

		when(planetRepository.findByNome("name")).thenReturn(null);

		PlanetResponse result = planetServiceImpl.findByName("name");

		assertNull(result);
	}

	@Test
	public void givenSavePlanetThenReturnPlanetSaved() {

		ReflectionTestUtils.setField(planetServiceImpl, "mapper", new ObjectMapper());

		when(planetRepository.insert(Planet.builder().clima("clima1").nome("nome1").terreno("terreno1").build()))
				.thenReturn(Planet.builder().id("1").clima("clima1").nome("nome1").terreno("terreno1").build());

		PlanetResponse result = planetServiceImpl
				.save(PlanetRequest.builder().clima("clima1").nome("nome1").terreno("terreno1").build());

		assertNotNull(result);
		assertEquals(result.getClima(), "clima1");
	}

	@Test(expected = SavedPlanetException.class)
	public void givenSavePlanetThenReturnException() {

		ReflectionTestUtils.setField(planetServiceImpl, "mapper", new ObjectMapper());

		doThrow(SavedPlanetException.class).when(planetRepository).insert(Planet.builder().clima("clima1").nome("nome1").terreno("terreno1").build());

		planetServiceImpl.save(PlanetRequest.builder().clima("clima1").nome("nome1").terreno("terreno1").build());
	}
	
	@Test(expected = RemovePLanetException.class)
	public void givenDeletePlanetThenReturnException() {

		ReflectionTestUtils.setField(planetServiceImpl, "mapper", new ObjectMapper());

		doThrow(RemovePLanetException.class).when(planetRepository).deleteById("1");

		planetServiceImpl.delete("1");
	}
	
	@Test
	public void givenFindByIdThenReturnPlanet() {

		ReflectionTestUtils.setField(planetServiceImpl, "mapper", new ObjectMapper());

		when(planetRepository.findById("1")).thenReturn(Planet.builder().clima("clima1").nome("nome1").terreno("terreno1").build());

		PlanetResponse result = planetServiceImpl.findById("1");

		assertNotNull(result);
		assertEquals(result.getClima(), "clima1");
	}
	
	@Test
	public void givenFindByIdThenReturnNull() {

		ReflectionTestUtils.setField(planetServiceImpl, "mapper", new ObjectMapper());

		when(planetRepository.findOne(1l)).thenReturn(null);

		PlanetResponse result = planetServiceImpl.findById("1");

		assertNull(result);
	}
	
	@Test
	public void givenFindByNomeThenReturnPlanet() {

		ReflectionTestUtils.setField(planetServiceImpl, "mapper", new ObjectMapper());

		when(planetRepository.findByNome("name")).thenReturn(Planet.builder().clima("clima1").nome("nome1").terreno("terreno1").build());

		PlanetResponse result = planetServiceImpl.findByName("name");

		assertNotNull(result);
		assertEquals(result.getClima(), "clima1");
	}
	
	@Test
	public void givenFindByNomeThenReturnNull() {

		ReflectionTestUtils.setField(planetServiceImpl, "mapper", new ObjectMapper());

		when(planetRepository.findByNome("name")).thenReturn(null);

		PlanetResponse result = planetServiceImpl.findByName("name");

		assertNull(result);
	}
}
