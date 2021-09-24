package com.algaworks.algafood.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final String MSG_COZINHA_NAO_ENCONTRADA = "A entidade cozinha de id %d não existe";
	
	private static final long serialVersionUID = 1L;

	public CozinhaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CozinhaNaoEncontradaException(Long id) {
		super(String.format(MSG_COZINHA_NAO_ENCONTRADA, id));
	}
	
}
