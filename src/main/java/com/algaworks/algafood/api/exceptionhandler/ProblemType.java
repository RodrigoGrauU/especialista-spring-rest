package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"), 
	ERRO_NEGOCIO("/erro-negocio", "Violação da regra de negócio"), 
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso");
	
	private String path;
	private String title;
	
	private ProblemType(String path, String title) {
		this.path = "https://algafood.com.br" + path;
		this.title = title;
	}
}
