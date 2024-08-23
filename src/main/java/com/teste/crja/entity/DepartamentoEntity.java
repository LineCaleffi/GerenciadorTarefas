package com.teste.crja.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

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
	
	public DepartamentoDTO toDTO() {
		ModelMapper mapper = new ModelMapper();
		
		DepartamentoDTO dto = mapper.map(this, DepartamentoDTO.class);
		
		return dto;
	}
}
