package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"), 
	ERRO_NEGOCIO("/erro-negocio", "Violação da regra de negócio"), 
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"), 
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema");
	
	private String path;
	private String title;
	
	private ProblemType(String path, String title) {
		this.path = "https://algafood.com.br" + path;
		this.title = title;
	}
}
