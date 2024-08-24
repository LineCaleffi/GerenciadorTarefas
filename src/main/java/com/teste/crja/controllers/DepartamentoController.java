/*
 *  Nome: Aline Caleffi
 *  Teste de backend da empresa CRJA
 */
package com.teste.crja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teste.crja.dto.DepartamentoCountDTO;
import com.teste.crja.dto.DepartamentoDTO;
import com.teste.crja.service.DepartamentoService;

@RestController
@RequestMapping("departamentos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class DepartamentoController {
	@Autowired
	private DepartamentoService depService;
	
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
	public ResponseEntity<List<DepartamentoCountDTO>> listarDepartamento(){
		return ResponseEntity.ok(depService.listarDepartamento());
	}
}
