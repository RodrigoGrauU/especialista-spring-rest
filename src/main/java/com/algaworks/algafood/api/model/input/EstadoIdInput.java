package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class EstadoIdInput {

	@NotNull
	private Long id;
}
