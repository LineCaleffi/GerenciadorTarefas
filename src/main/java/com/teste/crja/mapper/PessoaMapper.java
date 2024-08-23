package com.teste.crja.mapper;

import org.springframework.stereotype.Component;

import com.teste.crja.dto.PessoaDTO;
import com.teste.crja.entity.DepartamentoEntity;
import com.teste.crja.entity.PessoaEntity;

@Component
public class PessoaMapper {
    public PessoaDTO toDTO(PessoaEntity pessoa){
        if (pessoa == null){
            return null;
        }
        return new PessoaDTO(
                pessoa.getId(),
                pessoa.getName(),
                pessoa.getDepartamento(),
                pessoa.getTarefas()
        );
    }
    
    public PessoaEntity toModel(PessoaDTO pessoaDTO){
        if (pessoaDTO == null){
            return null;
        }

        PessoaEntity pessoa = new PessoaEntity();

        if (pessoaDTO.getTarefas() != null) {
            pessoa.setTarefas(pessoaDTO.getTarefas());
        }

        pessoa.setName(pessoaDTO.getName());

        var departamento = new DepartamentoEntity();
        departamento.setId(pessoaDTO.getDepartamento().getId());

        pessoa.setDepartamento(departamento);

        return pessoa;
    }
}
