package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

public class AlteracaoCozinhaMain {

	public static void main(String[] args) {
		/*
		 * Código necessário para iniciar a aplicação com o contexto não web
		 * */
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE) //informa para rodar a aplicação como não sendo web
				.run(args); //passa os argumentos do método main
		
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
		
		
		//irá alterar pois o id está sendo deffinido
		Cozinha cozinhaAlterada = new Cozinha();
		cozinhaAlterada.setId(1L);
		cozinhaAlterada.setNome("Japonesa");
		
		cozinhaRepository.salvar(cozinhaAlterada);
		
		
		List<Cozinha> cozinhas = cozinhaRepository.listar();
		
		for(Cozinha cozinha : cozinhas) {
			System.out.println(cozinha.getNome());
		}
		
	}
}
