package com.b2w.desafio.domain.model.internal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "planet")
public class Planet {

	@Id
	private String id;
	@Indexed(unique = true)
	private String nome;
	private String clima;
	private String terreno;
}
