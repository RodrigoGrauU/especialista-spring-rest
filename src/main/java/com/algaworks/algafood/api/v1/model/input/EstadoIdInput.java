package com.algaworks.algafood.api.v1.model.input;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class EstadoIdInput {

	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long id;
}
