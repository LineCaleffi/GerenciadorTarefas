/*
 *  Nome: Aline Caleffi
 *  Teste de backend da empresa CRJA
 */
package com.teste.crja.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teste.crja.dto.DepartamentoCountDTO;
import com.teste.crja.dto.DepartamentoDTO;
import com.teste.crja.entity.DepartamentoEntity;
import com.teste.crja.repositories.DepartamentoRepository;
import com.teste.crja.service.DepartamentoService;

@RestController
@RequestMapping("departamentos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class DepartamentoController {
	@Autowired
	private DepartamentoService depService;
	@Autowired
	private DepartamentoRepository depRepository;
	
	@GetMapping
	public ResponseEntity<List<DepartamentoDTO>> getAll(){
		return ResponseEntity.ok().body(depService.getAll());
	}
	
	@PostMapping
	public ResponseEntity<DepartamentoDTO> save(@RequestBody DepartamentoDTO departamento){
        return ResponseEntity.ok().body(depService.save(departamento.toEntity()));
	}
	
	// Listar departamento e quantidade de pessoas e tarefas	
	@GetMapping("get/departamentos")
	public ResponseEntity<List<DepartamentoCountDTO>> listarDepartamentos(){
		List<DepartamentoCountDTO> lista = new ArrayList<DepartamentoCountDTO>();
		List<DepartamentoEntity> cadastrados = depRepository.findAll();
		cadastrados.stream().forEach(dep -> {
			DepartamentoCountDTO temp = new DepartamentoCountDTO(dep);
			lista.add(temp);
		});
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
}
