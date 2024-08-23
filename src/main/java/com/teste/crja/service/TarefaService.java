package com.teste.crja.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.crja.dto.AlocarPessoaTarefa;
import com.teste.crja.dto.TarefaConcluidaDTO;
import com.teste.crja.dto.TarefaDTO;
import com.teste.crja.entity.TarefaEntity;
import com.teste.crja.mapper.TarefaMapper;
import com.teste.crja.repositories.PessoaRepository;
import com.teste.crja.repositories.TarefaRepository;

@Service
public class TarefaService {
	@Autowired
	private TarefaRepository tarefaRepository;
	@Autowired
	private PessoaRepository pessoaRepository;
    @Autowired
    private TarefaMapper tarefaMapper;
    
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
		
		if(tarefaBD.isPresent()) {
			TarefaEntity tarefa = tarefaBD.get();
			
			tarefa.setTitulo(newTarefa.getTitulo());
			tarefa.setDescricao(newTarefa.getDescricao());
			tarefa.setPrazo(newTarefa.getPrazo());
			tarefa.setDepartamento(newTarefa.getDepartamento());
			tarefa.setDuracaoTarefa(newTarefa.getDuracaoTarefa());
			tarefa.setPessoa(newTarefa.getPessoa());
			tarefa.setFinalizado(newTarefa.getFinalizado());
			
			return tarefaRepository.save(tarefa).toDTO();
		}else {
			return new TarefaEntity().toDTO();
		}
	}
	
	//Alocar pessoas
	public TarefaDTO alocarPessoaNaTarefa(int id, AlocarPessoaTarefa alocaPessoa) {
		if(!tarefaRepository.existsById(id)){
			throw new ValidationException("Id da tarefa informada não exite");
		}
		
		if(!pessoaRepository.existsById(alocaPessoa.idPessoa())) {
			throw new ValidationException("Id da pessoa informada não existe");
		}
		
		var pessoa = pessoaRepository.findById(alocaPessoa.idPessoa()).get();
		var tarefa = tarefaRepository.findById(id).get();
		
		if(pessoa.getDepartamento().getId() != tarefa.getDepartamento().getId()) {
			throw new ValidationException("O departamento da tarefa não é o mesmo departamento da pessoa selecionada.");
		}
		
		tarefa.setPessoa(pessoa);
		
		return tarefaMapper.toDTO(tarefaRepository.save(tarefa));
	}
	


}
