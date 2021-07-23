package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.infrastructure.repository.EstadoRepositoryImpl;

public class CRUDEstado {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepositoryImpl.class);
		
		
		//adicionar
		Estado novoEstado = new Estado();
		novoEstado.setNome("São Paulo");
		estadoRepository.salvar(novoEstado);

		//lendo estados do banco
		estadoRepository.listar().forEach(estado -> System.out.println(estado));
		
		//Alterando estado
		Estado estado = estadoRepository.buscar(1L);
		estado.setNome("Roraima");
		estadoRepository.salvar(estado);
		
		//removendo estado
		estado = new Estado();
		estado.setId(4L);
		estadoRepository.remover(estado);
		
	}
}
