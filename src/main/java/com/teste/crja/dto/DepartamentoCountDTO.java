/*
 *  Nome: Aline Caleffi
 *  Teste de backend da empresa CRJA
 */
package com.teste.crja.dto;

import com.teste.crja.entity.DepartamentoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoCountDTO {
	private int id;
	private String titulo;
	private int pessoas;
	private int tarefas;

	public DepartamentoCountDTO(DepartamentoEntity departamento) {
		this.id = departamento.getId();
		this.titulo = departamento.getTitulo();
		this.pessoas = departamento.getPessoa().size();
		this.tarefas = departamento.getTarefa().size();
	}
}
