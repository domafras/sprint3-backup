package com.compassouol.sprint3.controller.dto;

import java.math.BigDecimal;

import com.compassouol.sprint3.model.Carro;

public class DetalhesDoCarroDto {

	private Long id;
	private String chassi;
	private String nome;
    private String marca;
    private String cor;
    private BigDecimal valor;
    private int ano;
    
    public DetalhesDoCarroDto(Carro carro) {
    	this.id = carro.getId();
    	this.chassi = carro.getChassi();
    	this.nome = carro.getNome();
    	this.marca = carro.getMarca();
    	this.cor = carro.getCor();
    	this.valor = carro.getValor();
    	this.ano = carro.getAno();
    }

	public Long getId() {
		return id;
	}

	public String getChassi() {
		return chassi;
	}

	public String getNome() {
		return nome;
	}

	public String getMarca() {
		return marca;
	}

	public String getCor() {
		return cor;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public int getAno() {
		return ano;
	}
    
}
