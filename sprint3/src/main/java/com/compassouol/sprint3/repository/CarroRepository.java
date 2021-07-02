package com.compassouol.sprint3.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.compassouol.sprint3.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long>{

	Carro findByChassi(String chassi);

	Page<Carro> findByMarca(String marca, Pageable paginacao);

	List<Carro> findByNome(String nome);

}
