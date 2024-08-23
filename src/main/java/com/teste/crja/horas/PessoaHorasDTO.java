package com.teste.crja.horas;

import java.util.stream.Collectors;

import com.teste.crja.entity.PessoaEntity;
import com.teste.crja.entity.TarefaEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaHorasDTO {
	private String name;
	private String departamento;
	private int horasTarefas;
	
	public PessoaHorasDTO(PessoaEntity pessoa) {
		super();
		this.name = pessoa.getName();
		this.departamento = pessoa.getDepartamento().getTitulo();
		this.horasTarefas = pessoa.getTarefas().stream().collect(Collectors.summingInt(TarefaEntity::getDuracaoTarefa));
	}
}
