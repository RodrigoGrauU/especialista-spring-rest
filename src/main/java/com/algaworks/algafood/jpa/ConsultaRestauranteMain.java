package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class ConsultaRestauranteMain {

	public static void main(String[] args) {
		/*
		 * Código necessário para iniciar a aplicação com o contexto não web
		 * */
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE) //informa para rodar a aplicação como não sendo web
				.run(args); //passa os argumentos do método main
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		List<Restaurante> todosRestaurantes = restauranteRepository.listar();
		
		for(Restaurante restaurante: todosRestaurantes) {
			System.out.printf("Id: %s - Nome: %s - Taxa do Frete: %f - Cozinha: %s\n", restaurante.getId(),
					restaurante.getNome(), restaurante.getTaxaFrete(), restaurante.getCozinha().getNome());
		}
	}
}
