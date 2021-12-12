package com.algaworks.algafood.domain.exception;

public class FormaPagamentoNaoEncontradoException extends EntidadeNaoEncontradaException {

private static final String MSG_FORMA_PAGAMENTO_NAO_ENCONTRADO = "A entidade formaPagamento de id %d não existe";
	
	private static final long serialVersionUID = 1L;

	public FormaPagamentoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public FormaPagamentoNaoEncontradoException(Long formaPagamentoId) {
		super(String.format(MSG_FORMA_PAGAMENTO_NAO_ENCONTRADO, formaPagamentoId));
	}
}
