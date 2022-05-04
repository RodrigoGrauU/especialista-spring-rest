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
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeCriar { }
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('GERENCIAR_PEDIDOS') or "
				+ "@algaSecurity.gerenciaRestauranteDoPedido(#codigoPedido))")
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		public @interface PodeGerenciarPedidos {}
		
	}
	
	public @interface FormasPagamento {

	    @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_FORMAS_PAGAMENTO')")
	    @Retention(RetentionPolicy.RUNTIME)
	    @Target(ElementType.METHOD)
	    public @interface PodeEditar { }

	    @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
	    @Retention(RetentionPolicy.RUNTIME)
	    @Target(ElementType.METHOD)
	    public @interface PodeConsultar { }
	    
	}
	
	public @interface Cidades {

	    @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_CIDADES')")
	    @Retention(RetentionPolicy.RUNTIME)
	    @Target(ElementType.METHOD)
	    public @interface PodeEditar { }

	    @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
	    @Retention(RetentionPolicy.RUNTIME)
	    @Target(ElementType.METHOD)
	    public @interface PodeConsultar { }
	    
	}

	public @interface Estados {
	    
	    @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_ESTADOS')")
	    @Retention(RetentionPolicy.RUNTIME)
	    @Target(ElementType.METHOD)
	    public @interface PodeEditar { }

	    @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
	    @Retention(RetentionPolicy.RUNTIME)
	    @Target(ElementType.METHOD)
	    public @interface PodeConsultar { }
	    
	}
	
	public @interface UsuariosGruposPermissoes {

	    @PreAuthorize("hasAuthority('SCOPE_WRITE') and "
	            + "@algaSecurity.getUsuarioId() == #usuarioId")
	    @Retention(RetentionPolicy.RUNTIME)
	    @Target(ElementType.METHOD)
	    public @interface PodeAlterarPropriaSenha { }
	    
	    @PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') or "
	            + "@algaSecurity.getUsuarioId() == #usuarioId)")
	    @Retention(RetentionPolicy.RUNTIME)
	    @Target(ElementType.METHOD)
	    public @interface PodeAlterarUsuario { }

	    @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES')")
	    @Retention(RetentionPolicy.RUNTIME)
	    @Target(ElementType.METHOD)
	    public @interface PodeEditar { }
	    

	    @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('CONSULTAR_USUARIOS_GRUPOS_PERMISSOES')")
	    @Retention(RetentionPolicy.RUNTIME)
	    @Target(ElementType.METHOD)
	    public @interface PodeConsultar { }
	    
	}
	
	public @interface Estatisticas {

	    @PreAuthorize("hasAuthority('SCOPE_READ') and "
	            + "hasAuthority('GERAR_RELATORIOS')")
	    @Retention(RetentionPolicy.RUNTIME)
	    @Target(ElementType.METHOD)
	    public @interface PodeConsultar { }
	    
	}
}
