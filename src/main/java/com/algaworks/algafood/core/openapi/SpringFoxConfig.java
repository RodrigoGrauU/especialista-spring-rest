package com.algaworks.algafood.core.openapi;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select() //selecionando endpoints a serem expostos
					.apis(RequestHandlerSelectors.any())
					.build();
	}
}