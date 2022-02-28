package com.algaworks.algafood.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.controller.PedidoController;

@Component
public class AlgaLinks {

	public static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM));
	
	public Link linkToPedidos() {
    	TemplateVariables filtroVariables = new TemplateVariables(
    			new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
    			new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
    			new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
    			new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM));
    	
    	String pedidoUrl = linkTo(PedidoController.class).toUri().toString();
    	
    	return new Link(UriTemplate.of(pedidoUrl, PAGINACAO_VARIABLES.concat(filtroVariables)), "pedidos");
	}
}
