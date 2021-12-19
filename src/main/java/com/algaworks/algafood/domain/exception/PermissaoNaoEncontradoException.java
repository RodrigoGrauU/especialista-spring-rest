package com.algaworks.algafood.domain.exception;

public class PermissaoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	private static final String MSG_PERMISSAO_NAO_ENCONTRADA = "Não foi encontrada a permissão com id %d";
	
	public PermissaoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public PermissaoNaoEncontradoException(Long permissaoId) {
		this(String.format(MSG_PERMISSAO_NAO_ENCONTRADA, permissaoId));
	}

}
