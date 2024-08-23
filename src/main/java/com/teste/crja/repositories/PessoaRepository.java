package com.teste.crja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.crja.entity.PessoaEntity;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, Integer> {
	//public List<PessoaEntity> findAllByNome(String name);

	//public List<PessoaEntity> findAllByNomeContainingIgnoreCase(String name);
}
