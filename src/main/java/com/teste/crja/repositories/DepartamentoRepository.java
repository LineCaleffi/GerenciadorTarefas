package com.teste.crja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.crja.entity.DepartamentoEntity;

@Repository
public interface DepartamentoRepository extends JpaRepository<DepartamentoEntity, Integer>{

}
