package com.teste.crja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.crja.dto.PessoaDTO;
import com.teste.crja.entity.PessoaEntity;
import com.teste.crja.horas.PessoaHorasDTO;
import com.teste.crja.service.PessoaService;

@RestController
@RequestMapping("pessoa")
public class PessoaController {
	@Autowired
	private PessoaService pessoaService;

	@GetMapping
	public List<PessoaEntity> getAll() {
		return pessoaService.getAll();
	}

	@GetMapping("{id}")
	public PessoaEntity getOne(@PathVariable Integer id) {
		return pessoaService.getOne(id);
	}
	
	// Listar pessoas trazendo nome, departamento, total horas gastas nas tarefas
	@GetMapping("get/pessoas")
	public ResponseEntity<List<PessoaHorasDTO>> listarPessoas() {
		return ResponseEntity.ok(pessoaService.listarPessoas());
	}

	@PostMapping()
	public ResponseEntity<PessoaDTO> save(@RequestBody PessoaDTO pessoa) {
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(pessoa.toEntity()));
	}

	@DeleteMapping(value = "{id}")
	public void delete(@PathVariable int id) {
		pessoaService.delete(id);
	}

	@PatchMapping(value = "{id}")
	public ResponseEntity<PessoaDTO> update(@PathVariable int id, @RequestBody PessoaDTO pessoa) {
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.update(id, pessoa.toEntity()));
	}
}
