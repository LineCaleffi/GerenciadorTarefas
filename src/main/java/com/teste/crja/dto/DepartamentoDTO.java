/*
 *  Nome: Aline Caleffi
 *  Teste de backend da empresa CRJA
 */
package com.teste.crja.dto;

import org.modelmapper.ModelMapper;

import com.teste.crja.entity.DepartamentoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoDTO {
	private int id;

	private String titulo;

	public DepartamentoEntity toEntity() {
		ModelMapper mapper = new ModelMapper();

		return mapper.map(this, DepartamentoEntity.class);
	}
}
