package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.infrastructure.repository.FormaPagamentoImpl;

public class CRUDFormaPagamentoMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		FormaPagamentoRepository formaPagamentoRepository = applicationContext.getBean(FormaPagamentoImpl.class);
		
		//listando formas de pagamento
		formaPagamentoRepository.listar().forEach(formaPagamento -> {
			System.out.println(formaPagamento.getDescricao());
		});
		
		//buscando forma de pagamento
		System.out.println("\nRealizando busca por forma de pagamento: " + formaPagamentoRepository.buscar(1L).getDescricao());
		
		//alterando forma de pagamento já existente
		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setId(1L);
		formaPagamento.setDescricao("Pix");
		formaPagamentoRepository.salvar(formaPagamento);
		
		//removendo forma de pagamento;
		formaPagamento.setId(2L);
		formaPagamentoRepository.remover(formaPagamento);
	}

}
