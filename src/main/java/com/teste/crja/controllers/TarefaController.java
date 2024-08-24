/*
 *  Nome: Aline Caleffi
 *  Teste de backend da empresa CRJA
 */
package com.teste.crja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.crja.dto.TarefaDTO;
import com.teste.crja.entity.TarefaEntity;
import com.teste.crja.service.TarefaService;

@RestController
@RequestMapping("tarefas")
public class TarefaController {
	@Autowired
	private TarefaService tarefaService;

	@GetMapping
	public ResponseEntity<List<TarefaDTO>> getAll() {
		return ResponseEntity.ok().body(tarefaService.getAll());
	}

	@PostMapping
	public ResponseEntity<TarefaDTO> save(@RequestBody TarefaDTO tarefa) {
		return ResponseEntity.ok().body(tarefaService.save(tarefa.toEntity()));
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<TarefaDTO> update(@PathVariable int id, @RequestBody TarefaDTO tarefa) {
		return ResponseEntity.ok().body(tarefaService.update(id, tarefa.toEntity()));
	}
	// Pesquisar tarefas sem pessoas alocadas
	@GetMapping("/pendentes")
	public ResponseEntity<List<TarefaEntity>> getTarefasSemPessoas(){
		return ResponseEntity.ok().body(tarefaService.getTarefasSemPessoas());
	}

	// Alocar uma pessoa na tarefa que tenha o mesmo departamento
	@PutMapping("put/alocar/{id}")
	public ResponseEntity<TarefaDTO> alocarPessoaTarefa(@PathVariable int id, @RequestBody TarefaDTO tarefa) {
		return tarefaService.alocarPessoaTarefa(tarefa, id).map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	//Finalizar tarefa
	@PutMapping("put/finalizar/{id}")
	public ResponseEntity<Integer> finalizarTarefa(@PathVariable int id) {
		return tarefaService.finalizarTarefa(id).map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable int id) {
		tarefaService.delete(id);
	}
}
