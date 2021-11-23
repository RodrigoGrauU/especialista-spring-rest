package com.algaworks.algafood;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.restassured.http.ContentType;

@ExtendWith(SpringExtension.class) //fornece suporte para carregar um contexto do spring para utilização do recurso do framework no teste
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //fornece funcionalidade do spring boot nos testes
public class CadastroCozinhaIT {

	@LocalServerPort //injeção da RANDOM_PORT
	private int port;
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		//RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); habilita log importante para debug
		given()
			.basePath("/cozinhas")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
}
