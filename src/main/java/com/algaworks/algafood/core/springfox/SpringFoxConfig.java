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
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.CidadeModel;
import com.algaworks.algafood.api.v1.model.CozinhaModel;
import com.algaworks.algafood.api.v1.model.EstadoModel;
import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;
import com.algaworks.algafood.api.v1.model.GrupoModel;
import com.algaworks.algafood.api.v1.model.PedidoResumoModel;
import com.algaworks.algafood.api.v1.model.PermissaoModel;
import com.algaworks.algafood.api.v1.model.ProdutoModel;
import com.algaworks.algafood.api.v1.model.RestauranteBasicoModel;
import com.algaworks.algafood.api.v1.model.UsuarioModel;
import com.algaworks.algafood.api.v1.openapi.model.CidadesModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.CozinhasModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.EstadosModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.FormasPagamentoModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.GruposModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.LinksModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.PageableModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.PedidosResumoModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.PermissoesModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.ProdutosModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.RestaurantesBasicoModelOpenApi;
import com.algaworks.algafood.api.v1.openapi.model.UsuariosModelOpenApi;
import com.algaworks.algafood.api.v2.model.CidadeModelV2;
import com.algaworks.algafood.api.v2.model.CozinhaModelV2;
import com.algaworks.algafood.api.v2.openapi.model.CidadesModelV2OpenApi;
import com.algaworks.algafood.api.v2.openapi.model.CozinhasModelV2OpenApi;
import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RepresentationBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
//import springfox.documentation.service.Response;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//public class SpringFoxConfig {
@EnableSwagger2
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)	
public class SpringFoxConfig implements WebMvcConfigurer {

//Coment??rio no @Bean para remover o vers??o da documenta????o	
	@Bean
	public Docket apiDocketV1() {
		var typeResolver = new TypeResolver();
		
//		return new Docket(DocumentationType.OAS_30)
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("V1")
				.select() //selecionando endpoints a serem expostos
					.apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
					.paths(PathSelectors.ant("/v1/**"))
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
			        .directModelSubstitute(Links.class, LinksModelOpenApi.class)
			        .alternateTypeRules(AlternateTypeRules.newRule(
			        		typeResolver.resolve(PagedModel.class, CozinhaModel.class), 
			        		CozinhasModelOpenApi.class))
			        .alternateTypeRules(AlternateTypeRules.newRule(
		                    typeResolver.resolve(PagedModel.class, PedidoResumoModel.class),
		                    PedidosResumoModelOpenApi.class))
			        .alternateTypeRules(AlternateTypeRules.newRule(
			        		typeResolver.resolve(CollectionModel.class, CidadeModel.class), 
			        		CidadesModelOpenApi.class))
			        .alternateTypeRules(AlternateTypeRules.newRule(
			                typeResolver.resolve(CollectionModel.class, EstadoModel.class),
			                EstadosModelOpenApi.class))
			        .alternateTypeRules(AlternateTypeRules.newRule(
			        	    typeResolver.resolve(CollectionModel.class, FormaPagamentoModel.class),
			        	    FormasPagamentoModelOpenApi.class))
			        .alternateTypeRules(AlternateTypeRules.newRule(
			        	    typeResolver.resolve(CollectionModel.class, GrupoModel.class),
			        	    GruposModelOpenApi.class))
		        	.alternateTypeRules(AlternateTypeRules.newRule(
			        	        typeResolver.resolve(CollectionModel.class, PermissaoModel.class),
			        	        PermissoesModelOpenApi.class))
		        	.alternateTypeRules(AlternateTypeRules.newRule(
		        		    typeResolver.resolve(CollectionModel.class, ProdutoModel.class),
		        		    ProdutosModelOpenApi.class))
		        	.alternateTypeRules(AlternateTypeRules.newRule(
		        		    typeResolver.resolve(CollectionModel.class, RestauranteBasicoModel.class),
		        		    RestaurantesBasicoModelOpenApi.class))
	        		.alternateTypeRules(AlternateTypeRules.newRule(
	        		        typeResolver.resolve(CollectionModel.class, UsuarioModel.class),
	        		        UsuariosModelOpenApi.class))
	        		.securitySchemes(Arrays.asList(securitySchemas()))
	        		.securityContexts(Arrays.asList(securityContext()))
//			        .globalRequestParameters(Collections.singletonList(
//			                new RequestParameterBuilder()
//			                        .name("campos")
//			                        .description("Nomes das propriedades para filtrar na resposta, separados por v??rgula")
//			                        .in(ParameterType.QUERY)
//			                        .required(true)
//			                        .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
//			                        .build())
//			        )
					.apiInfo(apiInfoV1())
					.tags(new Tag("Cidades", "Gerencia as cidades"), 
							new Tag("Grupos", "Gerencia os grupos de usu??rios"),
							new Tag("Cozinhas", "Gerencia as cozinhas"),
							new Tag("Formas de Pagamento", "Forma de pagamento"),
							new Tag("Pedidos", "Gerencia de Pedidos"),
							new Tag("Restaurantes", "Gerencia os restaurantes"),
							new Tag("Estados", "Gerencia os estados"),
							new Tag("Produtos", "Gerencia os produtos de restaurantes"),
							new Tag("Usu??rios", "Gerencia os usu??rios"),
							new Tag("Estat??sticas", "Estat??sticas da AlgaFood"),
							new Tag("Permiss??es", "Gerencia de Permiss??es"));
	}
	
