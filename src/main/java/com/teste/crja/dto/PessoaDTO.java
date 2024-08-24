/*
 *  Nome: Aline Caleffi
 *  Teste de backend da empresa CRJA
 */
package com.teste.crja.dto;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teste.crja.entity.DepartamentoEntity;
import com.teste.crja.entity.PessoaEntity;
import com.teste.crja.entity.TarefaEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {
	private int id;
	private String name;
	
	@JoinColumn(name = "departamento_id")
	@ManyToOne
	private DepartamentoEntity departamento;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pessoa")
	private List<TarefaEntity> tarefas;

	public PessoaEntity toEntity() {
		ModelMapper mapper = new ModelMapper();

		return mapper.map(this, PessoaEntity.class);
	}

}
