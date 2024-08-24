/*
 *  Nome: Aline Caleffi
 *  Teste de backend da empresa CRJA
 */
package com.teste.crja.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.teste.crja.dto.TarefaDTO;
import com.teste.crja.entity.PessoaEntity;
import com.teste.crja.entity.TarefaEntity;
import com.teste.crja.repositories.PessoaRepository;
import com.teste.crja.repositories.TarefaRepository;

@Service
public class TarefaService {
	@Autowired
	private TarefaRepository tarefaRepository;
	@Autowired
	private PessoaRepository pessoaRepository;

	public List<TarefaDTO> getAll() {
		List<TarefaEntity> list = tarefaRepository.findAll();
		List<TarefaDTO> listDTO = new ArrayList<>();
		for (TarefaEntity tarefa : list) {
			listDTO.add(tarefa.toDTO());
		}

		return listDTO;
	}

	public TarefaDTO save(TarefaEntity tarefa) {
		return tarefaRepository.save(tarefa).toDTO();
	}

	public TarefaDTO update(int id, TarefaEntity newTarefa) {
		Optional<TarefaEntity> tarefaBD = tarefaRepository.findById(id);

		if (tarefaBD.isPresent()) {
			TarefaEntity tarefa = tarefaBD.get();

			tarefa.setTitulo(newTarefa.getTitulo());
			tarefa.setDescricao(newTarefa.getDescricao());
			tarefa.setPrazo(newTarefa.getPrazo());
			tarefa.setDepartamento(newTarefa.getDepartamento());
			tarefa.setDuracaoTarefa(newTarefa.getDuracaoTarefa());
			tarefa.setPessoa(newTarefa.getPessoa());
			tarefa.setFinalizado(newTarefa.getFinalizado());

			return tarefaRepository.save(tarefa).toDTO();
		} else {
			return new TarefaEntity().toDTO();
		}
	}

	// Alocar pessoas
	public Optional<TarefaDTO> alocarPessoaTarefa(@RequestBody TarefaDTO alocarDTO, int id) {
		TarefaEntity tarefa = tarefaRepository.getById(alocarDTO.getId());
		PessoaEntity pessoa = pessoaRepository.getById(id);

		if (tarefaRepository.findById(id).isPresent() && pessoaRepository.findById(id).isPresent()
				&& tarefa.getDepartamento() == pessoa.getDepartamento()) {
			tarefa.setPessoa(pessoa);
			tarefaRepository.save(tarefa);
			return Optional.of(alocarDTO);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Possiveis erros: Departamentos diferentes ou não encontrado pessoa/tarefa!!");
		}
	}

	// Finalizar tarefa
	public Optional<Integer> finalizarTarefa(int finalizarTarefa) {
		if (tarefaRepository.findById(finalizarTarefa).isPresent()) {
			TarefaEntity tarefa = tarefaRepository.getById(finalizarTarefa);
			tarefa.setFinalizado(true);
			tarefaRepository.save(tarefa);
			return Optional.of(finalizarTarefa);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID da tarefa não localizado!");
		}
	}

	// Tarefas sem pessoas alocadas
	public List<TarefaEntity> getTarefasSemPessoas() {
		List<TarefaEntity> tarefas = tarefaRepository.tarefasAntigas(PageRequest.of(0, 3));
		tarefas.sort(new Comparator<TarefaEntity>() {
			public int compare(TarefaEntity a, TarefaEntity b) {
				LocalDate d1 = a.getPrazo();
				LocalDate d2 = b.getPrazo();
				if (d1.isBefore(d2)) {
					return -1;
				} else if (d1.isEqual(d2)) {
					return 0;
				} else {
					return 1;
				}
			}
		});
		
		return tarefas;
	}

	public void delete(int id) {
		tarefaRepository.deleteById(id);
	}

}
