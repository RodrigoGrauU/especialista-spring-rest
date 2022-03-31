package com.algaworks.algafood.api.v2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.ResourceUriHelper;
import com.algaworks.algafood.api.v2.assembler.CidadeInputDisassemblerV2;
import com.algaworks.algafood.api.v2.assembler.CidadeModelAssemblerV2;
import com.algaworks.algafood.api.v2.model.CidadeModelV2;
import com.algaworks.algafood.api.v2.model.input.CidadeInputV2;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(value = "/v2/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeControllerV2 {

	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	CadastroCidadeService cadastroCidade;
	
	@Autowired
	CadastroEstadoService estadoService;
	
	@Autowired
	CidadeModelAssemblerV2 cidadeModelAssemblerV2;
	
	@Autowired
	CidadeInputDisassemblerV2 cidadeInputDisassemblerV2;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CollectionModel<CidadeModelV2> listar() {
		List<Cidade> cidades = cidadeRepository.findAll();
		
		return cidadeModelAssemblerV2.toCollectionModel(cidades);
	}
	
	@GetMapping("/{cidadeId}")
	public CidadeModelV2 buscar(
			@PathVariable Long cidadeId) {
		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
		
		return cidadeModelAssemblerV2.toModel(cidade);
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public CidadeModelV2 adicionar(
			@RequestBody @Valid CidadeInputV2 cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassemblerV2.toDomainObject(cidadeInput);
			
			cidade = cadastroCidade.salvar(cidade);
			
			CidadeModelV2 cidadeModel = cidadeModelAssemblerV2.toModel(cidade);
			
			ResourceUriHelper.addUriInResponseHeader(cidadeModel.getIdCidade());
			
			return cidadeModel;
			
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{cidadeId}")
	public CidadeModelV2 atualizar(
			@PathVariable Long cidadeId, 
			@RequestBody @Valid CidadeInputV2 cidadeInput) {					
		try {
			Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
			//BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			cidadeInputDisassemblerV2.copyToDomainObject(cidadeInput, cidadeAtual);
			
			return cidadeModelAssemblerV2.toModel(cadastroCidade.salvar(cidadeAtual));
			
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
			cadastroCidade.excluir(cidadeId);
	}
	
}
