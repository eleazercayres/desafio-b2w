package com.b2w.desafio.domain.model.external;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanetSwapi {

	private String name;
	private String rotationPeriod;
	private String orbitalPeriod;
	private String diameter;
	private String climate;
	private String gravity;
	private String terrain;
	private String surfaceWater;
	private String population;
	private List<String> films;
	private String created;
	private String edited;
	private String url;
}
