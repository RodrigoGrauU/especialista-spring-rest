package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;

public class InclusaoCozinhaMain {

	public static void main(String[] args) {
		/*
		 * Código necessário para iniciar a aplicação com o contexto não web
		 * */
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE) //informa para rodar a aplicação como não sendo web
				.run(args); //passa os argumentos do método main
		
		CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
		
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Brasileira");
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setId(1L);
		cozinha2.setNome("Japonesa");
		
		cadastroCozinha.salvar(cozinha1);
		cadastroCozinha.salvar(cozinha2);
		
		
		List<Cozinha> cozinhas = cadastroCozinha.listar();
		
		for(Cozinha cozinha : cozinhas) {
			System.out.println(cozinha.getNome());
		}
		
		
	}
}
