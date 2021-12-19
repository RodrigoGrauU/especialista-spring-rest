package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.PermissaoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {

	private static final String MSG_GRUPO_EM_USO = "O grupo que possui %d não pode ser removido pois possui registros associados a ele na base";
	
	@Autowired
	private GrupoRepository repository;
	
	@Autowired
	private CadastroPermissaoService cadastroPermissaoService;
	
	public List<Grupo> listar() {
		return repository.findAll();
	}

	@Transactional
	public Grupo adicionar(Grupo grupo) {
		return repository.save(grupo);
	}
	
	@Transactional
	public Grupo alterar(Grupo grupo) {
		return repository.save(grupo);
	}

	public Grupo buscarOuFalhar(Long grupoId) {
		return repository.findById(grupoId)
				.orElseThrow(() -> new GrupoNaoEncontradoException(String.format("Não foi encontrado grupo com id %d", grupoId)));
	}

	@Transactional
	public void remover(Long grupoId) {
		try {
			repository.deleteById(grupoId);
			repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException(String.format(MSG_GRUPO_EM_USO, grupoId));
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(grupoId);
		}
	}

	@Transactional
	public void associarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		Permissao permissao = cadastroPermissaoService.buscarOuFalhar(permissaoId);
		
		grupo.adicionarPermissao(permissao);
	}

	@Transactional
	public void desassociarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		Permissao permissao = cadastroPermissaoService.buscarOuFalhar(permissaoId);
		
		grupo.removerPermissao(permissao);
	}

	public Permissao buscarPermissao(Long grupoId, Long permissaoId) {
		return repository.findByPermissao(grupoId, permissaoId).orElseThrow(() ->
		new PermissaoNaoEncontradoException(String.format("Permissão com id %d não encontrada para grupo %d", permissaoId, grupoId)));
	}
}
