package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		
		try {
			Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
			restaurante.setCozinha(cozinha);
			
			return restauranteRepository.salvar(restaurante);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeNaoEncontradaException(String.format(
					"Não foi encontrada entidade cozinha com id igual a %d",cozinhaId));
		}
		
	}
}
