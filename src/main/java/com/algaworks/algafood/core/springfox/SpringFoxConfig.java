package com.algaworks.algafood.core.springfox;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.openapi.model.CozinhasModelOpenApi;
import com.algaworks.algafood.api.openapi.model.PageableModelOpenApi;
import com.algaworks.algafood.api.openapi.model.PedidosResumoModelOpenApi;
import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RepresentationBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
//import springfox.documentation.service.Response;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//public class SpringFoxConfig {
@EnableSwagger2
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)	
public class SpringFoxConfig implements WebMvcConfigurer {

	@SuppressWarnings("deprecation")
	@Bean
	public Docket apiDocket() {
		var typeResolver = new TypeResolver();
		
//		return new Docket(DocumentationType.OAS_30)
		return new Docket(DocumentationType.SWAGGER_2)
				.select() //selecionando endpoints a serem expostos
					.apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
					.paths(PathSelectors.any())
//		          .paths(PathSelectors.ant("/restaurantes/*"))
					.build()
					.useDefaultResponseMessages(false)
					.globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
					.globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
			        .globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
			        .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
			        .additionalModels(typeResolver.resolve(Problem.class))
			        .ignoredParameterTypes(ServletWebRequest.class, URL.class, URI.class, URLStreamHandler.class, Resource.class,
		                    File.class, InputStream.class)
			        .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
			        .alternateTypeRules(AlternateTypeRules.newRule(
			        		typeResolver.resolve(Page.class, CozinhaModel.class), 
			        		CozinhasModelOpenApi.class))
			        .alternateTypeRules(AlternateTypeRules.newRule(
		                    typeResolver.resolve(Page.class, PedidoResumoModel.class),
		                    PedidosResumoModelOpenApi.class))
//			        .globalRequestParameters(Collections.singletonList(
//			                new RequestParameterBuilder()
//			                        .name("campos")
//			                        .description("Nomes das propriedades para filtrar na resposta, separados por vírgula")
//			                        .in(ParameterType.QUERY)
//			                        .required(true)
//			                        .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
//			                        .build())
//			        )
					.apiInfo(apiInfo())
					.tags(new Tag("Cidades", "Gerencia as cidades"), 
							new Tag("Grupos", "Gerencia os grupos de usuários"),
							new Tag("Cozinhas", "Gerencia as cozinhas"),
							new Tag("Formas de Pagamento", "Forma de pagamento"),
							new Tag("Pedidos", "Gerencia de Pedidos"),
							new Tag("Restaurantes", "Gerencia os restaurantes"),
							new Tag("Estados", "Gerencia os estados"),
							new Tag("Produtos", "Gerencia os produtos de restaurantes"),
							new Tag("Usuários", "Gerencia os usuários"),
							new Tag("Estatísticas", "Estatísticas da AlgaFood"));
	}
	
	@Bean
	public JacksonModuleRegistrar springFoxJacksonConfig() {
		return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
	}
	
//	private Consumer<RepresentationBuilder> getProblemaModelReference() {
//	    return r -> r.model(m -> m.name("Problema")
//	            .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
//	                    q -> q.name("Problema").namespace("com.algaworks.algafood.api.exceptionhandler")))));
//	}
	
	@SuppressWarnings("deprecation")
	private List<ResponseMessage> globalDeleteResponseMessages() {
		return Arrays.asList(
//		        new ResponseBuilder()
//		            .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
//		            .description("Requisição inválida (erro do cliente)")
//		            .representation( MediaType.APPLICATION_JSON )
//                    .apply(getProblemaModelReference())
//		            .build(),
//		        new ResponseBuilder()
//		            .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
//		            .description("Erro interno no servidor")
//		            .representation( MediaType.APPLICATION_JSON )
//                    .apply(getProblemaModelReference())
//		            .build(),
//		        new ResponseBuilder()
//		            .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
//		            .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
//		            .build(),
//		        new ResponseBuilder()
//		            .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
//		            .description("Requisição recusada porque o corpo está em um formato não suportado")
//		            .representation( MediaType.APPLICATION_JSON )
//                    .apply(getProblemaModelReference())
//		            .build()
				new ResponseMessageBuilder()
				.code(HttpStatus.BAD_REQUEST.value())
				.message("Requisição inválida (erro do cliente)")
				.responseModel(new ModelRef("Problema"))
				.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Erro interno no servidor")
					.responseModel(new ModelRef("Problema"))
					.build()
		    );
	}

	@SuppressWarnings("deprecation")
	private List<ResponseMessage> globalPostPutResponseMessages() {
		return Arrays.asList(
//		        new ResponseBuilder()
//		            .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
//		            .description("Requisição inválida (erro do cliente)")
//		            .representation( MediaType.APPLICATION_JSON )
//                    .apply(getProblemaModelReference())
//		            .build(),
//		        new ResponseBuilder()
//		            .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
//		            .description("Erro interno no servidor")
//		            .representation( MediaType.APPLICATION_JSON )
//                    .apply(getProblemaModelReference())
//		            .build(),
//		        new ResponseBuilder()
//		            .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
//		            .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
//		            .build(),
//		        new ResponseBuilder()
//		            .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
//		            .description("Requisição recusada porque o corpo está em um formato não suportado")
//		            .representation( MediaType.APPLICATION_JSON )
//                    .apply(getProblemaModelReference())
//		            .build()
				new ResponseMessageBuilder()
				.code(HttpStatus.BAD_REQUEST.value())
				.message("Requisição inválida (erro do cliente)")
				.responseModel(new ModelRef("Problema"))
				.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Erro interno no servidor")
					.responseModel(new ModelRef("Problema"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Recurso não possui representação que poderia ser aceita pelo consumidor")
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
					.message("Requisição recusada porque o corpo está em um formato não suportado")
					.responseModel(new ModelRef("Problema"))
					.build()
		    );
	}

	@SuppressWarnings("deprecation")
	private List<ResponseMessage> globalGetResponseMessages() {
		return Arrays.asList(
//				new ResponseBuilder()
//				.code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
//				.description("Erro interno do servidor")
//				.representation(MediaType.APPLICATION_JSON)
//				.apply(getProblemaModelReference()),
//				.build().
//				new ResponseBuilder()
//		          .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
//		          .description("Recurso não possui representação que pode ser aceita pelo consumidor")
//		          .build()
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Erro interno do servidor")
					.responseModel(new ModelRef("Problema"))
					.build(),
					new ResponseMessageBuilder()
					.code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Recurso não possui representação que poderia ser aceita pelo consumidor")
					.build()
				);
	}

	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("AlgaFood API")
				.description("API aberta para clientes e restaurantes")
				.version("1")
				.contact(new Contact("Rodrigo", "https://github.com/RodrigoGrauU", "enderecoemail@email.com"))
				.build();
	}
	
	//necessario para funcionar o front 
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
