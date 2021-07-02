package com.compassouol.sprint3.controller.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.Specification;

import com.compassouol.sprint3.model.Carro;
import com.compassouol.sprint3.repository.CarroRepository;

public class CarroForm {

	@NotBlank
	@Length(max = 18, min = 18, message = "Devem ser 18 caracteres em maiúsculos")
	private String chassi;

	@NotNull
	@NotEmpty
	@Length(max = 25)
	private String nome;

	@NotNull
	@NotEmpty
	@Length(max = 25)
	private String marca;

	@NotNull
	@NotEmpty
	@Length(max = 15)
	private String cor;

	@NotNull
	@Digits(integer = 6, fraction = 2, message = "Apenas milhar e 2 casas após o ponto.")
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

	// Validação chassi único
	public Carro converter(CarroRepository carroRepository) {
		Carro carro = carroRepository.findByChassi(this.chassi);
		if (carro == null) {
			return new Carro(chassi, nome, marca, cor, valor, ano);
		} else {
			return null;
		}
	}


	// Utilizando Specification para criar querys dinâmicas
	public Specification<Carro> toSpec() {
		return (root, query, builder) -> {
			List<Predicate> predicados = new ArrayList<>();
			if (nome != null) {
				Path<String> campoNome = root.<String>get("nome");
				Predicate predicadoNome = builder.like(campoNome, "%" + nome + "%");
				predicados.add(predicadoNome);
			}
			if (marca != null) {
				Path<String> campoMarca = root.<String>get("marca");
				Predicate predicadoMarca = builder.like(campoMarca, "%" + marca + "%");
				predicados.add(predicadoMarca);
			}
			if (cor != null) {
				Path<String> campoCor = root.<String>get("cor");
				Predicate predicadoCor = builder.like(campoCor, "%" + cor + "%");
				predicados.add(predicadoCor);
			}
			if (ano != 0) {
				Expression<String> campoAno = root.get("ano").as(String.class);
				Predicate predicadoAno = builder.like(campoAno, "%" + ano + "%");
				predicados.add(predicadoAno);
			}
			return builder.and(predicados.toArray(new Predicate[0]));
		};
	}
}