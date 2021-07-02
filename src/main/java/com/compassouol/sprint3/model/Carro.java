package com.compassouol.sprint3.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Carro {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @NotEmpty @Length(max = 18, min = 18) 
	private String chassi;
	
	@NotNull @NotEmpty
	private String nome;
	
	@NotNull @NotEmpty
	private String marca;
	
	@NotNull @NotEmpty
	private String cor;
	
	@NotNull @Digits(integer=5,fraction=2,message="Apenas milhar e 2 casas após o ponto.")
	private BigDecimal valor;
	
	@NotNull 
	private int ano;
	
	public Carro() {
		
	}
	
	public Carro(String chassi, String nome, String marca, String cor, BigDecimal valor, int ano) {
		this.chassi = chassi;
		this.nome = nome;
		this.marca = marca;
		this.cor = cor;
		this.valor = valor;
		this.ano = ano;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	
}
