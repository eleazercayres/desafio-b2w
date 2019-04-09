package com.b2w.desafio.domain.model.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanetResponse {

	private String id;
	private String nome;
	private String clima;
	private String terreno;
}
