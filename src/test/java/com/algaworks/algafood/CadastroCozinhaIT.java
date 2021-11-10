package com.algaworks.algafood;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@ExtendWith(SpringExtension.class) //fornece suporte para carregar um contexto do spring para utilização do recurso do framework no teste
@SpringBootTest //fornece funcionalidade do spring boot nos testes
public class CadastroCozinhaIT {

	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Test
	public void testarCadastroCozinhaComSucesso() {
		//cenário
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");
		
		//ação
		novaCozinha = cadastroCozinha.salvar(novaCozinha);
		
		//validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}
	
	@Test
	public void testarCadastroCozinhaSemNome() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);
		
		ConstraintViolationException erroEsperado = Assertions.assertThrows(ConstraintViolationException.class, () -> {
			cadastroCozinha.salvar(novaCozinha);
		});
		
		assertThat(erroEsperado).isNotNull();
	}
	
	@Test
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Nova Cozinha");
		cozinha = cadastroCozinha.salvar(cozinha);
		
		Restaurante restaurante = new Restaurante();
		restaurante.setNome("Nome da cozinha");
		restaurante.setCozinha(cozinha);
		restaurante.setTaxaFrete(BigDecimal.valueOf(2.50));
		cadastroRestaurante.salvar(restaurante);
		
		Long idCozinha = cozinha.getId();
		Assertions.assertThrows(EntidadeEmUsoException.class, () -> cadastroCozinha.excluir(idCozinha));
	}
	
	@Test
	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
		Long idCozinhaInexistente = 1000L;
		
		EntidadeNaoEncontradaException cozinhaInexistenteErro = Assertions
				.assertThrows(EntidadeNaoEncontradaException.class, () -> cadastroCozinha.excluir(idCozinhaInexistente));
		
		assertThat(cozinhaInexistenteErro).isNotNull();
	}
}
