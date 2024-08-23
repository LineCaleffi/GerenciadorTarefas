package com.teste.crja.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.nio.charset.StandardCharsets;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.crja.dto.DepartamentoDTO;
import com.teste.crja.entity.DepartamentoEntity;
import com.teste.crja.repositories.DepartamentoRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("DepartamentoController")
public class DepartamentoController {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private DepartamentoRepository depRepository;

	@Test
	@DisplayName("Listando departamentos")
	void getAllTest() throws Exception {
		ResultActions response = mockMvc.perform(get("/departamentos").contentType("application/json"));
		MvcResult result = response.andReturn();
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		System.out.println(responseStr);

		ObjectMapper mapper = new ObjectMapper();
		DepartamentoDTO[] lista = mapper.readValue(responseStr, DepartamentoDTO[].class);
		assertThat(lista).isNotEmpty();
	}

	@Test
	@DisplayName("Criando e salvando departamento")
	void saveTest() throws Exception {
		DepartamentoEntity dep = createDep();
		DepartamentoEntity depSave = this.depRepository.save(dep);
		
		Assertions.assertThat(depSave.getId()).isPositive();
		Assertions.assertThat(depSave.getTitulo()).isEqualTo(dep.getTitulo());
	}
	
	private DepartamentoEntity createDep() {
		return DepartamentoEntity.builder().titulo("Administração").build();
	}
}
