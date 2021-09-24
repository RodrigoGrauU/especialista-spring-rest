package com.algaworks.algafood.domain.exception;

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
