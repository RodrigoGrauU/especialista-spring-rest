package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import com.algaworks.algafood.infrastructure.repository.PermissaoRepositoryImpl;

public class CRUDPermissaoMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		PermissaoRepository permissaoRepository = applicationContext.getBean(PermissaoRepositoryImpl.class);
		
		//listando permissões
		permissaoRepository.listar().forEach(permissao -> {
			System.out.println(permissao.getDescricao());
		});
		
		//buscando permissao
		System.out.println("\nPermissão que foi buscada: " + permissaoRepository.buscar(1L));
		
		//alterando forma de pagamento já existente
		Permissao permissao = permissaoRepository.buscar(1L);
		permissao.setNome("Subadmin");
		permissaoRepository.salvar(permissao);
		
		//removendo forma de pagamento;
		permissao.setId(2L);
		permissaoRepository.remover(permissao);
	}

}
