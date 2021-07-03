package com.compassouol.sprint3.controller.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.compassouol.sprint3.model.Carro;
import com.compassouol.sprint3.repository.CarroRepository;

public class AtualizacaoCarroForm {

	@NotNull
	private BigDecimal valor;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Carro atualizar(Long id, CarroRepository carroRepository) {
		Carro carro = carroRepository.getOne(id);

		carro.setValor(this.valor);

		return carro;
	}

}
