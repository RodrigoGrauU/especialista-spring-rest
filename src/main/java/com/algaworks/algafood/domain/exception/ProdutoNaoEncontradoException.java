package com.algaworks.algafood.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	private static final String MSG_PRODUTO_NAO_ENCONTRADO = "Não existe produto com o id %d para o resturante com id %d";
	
	public ProdutoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public ProdutoNaoEncontradoException(Long restauranteId, Long produtoId) {
		this(String.format(MSG_PRODUTO_NAO_ENCONTRADO, produtoId, restauranteId));
	}
}
