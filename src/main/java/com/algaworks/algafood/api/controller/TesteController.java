package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	CozinhaRepository cozinhaRepository;
	
	@Autowired
	RestauranteRepository restauranteRepository;
	
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhasPorNome(@RequestParam String nome) {
		return cozinhaRepository.findTodasByNomeContaining(nome);
	}
	
	@GetMapping("/cozinhas/unica-por-nome")
	public Optional<Cozinha> cozinhaPorNome(@RequestParam String nome) {
		return cozinhaRepository.findByNome(nome);
	}
	
	@GetMapping("/cozinhas/exists")
	public boolean cozinhaExists(String nome) {
		return cozinhaRepository.existsByNome(nome);
	}
	
	
//	@GetMapping("/restaurantes/por-taxa-frete")
//	public List<Restaurante> taxaFreteRestauranteEntre(BigDecimal taxaInicial, BigDecimal taxaFinal) {
//		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
//	}
	
	@GetMapping("/restaurantes/por-nome-id")
	public Optional<Restaurante> restaurantePorNomeECozinhaId(String nome, Long cozinhaId) {
		//return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
		return restauranteRepository.consultaPorNome(nome, cozinhaId);
	}
	
	@GetMapping("/restaurantes/primeiro-por-nome")
	public List<Restaurante> restaurantePrimeiroNome(String nome) {
		return restauranteRepository.findByNomeStartingWith(nome);
	}
	
	@GetMapping("/restaurantes/top2-por-nome")
	public List<Restaurante> restaurantesTop2PorNome(String nome) {
		return restauranteRepository.findTop2ByNomeContaining(nome);
	}
	
	@GetMapping("/restaurantes/count-por-cozinha")
	public int restaurantesCountPorCozinha(Long cozinhaId) {
		return restauranteRepository.countByCozinhaId(cozinhaId);
	}
	
	@GetMapping("/restaurantes/taxa-frete")
	public List<Restaurante> porDeterminadaTaxaFrete(BigDecimal taxa) {
		return restauranteRepository.restaurantePorTaxaFrete(taxa);
	}
	
	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> restaurantesPorTaxafrete(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.find(nome, taxaInicial, taxaFinal);
	}
	
	@GetMapping("restaurantes/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome) {
		return restauranteRepository.findComFreteGratis(nome);
	}
}
