package com.compassouol.sprint3.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.compassouol.sprint3.model.Carro;

@Repository
public interface CarroRepository extends PagingAndSortingRepository<Carro, Long>,
		JpaSpecificationExecutor<Carro> /* JpaRepository<Carro, Long> */ {

	Carro findByChassi(String chassi);

	Carro getOne(Long id);

}
