package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping(value = "/cozinhas") //, produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {
	
	@Autowired
	CozinhaRepository cozinhaRepository;
	
	@Autowired
	CadastroCozinhaService cadastroCozinha;

	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.listar();
	}
	
	@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
		
		if(cozinha != null) {
			return ResponseEntity.ok(cozinha);
		}
		
		//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		//atalho para o código acima
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) //retorna o status 201
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinha.salvar(cozinha);
	}
	
	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@RequestBody Cozinha cozinha, @PathVariable Long cozinhaId) {
		Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);
		if(cozinhaAtual != null) {
			//cozinhaAtual.setNome(cozinha.getNome()); 
			
			//Utilizando para copiar propriedade e evitar código como o comentado acima
			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			cozinhaAtual = cadastroCozinha.salvar(cozinhaAtual);
			return ResponseEntity.ok(cozinhaAtual); 
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {

		try {
			cadastroCozinha.excluir(cozinhaId);
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
