/*
 *  Nome: Aline Caleffi
 *  Teste de backend da empresa CRJA
 */
package com.teste.crja.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.teste.crja.entity.TarefaEntity;

@Repository
public interface TarefaRepository extends JpaRepository<TarefaEntity, Integer> {
	public List<TarefaEntity> findAllByTitulo(String titulo);

	@Query(value = "SELECT * FROM tarefas WHERE tarefas.id_pessoa is null "
			+ "ORDER BY tarefas.prazo ASC", nativeQuery = true)
	List<TarefaEntity> tarefasAntigas(Pageable page);
}
