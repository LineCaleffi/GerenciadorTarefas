package com.teste.crja.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.crja.dto.DepartamentoDTO;
import com.teste.crja.entity.DepartamentoEntity;
import com.teste.crja.repositories.DepartamentoRepository;

@Service
public class DepartamentoService {
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	public List<DepartamentoDTO> getAll(){
		List<DepartamentoEntity> list = departamentoRepository.findAll();
		List<DepartamentoDTO> listDTO = new ArrayList<>();
		for (DepartamentoEntity departamento : list) {
			listDTO.add(departamento.toDTO());
		}
		
		return listDTO;
	}
	
	public DepartamentoDTO save(DepartamentoEntity departamento) {
		return departamentoRepository.save(departamento).toDTO();
	}
}