	private SecurityContext securityContext() {
		var securityReference = SecurityReference.builder()
				.reference("Algafood")
				.scopes(scopes().toArray(new AuthorizationScope[0]))
				.build();
		
		return SecurityContext.builder()
				.securityReferences(Arrays.asList(securityReference))
				.forPaths(PathSelectors.any())
				.build();
}

	private SecurityScheme securitySchemas() {
		return new OAuthBuilder()
				.name("Algafood")
				.grantTypes(grantTypes())
				.scopes(scopes())
				.build();
	}

	private List<AuthorizationScope> scopes() {
		return Arrays.asList(new AuthorizationScope("READ", "Acesso de leitura"),
				new AuthorizationScope("WRITE", "Acesso de escrita"));
	}

	private List<GrantType> grantTypes() {
		return Arrays.asList(new ResourceOwnerPasswordCredentialsGrant("/oauth/token"));
	}

	@Bean
	public Docket apiDocketV2() {
		var typeResolver = new TypeResolver();
		
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("V2")
				.select() //selecionando endpoints a serem expostos
					.apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
					.paths(PathSelectors.ant("/v2/**"))
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
			        .directModelSubstitute(Links.class, LinksModelOpenApi.class)
			        .alternateTypeRules(AlternateTypeRules.newRule(
			        	    typeResolver.resolve(PagedModel.class, CozinhaModelV2.class),
			        	    CozinhasModelV2OpenApi.class))
		        	.alternateTypeRules(AlternateTypeRules.newRule(
		        	        typeResolver.resolve(CollectionModel.class, CidadeModelV2.class),
		        	        CidadesModelV2OpenApi.class))
					.apiInfo(apiInfoV2())
					.tags(new Tag("Cidades", "Gerencia as cidades"), 
							new Tag("Cozinhas", "Gerencia as cozinhas"));
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
//		            .description("Requisi????o inv??lida (erro do cliente)")
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
//		            .description("Recurso n??o possui representa????o que poderia ser aceita pelo consumidor")
//		            .build(),
//		        new ResponseBuilder()
//		            .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
//		            .description("Requisi????o recusada porque o corpo est?? em um formato n??o suportado")
//		            .representation( MediaType.APPLICATION_JSON )
//                    .apply(getProblemaModelReference())
//		            .build()
				new ResponseMessageBuilder()
				.code(HttpStatus.BAD_REQUEST.value())
				.message("Requisi????o inv??lida (erro do cliente)")
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
//		            .description("Requisi????o inv??lida (erro do cliente)")
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
//		            .description("Recurso n??o possui representa????o que poderia ser aceita pelo consumidor")
//		            .build(),
//		        new ResponseBuilder()
//		            .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
//		            .description("Requisi????o recusada porque o corpo est?? em um formato n??o suportado")
//		            .representation( MediaType.APPLICATION_JSON )
//                    .apply(getProblemaModelReference())
//		            .build()
				new ResponseMessageBuilder()
				.code(HttpStatus.BAD_REQUEST.value())
				.message("Requisi????o inv??lida (erro do cliente)")
				.responseModel(new ModelRef("Problema"))
				.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Erro interno no servidor")
					.responseModel(new ModelRef("Problema"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Recurso n??o possui representa????o que poderia ser aceita pelo consumidor")
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
					.message("Requisi????o recusada porque o corpo est?? em um formato n??o suportado")
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
//		          .description("Recurso n??o possui representa????o que pode ser aceita pelo consumidor")
//		          .build()
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Erro interno do servidor")
					.responseModel(new ModelRef("Problema"))
					.build(),
					new ResponseMessageBuilder()
					.code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Recurso n??o possui representa????o que poderia ser aceita pelo consumidor")
					.build()
				);
	}

	public ApiInfo apiInfoV1() {
		return new ApiInfoBuilder()
				.title("AlgaFood API")
				.description("API aberta para clientes e restaurantes.<br>"
						+ "<strong>Essa vers??o da API est?? depreciada e deixar?? de existir a partir de 01/01/2022. "
						+ "Use a vers??o mais atual da API</strong>")
				.version("1")
				.contact(new Contact("Rodrigo", "https://github.com/RodrigoGrauU", "enderecoemail@email.com"))
				.build();
	}
	
	public ApiInfo apiInfoV2() {
		return new ApiInfoBuilder()
				.title("AlgaFood API")
				.description("API aberta para clientes e restaurantes")
				.version("2")
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
