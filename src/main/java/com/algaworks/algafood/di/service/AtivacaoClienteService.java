package com.algaworks.algafood.di.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.NivelUrgencia;
import com.algaworks.algafood.di.notificacao.Notificador;
import com.algaworks.algafood.di.notificacao.TipoDoNotificador;


@Component
public class AtivacaoClienteService {
	
	//Ponto de injeção
	@TipoDoNotificador(NivelUrgencia.URGENTE)
	@Autowired(required = false)
	private Notificador notificador;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
//	@Autowired
//	private List<Notificador> notificadores;
	
	
	/*ponto de injeção
	 * public AtivacaoClienteService(Notificador notificador) { this.notificador =
	 * notificador; System.out.println("Ativacao cliente service: " + notificador);
	 * }
	 */
	

//	@PostConstruct
	public void init() {
		System.out.println("INIT " + notificador);
	}
	
//	@PreDestroy
	public void destroy() {
		System.out.println("DESTROY");
	}
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		
//		if(notificador != null) {
//			notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
//		} else {
//			System.out.println("Não existe notificador mas o cliente foi ativado");
//		}
		
		applicationEventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
		
	}


	
	//Ponto de injeção
	//public void setNotificador(Notificador notificador) {
	//	this.notificador = notificador;
	//}
}