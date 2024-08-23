package com.teste.crja.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import com.teste.crja.dto.PessoaDTO;
import com.teste.crja.entity.PessoaEntity;
import com.teste.crja.repositories.PessoaRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Pessoa Controller Test")
public class PessoaControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private PessoaRepository pRepository;

	@Test
	void getAllTest() throws Exception {
		ResultActions response = mockMvc.perform(get("/pessoa").contentType("application/json"));
		MvcResult result = response.andReturn();
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		System.out.println(responseStr);

		ObjectMapper mapper = new ObjectMapper();
		PessoaDTO[] lista = mapper.readValue(responseStr, PessoaDTO[].class);
		assertThat(lista).isNotEmpty();
	}

	@Test
	@DisplayName("Criando e salvando pessoa")
	void saveTest() throws Exception {
		PessoaEntity pessoa = createDep();
		PessoaEntity pessoaSave = this.pRepository.save(pessoa);

		Assertions.assertThat(pessoaSave.getId()).isPositive();
		Assertions.assertThat(pessoaSave.getName()).isEqualTo(pessoa.getName());
		Assertions.assertThat(pessoaSave.getDepartamento()).isEqualTo(pessoa.getDepartamento());
		Assertions.assertThat(pessoaSave.getTarefas()).isEqualTo(pessoa.getTarefas());
	}

	@Test
	@DisplayName("Deletando Pessoa do BD")
	void deleteTest() throws Exception {
		ResultActions response = mockMvc.perform(delete("/pessoa/6").contentType("application/json"));
		MvcResult result = response.andReturn();

		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}

	private PessoaEntity createDep() {
		return PessoaEntity.builder().name("Aline").build();
	}
}
