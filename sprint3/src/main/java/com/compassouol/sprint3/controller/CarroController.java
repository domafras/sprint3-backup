package com.compassouol.sprint3.controller;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.compassouol.sprint3.controller.dto.CarroDto;
import com.compassouol.sprint3.controller.dto.DetalhesDoCarroDto;
import com.compassouol.sprint3.controller.form.AtualizacaoCarroForm;
import com.compassouol.sprint3.controller.form.CarroForm;
import com.compassouol.sprint3.model.Carro;
import com.compassouol.sprint3.repository.CarroRepository;

@RestController
@RequestMapping("/api")
public class CarroController {

	@Autowired
	private CarroRepository carroRepository;

	// Cadastrar novo carro + validação chassi único
	@PostMapping("/cars")
	@Transactional
	public ResponseEntity<CarroDto> cadastrar(@RequestBody @Valid CarroForm form, UriComponentsBuilder uriBuilder) {
		Carro carro = form.converter(carroRepository);
		if (carro != null) {
			carroRepository.save(carro);
			URI uri = uriBuilder.path("/api/cars/{id}").buildAndExpand(carro.getId()).toUri();
			return ResponseEntity.created(uri).body(new CarroDto(carro));
		} else {
			System.out.println("Chassi já cadastrado, este valor deve ser único.");
			return null;
		}
	}

	// Detalhar carro por id
	@GetMapping("/cars/{id}")
	public ResponseEntity<DetalhesDoCarroDto> detalhar(@PathVariable Long id) {
		Optional<Carro> carro = carroRepository.findById(id);
		if (carro.isPresent()) {
			return ResponseEntity.ok(new DetalhesDoCarroDto(carro.get()));
		}
		return ResponseEntity.notFound().build();
	}

	// Atualizar valor de um carro
	@PutMapping("/cars/{id}")
	@Transactional
	public ResponseEntity<CarroDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoCarroForm form) {
		Optional<Carro> optional = carroRepository.findById(id);
		if (optional.isPresent()) {
			Carro carro = form.atualizar(id, carroRepository);
			return ResponseEntity.ok(new CarroDto(carro));
		}
		return ResponseEntity.notFound().build();
	}

	// Excluir carro por id
	@DeleteMapping("/cars/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Carro> optional = carroRepository.findById(id);
		if (optional.isPresent()) {
			carroRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	// Listar, ordenar e filtrar
	// Paginação simples, com parâmetros, paginação default e query dinâmica
	@GetMapping(value = "/cars")
	public List<CarroDto> paginacaoComParametrosEOrdenacao(CarroForm carroForm,
			@PageableDefault(direction = Direction.ASC, page = 0, size = 20) Pageable pageable) {
		Collection<Carro> carros = (Collection<Carro>) carroRepository.findAll(carroForm.toSpec(), pageable)
				.getContent();
		return CarroDto.converter(carros);
	}

	//Filtrar carro mais caro
	@GetMapping(value = "/cars/caro")
	public List<CarroDto> max(CarroForm carroForm,
			@PageableDefault(direction = Direction.DESC, page = 0, size = 1, sort = "valor") Pageable pageable) {
		Collection<Carro> carros = (Collection<Carro>) carroRepository.findAll(carroForm.toSpec(), pageable)
				.getContent();
		return CarroDto.converter(carros);
	}

	//Filtrar carro mais barato
	@GetMapping(value = "/cars/barato")
	public List<CarroDto> min(CarroForm carroForm,
			@PageableDefault(direction = Direction.ASC, page = 0, size = 1, sort = "valor") Pageable pageable) {
		Collection<Carro> carros = (Collection<Carro>) carroRepository.findAll(carroForm.toSpec(), pageable)
				.getContent();
		return CarroDto.converter(carros);
	}
}
