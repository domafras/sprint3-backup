package com.compassouol.sprint3.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.compassouol.sprint3.model.Carro;

@Repository
public interface CarroRepository extends PagingAndSortingRepository<Carro, Long>, JpaSpecificationExecutor<Carro> /*JpaRepository<Carro, Long>*/{

	Carro findByChassi(String chassi);
//
//	Page<Carro> findByMarca(String marca, Pageable paginacao);
//
//	Page<Carro> findByNome(String nome, Pageable paginacao);
//
//	Page<Carro> findByCor(String cor, Pageable paginacao);
//
//	Page<Carro> findByAno(Integer ano, Pageable paginacao);
//
//	//@Query(value = "SELECT min(valor) FROM Carro", nativeQuery = true)
//	@Query("SELECT min(valor) FROM Carro")
//	public BigDecimal min();
//	
//	@Query(value = "SELECT max(valor) FROM Carro")
//	public BigDecimal max();

	Carro getOne(Long id);

}
