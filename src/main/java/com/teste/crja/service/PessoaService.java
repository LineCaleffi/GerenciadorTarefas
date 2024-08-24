/*
 *  Nome: Aline Caleffi
 *  Teste de backend da empresa CRJA
 */
package com.teste.crja.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.crja.dto.PessoaDTO;
import com.teste.crja.entity.PessoaEntity;
import com.teste.crja.horas.PessoaHorasDTO;
import com.teste.crja.repositories.PessoaRepository;

@Service
public class PessoaService {
	@Autowired
	private PessoaRepository pessoaRep;

	public List<PessoaEntity> getAll() {
		return pessoaRep.findAll();
	}

	public PessoaDTO save(PessoaEntity pessoa) {
		return pessoaRep.save(pessoa).toDTO();
	}

	public void delete(int id) {
		pessoaRep.deleteById(id);
	}

	public PessoaDTO update(int id, PessoaEntity newPessoa) {
		Optional<PessoaEntity> pessoaBD = pessoaRep.findById(id);

		if (pessoaBD.isPresent()) {
			PessoaEntity obj = pessoaBD.get();

			obj.setName(newPessoa.getName());
			obj.setDepartamento(newPessoa.getDepartamento());
			obj.setTarefas(newPessoa.getTarefas());

			return pessoaRep.save(obj).toDTO();
		} else {
			return new PessoaEntity().toDTO();
		}
	}

	public PessoaEntity getOne(int id) {
		Optional<PessoaEntity> optional = pessoaRep.findById(id);
		PessoaEntity pessoa = optional.orElse(new PessoaEntity());
		return pessoa;
	}
	
	// Listar pessoas trazendo nome, departamento, total horas gastas nas tarefas
	public List<PessoaHorasDTO> listarPessoas() {
		List<PessoaHorasDTO> PessoaHorasDTO = pessoaRep.findAll().stream().map(x -> new PessoaHorasDTO(x))
				.collect(Collectors.toList());
		return PessoaHorasDTO;
	}

}
