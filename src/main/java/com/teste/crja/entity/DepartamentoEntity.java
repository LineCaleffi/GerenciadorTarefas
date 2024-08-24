/*
 *  Nome: Aline Caleffi
 *  Teste de backend da empresa CRJA
 */
package com.teste.crja.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teste.crja.dto.DepartamentoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "departamento")
public class DepartamentoEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	private String titulo;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"idDepartamento", "tarefa"})
	private List<PessoaEntity> pessoa;
	
	@JsonIgnore
	@OneToMany (cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"idDepartamento", "pessoa"})
	private List<TarefaEntity> tarefa;
	public DepartamentoDTO toDTO() {
		ModelMapper mapper = new ModelMapper();
		
		DepartamentoDTO dto = mapper.map(this, DepartamentoDTO.class);
		
		return dto;
	}
}
