/*
 *  Nome: Aline Caleffi
 *  Teste de backend da empresa CRJA
 */
package com.teste.crja.dto;

import java.util.stream.Collectors;

import com.teste.crja.entity.DepartamentoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoCountDTO {
	private String titulo;
	private Long pessoas;
	private Long tarefas;

	public DepartamentoCountDTO(DepartamentoEntity departamento) {
		this.titulo = departamento.getTitulo();
		this.pessoas = departamento.getPessoa().stream().collect(Collectors.counting());
		this.tarefas = departamento.getTarefa().stream().collect(Collectors.counting());
	}

}
