package com.algaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final String MSG_ESTADO_NAO_ENCONTRADA = "A entidade estado de id %d não existe";
	
	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public EstadoNaoEncontradoException(Long idEstado) {
		super(String.format(MSG_ESTADO_NAO_ENCONTRADA, idEstado));
	}
	
}
