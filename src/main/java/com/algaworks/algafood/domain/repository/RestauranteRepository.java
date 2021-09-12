package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.infrastructure.repository.RestauranteRepositoryQueries;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, CustomJpaRepository<Restaurante, Long>,
	JpaSpecificationExecutor<Restaurante> //preparando interface para receber specifications
{
	@Query("from Restaurante r join fetch r.cozinha")
	List<Restaurante> findAll();  
	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long id);

	List<Restaurante> findByNomeStartingWith(String nome);

	List<Restaurante> findTop2ByNomeContaining(String nome);

	int countByCozinhaId(Long cozinhaId);
	
	@Query("from Restaurante where taxaFrete = :taxa")
	List<Restaurante> restaurantePorTaxaFrete(@Param("taxa") BigDecimal taxaFrete);
	
	Optional<Restaurante> consultaPorNome(@Param("nome") String nome, @Param("id") Long idCozinha);
	
}