/*
 *  Nome: Aline Caleffi
 *  Teste de backend da empresa CRJA
 */
package com.teste.crja.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teste.crja.dto.PessoaDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity(name = "Pessoa")
@Table(name = "pessoas")
public class PessoaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	@JoinColumn(name = "departamento_id")
	@ManyToOne
	private DepartamentoEntity departamento;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pessoa")
	private List<TarefaEntity> tarefas;

	public PessoaDTO toDTO() {
		ModelMapper mapper = new ModelMapper();
		
		PessoaDTO dto = mapper.map(this, PessoaDTO.class);
		
		return dto;
	}

}
