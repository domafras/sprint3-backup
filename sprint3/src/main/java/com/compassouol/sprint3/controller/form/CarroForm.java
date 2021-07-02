package com.compassouol.sprint3.controller.form;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.compassouol.sprint3.model.Carro;
import com.compassouol.sprint3.repository.CarroRepository;

public class CarroForm {

	@NotNull @NotEmpty @Length(max = 18, min = 18)
	private String chassi;
	
	@NotNull @NotEmpty @Length(max = 25)
	private String nome;
	
	@NotNull @NotEmpty @Length(max = 25)
	private String marca;
	
	@NotNull @NotEmpty @Length(max = 15)
	private String cor;
	
	@NotNull @Digits(integer=5,fraction=2,message="Apenas milhar e 2 casas após o ponto.")	
	private BigDecimal valor;
	
	@NotNull
	private int ano;
	
	public String getChassi() {
		return chassi;
	}
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public Carro converter(CarroRepository carroRepository) {
		Carro carro = carroRepository.findByChassi(this.chassi);
		if (carro == null) {
			return new Carro(chassi, nome, marca, cor, valor, ano);
		} else {
			return null;
		}
	}	
}
