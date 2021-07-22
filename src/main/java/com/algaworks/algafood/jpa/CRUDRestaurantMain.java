package com.algaworks.algafood.jpa;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.infrastructure.repository.RestauranteRepositoryImpl;

public class CRUDRestaurantMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepositoryImpl.class);
		
		//Listando restaurantes
		List<Restaurante> restaurantes = restauranteRepository.listar();
		detalheCadaRestaurante(restaurantes);
		
		//adicionando novo restaurante
		Restaurante restaurante = new Restaurante();
		restaurante.setNome("Restaurante da Família");
		restaurante.setTaxaFrete(BigDecimal.valueOf(3.0));
		restauranteRepository.salvar(restaurante);
		System.out.println("Novo restaurante adicionado. Lista atualizada: ");
		detalheCadaRestaurante(restauranteRepository.listar());
		
		//alterando restaurante existente
		restaurante = restauranteRepository.buscar(2L);
		restaurante.setNome("Comida Caseira 2.0 - Atualizada");
		restauranteRepository.salvar(restaurante);
		System.out.println("Um restaurante teve suas informações atualizadas:");
		detalheCadaRestaurante(restauranteRepository.listar());
		
		//removendo restaurante
		restaurante = restauranteRepository.buscar(1L);
		restauranteRepository.remover(restaurante);
		
		System.out.println("Um restaurante foi removido:");
		detalheCadaRestaurante(restauranteRepository.listar());
	}
	
	public static void detalheCadaRestaurante(List<Restaurante> restaurantes) {
		restaurantes.forEach(restaurante -> {
			System.out.println("Id: " + restaurante.getId());
			System.out.println("Nome: " + restaurante.getNome());
			System.out.println("Taxa do Frete: " + restaurante.getTaxaFrete());
			System.out.println("\n");
		});
	}

}
