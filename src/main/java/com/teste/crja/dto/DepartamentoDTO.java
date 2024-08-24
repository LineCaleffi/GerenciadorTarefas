/*
 *  Nome: Aline Caleffi
 *  Teste de backend da empresa CRJA
 */
package com.teste.crja.dto;

import java.util.List;

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
public class DepartamentoDTO {
	private int id;
	private String titulo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "idDepartamento")
	private List<PessoaEntity> pessoa;
	
	@JsonIgnore
	@OneToMany (mappedBy = "idDepartamento")
	private List<TarefaEntity> tarefa;

	public DepartamentoEntity toEntity() {
		ModelMapper mapper = new ModelMapper();

		return mapper.map(this, DepartamentoEntity.class);
	}
}
