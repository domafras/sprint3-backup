package com.compassouol.sprint3.controller.dto;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import com.compassouol.sprint3.model.Carro;

public class CarroDto {

	private String chassi;
	private String nome;
    private String marca;
    private String cor;
    private BigDecimal valor;
    private int ano;
    
    public CarroDto() {
    	
    }
	
    public CarroDto(Carro carro) {
    	this.chassi = carro.getChassi();
    	this.nome = carro.getNome();
    	this.marca = carro.getMarca();
    	this.cor = carro.getCor();
    	this.valor = carro.getValor();
    	this.ano = carro.getAno();
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

	//A partir do Java 8 -> Conversão de lista carros para CarroDto
	public static Page<CarroDto> converter(Page<Carro> carros) {
		return carros.map(CarroDto::new);
	}
    
}
