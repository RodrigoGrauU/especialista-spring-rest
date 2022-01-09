package com.algaworks.algafood.core.emal;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties(value = "algafood.email")
public class EmailProperties {

	// Atribuimos FAKE como padrão
	// Isso evita o problema de enviar e-mails de verdade caso você esqueça
	// de definir a propriedade
	private Implementacao impl = Implementacao.FAKE;
	
	@NotNull
	private String remetente;
	
	public enum Implementacao {
	    SMTP, FAKE
	}
}