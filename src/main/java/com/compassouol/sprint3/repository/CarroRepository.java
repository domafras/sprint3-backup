package com.compassouol.sprint3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compassouol.sprint3.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long>{

	Carro findByChassi(String chassi);

}
