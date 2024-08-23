package com.teste.crja.mapper;

import org.springframework.stereotype.Component;

import com.teste.crja.dto.AlocarPessoaTarefa;
import com.teste.crja.dto.TarefaConcluidaDTO;
import com.teste.crja.dto.TarefaDTO;
import com.teste.crja.entity.DepartamentoEntity;
import com.teste.crja.entity.PessoaEntity;
import com.teste.crja.entity.TarefaEntity;

@Component
public class TarefaMapper {
	public TarefaDTO toDTO(TarefaEntity tarefa) {
		if (tarefa == null) {
			return null;
		}

		if (tarefa.getPessoa() != null) {
			var pessoa = new PessoaEntity();
			pessoa.setId(tarefa.getPessoa().getId());
			pessoa.setName(tarefa.getPessoa().getName());
			tarefa.setPessoa(pessoa);
		} else {
			tarefa.setPessoa(null);
		}

		if (tarefa.getDepartamento() != null) {
			var departamento = new DepartamentoEntity();
			departamento.setId(tarefa.getDepartamento().getId());
			departamento.setTitulo(tarefa.getDepartamento().getTitulo());
			tarefa.setDepartamento(departamento);
		} else {
			tarefa.setDepartamento(null);
		}

		return new TarefaDTO(0, tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getPrazo(), tarefa.getDepartamento(),
				tarefa.getDuracaoTarefa(), tarefa.getPessoa(), tarefa.getFinalizado());
	}
	public AlocarPessoaTarefa alocaPessoaTarefaToDTO(TarefaEntity tarefa) {
        return new AlocarPessoaTarefa(tarefa, tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getPrazo(), tarefa.getDepartamento().getId(), tarefa.getPessoa().getId());
    }
	
    public TarefaConcluidaDTO toTarefaConcluidaDTO(TarefaEntity tarefa){
        return new TarefaConcluidaDTO(tarefa, tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getPrazo(), tarefa.getDepartamento().getTitulo(), tarefa.getPessoa().getName(), tarefa.getFinalizado());
    }
}
