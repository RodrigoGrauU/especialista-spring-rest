package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

//@Component
public class NotificadorEmail implements Notificador {
	
	//utilizando o ConfigurationProperties
	@Autowired
	private NotificadorProperties notificadorProperties;
	
	private Boolean caixaAlta;
	private String hostServidorSmtp;

	public NotificadorEmail(String hostServidorSmtp) {
		this.hostServidorSmtp = hostServidorSmtp;
		System.out.println("NotificadorEmail");
	}
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		//Verifica se o atributo de caixa alta é true. Caso seja, transforma para caixa alta o texto
		if (this.caixaAlta) {
			mensagem = mensagem.toUpperCase();
		}
		
		System.out.println("Host: " + notificadorProperties.getHostServidor());
		System.out.println("Porta: " + notificadorProperties.getPortaServidor());
		
		System.out.printf("Notificando %s através do e-mail %s usando SMTP %s: %s \n",
				cliente.getNome(), cliente.getEmail(), this.hostServidorSmtp, mensagem);
	}
	
	public void setCaixaAlta(boolean caixaAlta) {
		this.caixaAlta = caixaAlta;
	}

}