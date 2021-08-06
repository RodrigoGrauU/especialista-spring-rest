package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format("Não foi encontrada entidade cozinha com id igual a %d",cozinhaId)));
		
		
		//Esse if a seguir é para caso a linha 27 seja: Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);
//		if (cozinha.isEmpty()) {
//			throw new EntidadeNaoEncontradaException(
//					String.format("Não foi encontrada entidade cozinha com id igual a %d",cozinhaId));
//		}
			
			restaurante.setCozinha(cozinha);
			
			return restauranteRepository.salvar(restaurante);
	}
}