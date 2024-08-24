package com.teste.crja.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaGastosDTO {
	private int id;
    private String name;
    private double media_duracao;
    
    public PessoaGastosDTO(int id, String name, Double media_duracao) {
        this.id = id;
        this.name = name;
        this.media_duracao = media_duracao == null ? 0 : media_duracao;
    }
}
