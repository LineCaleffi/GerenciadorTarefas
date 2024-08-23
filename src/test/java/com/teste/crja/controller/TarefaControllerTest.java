package com.teste.crja.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.nio.charset.StandardCharsets;

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
	@Autowired
	private TarefaRepository tRepository;
	
	@Test
	@DisplayName("Listando as tarefas")
	void getAllTest() throws Exception{
		ResultActions response = mockMvc.perform(get("/tarefas").contentType("application/json"));
		MvcResult result = response.andReturn();
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		System.out.println(responseStr);

		ObjectMapper mapper = new ObjectMapper();
		TarefaDTO[] lista = mapper.readValue(responseStr, TarefaDTO[].class);
		assertThat(lista).isNotEmpty();
	}
}
