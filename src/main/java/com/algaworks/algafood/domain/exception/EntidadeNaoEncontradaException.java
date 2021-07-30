package com.algaworks.algafood.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 6726817923000169893L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
}
