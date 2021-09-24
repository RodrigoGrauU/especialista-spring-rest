package com.algaworks.algafood.domain.exception;

public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final String MSG_CIDADE_NAO_ENCONTRADA = "A entidade cidade de id %d não existe";
	
	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public CidadeNaoEncontradoException(Long id) {
		super(String.format(MSG_CIDADE_NAO_ENCONTRADA, id));
	}
	
}
