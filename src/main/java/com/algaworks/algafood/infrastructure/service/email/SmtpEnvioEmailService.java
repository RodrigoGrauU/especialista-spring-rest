package com.algaworks.algafood.infrastructure.service.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.core.emal.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;

@Service
public class SmtpEnvioEmailService implements EnvioEmailService {

	@Autowired
	private EmailProperties emailProperties;
	
	@Autowired
	private JavaMailSender mailSander;
	
	@Override
	public void Enviar(Mensagem mensagem) {
		try {
			MimeMessage mimeMessage = mailSander.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(emailProperties.getRemetente());
			helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
			helper.setSubject(mensagem.getAssunto());
			helper.setText(mensagem.getCorpo(), true);
			
			
			mailSander.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Não foi possível enviar email", e);
		}
		
	}

	
	


}
