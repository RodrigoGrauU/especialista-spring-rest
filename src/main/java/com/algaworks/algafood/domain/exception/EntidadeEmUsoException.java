package com.algaworks.algafood.domain.exception;

public class EntidadeEmUsoException extends RuntimeException {

	private static final long serialVersionUID = 5306202639130417546L;

	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);
	}
}
