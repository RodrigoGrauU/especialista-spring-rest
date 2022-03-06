package com.algaworks.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation("fotos")
@Getter
@Setter
public class FotoProdutoModel extends RepresentationModel<FotoProdutoModel> {
		
	private ProdutoModel produto;
	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;

}
