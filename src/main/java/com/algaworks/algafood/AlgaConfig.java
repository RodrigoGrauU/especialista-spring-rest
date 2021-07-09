package com.algaworks.algafood;

import com.algaworks.algafood.di.notificacao.NotificadorEmail;
//import com.algaworks.algafood.di.service.AtivacaoClienteService;

//@Configuration
public class AlgaConfig {

	//@Bean
	public NotificadorEmail notificadorEmail() {
		NotificadorEmail notificador = new NotificadorEmail("servidorSMTP@smtp.com");
		notificador.setCaixaAlta(false);

		return notificador;
	}

	
	 //@Bean 
		/*
		 * public AtivacaoClienteService ativacaoClienteService() { //passando como
		 * parâmetro o método (bean) anterior return new
		 * AtivacaoClienteService(notificadorEmail()); }
		 */
	
}
