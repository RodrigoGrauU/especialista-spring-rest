package com.algaworks.algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "A entidade restaurante de id %d não existe";
	
	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public RestauranteNaoEncontradoException(Long id) {
		super(String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, id));
	}
	
}
