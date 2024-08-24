/*
 *  Nome: Aline Caleffi
 *  Teste de backend da empresa CRJA
 */
package com.teste.crja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.crja.entity.PessoaEntity;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, Integer> {
	
}
