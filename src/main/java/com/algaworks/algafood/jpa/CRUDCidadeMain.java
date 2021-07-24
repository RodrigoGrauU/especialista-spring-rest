package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.infrastructure.repository.CidadeRepositoryImpl;
import com.algaworks.algafood.infrastructure.repository.EstadoRepositoryImpl;

public class CRUDCidadeMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepositoryImpl.class);
		
		
		//adicionar
		Estado estado = applicationContext.getBean(EstadoRepositoryImpl.class).buscar(2L);
		Cidade novaCidade = new Cidade();
		novaCidade.setNome("Belém");
		novaCidade.setEstado(estado);
		cidadeRepository.salvar(novaCidade);
		
		
		//lendo cidades do banco
		cidadeRepository.listar().forEach(cidade -> System.out.println(cidade));
		
		//Alterando cidade
		Cidade cidade = cidadeRepository.buscar(1L);
		cidade.setNome("Bragança");
		cidadeRepository.salvar(cidade);
		
		//removendo estado
		cidade.setId(2L);
		cidadeRepository.remover(cidade);
	}
}
