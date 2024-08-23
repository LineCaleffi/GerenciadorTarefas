package com.teste.crja.dto;

import java.time.LocalDate;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.teste.crja.entity.DepartamentoEntity;
import com.teste.crja.entity.PessoaEntity;
import com.teste.crja.entity.TarefaEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarefaDTO {
	private int id;

	@NotBlank(message="Titulo da tarefa não pode ser vazio!")
	private String titulo;
	
	@NotBlank(message="A descrição da tarefa não pode ser vazia!")
	private String descricao;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate prazo;
	
	@ManyToOne
	private DepartamentoEntity departamento;

	private int duracaoTarefa;
	
	@ManyToOne
	private PessoaEntity pessoa; // pessoa alocada
	
	private Boolean finalizado = false;
	

	public TarefaEntity toEntity() {
		ModelMapper mapper = new ModelMapper();

		return mapper.map(this, TarefaEntity.class);
	}
}
