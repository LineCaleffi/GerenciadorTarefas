package com.teste.crja.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;

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
import com.teste.crja.dto.TarefaDTO;
import com.teste.crja.repositories.TarefaRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("TarefaControllerTest")
public class TarefaControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Listando as tarefas")
	void getAllTest() throws Exception {
		ResultActions response = mockMvc.perform(get("/tarefas").contentType("application/json"));
		MvcResult result = response.andReturn();
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		System.out.println(responseStr);

		ObjectMapper mapper = new ObjectMapper();
		TarefaDTO[] lista = mapper.readValue(responseStr, TarefaDTO[].class);
		assertThat(lista).isNotEmpty();
	}

	@Test
	@DisplayName("Salvando e criando Tarefa")
	void saveTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		TarefaDTO tarefa = new TarefaDTO();
		tarefa.setTitulo("SaveTest");
		tarefa.setDescricao("SaveTest descrição");
		tarefa.setDuracaoTarefa(222);
		// tarefa.setPrazo("2024-10-12");
		//tarefa.setDepartamento(1);
		tarefa.setFinalizado(false);

		ResultActions response = mockMvc.perform(post("/tarefas").content(mapper.writeValueAsString(tarefa)).contentType("application/json"));
		MvcResult result = response.andReturn();
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		System.out.println(responseStr);

		TarefaDTO tarefaDto = mapper.readValue(responseStr, TarefaDTO.class);

		assertThat(tarefaDto.getId()).isPositive();
		assertThat(tarefaDto.getTitulo()).isEqualTo(tarefa.getTitulo());
		assertThat(tarefaDto.getDescricao()).isEqualTo(tarefa.getDescricao());
		assertThat(tarefaDto.getDuracaoTarefa()).isEqualTo(tarefa.getDuracaoTarefa());
		assertThat(tarefaDto.getDepartamento()).isEqualTo(tarefa.getDepartamento());
		assertThat(tarefaDto.getFinalizado()).isEqualTo(tarefa.getFinalizado());

		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}

}
