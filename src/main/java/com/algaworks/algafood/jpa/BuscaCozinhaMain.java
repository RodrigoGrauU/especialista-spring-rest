package com.algaworks.algafood.jpa;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;

public class BuscaCozinhaMain {

	public static void main(String[] args) {
		/*
		 * Código necessário para iniciar a aplicação com o contexto não web
		 * */
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE) //informa para rodar a aplicação como não sendo web
				.run(args); //passa os argumentos do método main
		
		CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
		
		Cozinha cozinha = new Cozinha();
		cozinha = cadastroCozinha.buscar(2L);
		
		System.out.println(cozinha.getId());
		System.out.println(cozinha.getNome());
	}
}
