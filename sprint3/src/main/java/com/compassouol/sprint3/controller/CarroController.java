package com.compassouol.sprint3.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
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
	
	//Cadastrar novo carro
	@PostMapping("/cars")
	@Transactional
	public ResponseEntity<CarroDto> cadastrar(@RequestBody @Valid CarroForm form, UriComponentsBuilder uriBuilder) {
		Carro carro = form.converter(carroRepository);
		carroRepository.save(carro);
		
		URI uri = uriBuilder.path("/api/cars/{id}").buildAndExpand(carro.getId()).toUri();
		return ResponseEntity.created(uri).body(new CarroDto(carro));
	}
	
	//Lista todos os carros
//	@GetMapping("/cars")
//	public List<CarroDto> listar() {
//		List<Carro> carros = carroRepository.findAll();
//		return CarroDto.converter(carros);
//	}
	
	//Detalhar carro por id
	@GetMapping("/cars/{id}")
	public ResponseEntity<DetalhesDoCarroDto> detalhar(@PathVariable Long id) {
		Optional<Carro> carro = carroRepository.findById(id);
		if (carro.isPresent()) {
			return ResponseEntity.ok(new DetalhesDoCarroDto(carro.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	//Atualizar valor de um carro
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
	
	//Excluir carro por id
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
	
	// Ordenação e filtros
	
	@GetMapping("/cars")
	public Page<CarroDto> listar(@RequestParam(required = false) String marca,
								 @PageableDefault(sort = "nome", direction = Direction.ASC, page = 0, size = 15) Pageable paginacao) {
		
		if (marca == null) {
			Page<Carro> carros = carroRepository.findAll(paginacao);
			return CarroDto.converter(carros);
		} else {
			Page<Carro> carros = carroRepository.findByMarca(marca, paginacao);
			return CarroDto.converter(carros);
		}
	}
}
