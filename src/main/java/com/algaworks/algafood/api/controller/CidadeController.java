package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.CidadeInputDisassembler;
import com.algaworks.algafood.api.assembler.CidadeModelAssembler;
import com.algaworks.algafood.api.model.CidadeModel;
import com.algaworks.algafood.api.model.input.CidadeInput;
import com.algaworks.algafood.api.openapi.controller.CidadeControllerOpenApi;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/cidades")
public class CidadeController implements CidadeControllerOpenApi {

	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	CadastroCidadeService cadastroCidade;
	
	@Autowired
	CadastroEstadoService estadoService;
	
	@Autowired
	CidadeModelAssembler cidadeModelAssembler;
	
	@Autowired
	CidadeInputDisassembler cidadeInputDisassembler;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CidadeModel> listar() {
		List<Cidade> cidades = cidadeRepository.findAll();
		return cidadeModelAssembler.toCollectionModel(cidades);
	}
	
	@GetMapping("/{cidadeId}")
	public CidadeModel buscar(
			@PathVariable Long cidadeId) {
		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
		return cidadeModelAssembler.toModel(cidade);
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public CidadeModel adicionar(
			@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
			return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidade));
			
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{cidadeId}")
	public CidadeModel atualizar(
			@PathVariable Long cidadeId, 
			@RequestBody @Valid CidadeInput cidadeInput) {					
		try {
			Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
			//BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
			
			return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidadeAtual));
			
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
