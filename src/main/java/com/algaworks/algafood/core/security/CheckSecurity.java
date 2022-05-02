package com.algaworks.algafood.core.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface Cozinhas {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('CONSULTAR_COZINHAS')")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeEditar {}
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and  isAuthenticated()")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeConsultar {}
	}
	
	public @interface Restaurantes {
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and "
				+ "hasAuthority('EDITAR_RESTAURANTES') or "
				+ "@algaSecurity.gerenciaRestaurante(#restauranteId)") //o @ acessa o bean e o # acessa a variavel
	    @Retention(RetentionPolicy.RUNTIME)
	    @Target(ElementType.METHOD)
	    public @interface PodeGerenciarFuncionamento {}
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_RESTAURANTES')")
	    @Retention(RetentionPolicy.RUNTIME)
	    @Target(ElementType.METHOD)
	    public @interface PodeGerenciarCadastro { }

	    @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
	    @Retention(RetentionPolicy.RUNTIME)
	    @Target(ElementType.METHOD)
	    public @interface PodeConsultar { }
	    
	}
	
	public @interface Pedidos {
			
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@PostAuthorize("hasAuthority('CONSULTAR_PEDIDOS') or " 	//post executa apos a chamada do metodo
				+ "@algaSecurity.getUsuarioId() == returnObject.cliente.id or "
				+ "@algaSecurity.gerenciaRestaurante(returnObject.restaurante.id)") //returnObject referencia o objeto de retorno do metodo anotado
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeBuscar { }
	
		@PreAuthorize("hasAuthority('SCOPE_READ') and (hasAuthority('CONSULTAR_PEDIDOS') or " 
				+ "@algaSecurity.getUsuarioId() == #filtro.clienteId or"
				+ "@algaSecurity.gerenciaRestaurante(#filtro.restauranteId))")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodePesquisar { }
	}
}
