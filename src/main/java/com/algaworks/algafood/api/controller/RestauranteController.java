package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.algaworks.algafood.api.assembler.RestauranteApenasNomeModelAssembler;
import com.algaworks.algafood.api.assembler.RestauranteBasicoModelAssembler;
import com.algaworks.algafood.api.assembler.RestauranteInputDisassembler;
import com.algaworks.algafood.api.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.model.RestauranteApenasNomeModel;
import com.algaworks.algafood.api.model.RestauranteBasicoModel;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.api.openapi.controller.RestauranteControllerOpenApi;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradoException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
//import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController implements RestauranteControllerOpenApi {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;
	
	@Autowired
	private RestauranteInputDisassembler restauranteInputDisassembler;
	
	@Autowired
	private RestauranteBasicoModelAssembler restauranteBasicoModelAssembler;

	@Autowired
	private RestauranteApenasNomeModelAssembler restauranteApenasNomeModelAssembler;
	
//	@JsonView(RestauranteView.Resumo.class)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<RestauranteBasicoModel> listar() {
		return restauranteBasicoModelAssembler
                .toCollectionModel(restauranteRepository.findAll());
	}
	
//	@JsonView(RestauranteView.ApenasNome.class)
	@GetMapping(params = "projecao=apenas-nome", produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<RestauranteApenasNomeModel> listarApenasNomes() {
		return restauranteApenasNomeModelAssembler
                .toCollectionModel(restauranteRepository.findAll());
	}
	
//	@GetMapping
//	public MappingJacksonValue listar(@RequestParam(required = false) String projecao) {
//		List<Restaurante> restaurantes = restauranteRepository.findAll();
//		List<RestauranteModel> restaurantesModel = restauranteModelAssembler.toCollectionModel(restaurantes);
//		
//		MappingJacksonValue restaurantesWrapper = new MappingJacksonValue(restaurantesModel);
//		
//		restaurantesWrapper.setSerializationView(RestauranteView.Resumo.class);
//		
//		if ("apenas-nome".equals(projecao)) {
//			restaurantesWrapper.setSerializationView(RestauranteView.ApenasNome.class);
//		} else if ("completo".equals(projecao)) {
//			restaurantesWrapper.setSerializationView(null);
//		}
//		
//		return restaurantesWrapper;
//	}
	
//	@GetMapping
//	public List<RestauranteModel> listar() {
//		return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
//	}
//	
//	@JsonView(RestauranteView.Resumo.class)
//	@GetMapping(params = "projecao=resumo")
//	public List<RestauranteModel> listarResumido() {
//		return listar();
//	}
//
//	@JsonView(RestauranteView.ApenasNome.class)
//	@GetMapping(params = "projecao=apenas-nome")
//	public List<RestauranteModel> listarApenasNomes() {
//		return listar();
//	}
	
	@GetMapping(value = "/{restauranteId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RestauranteModel buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		return restauranteModelAssembler.toModel(restaurante);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
		try {
			Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
			return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restaurante));
		} catch(CozinhaNaoEncontradaException |CidadeNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping(value = "/{restauranteId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RestauranteModel atualizar(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInput restauranteInput) {
		
		try {
			Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
			
			restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);
//			Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
			
//		BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro",
//				"produtos");
		
			return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restauranteAtual));
		} catch(CozinhaNaoEncontradaException | CidadeNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
		
	}
	
	@PutMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> ativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.ativar(restauranteId);
	    
	    return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> inativar(@PathVariable Long restauranteId) {
		cadastroRestaurante.inativar(restauranteId);
	    
	    return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
		cadastroRestaurante.inativar(restauranteIds);
	}
	
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
		cadastroRestaurante.ativar(restauranteIds);
	}
	
	@PutMapping("/{restauranteId}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> fechar(@PathVariable Long restauranteId) {
		cadastroRestaurante.fechar(restauranteId);
	    
	    return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{restauranteId}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> abrir(@PathVariable Long restauranteId) {
		cadastroRestaurante.abrir(restauranteId);
	    
	    return ResponseEntity.noContent().build();
	}
}