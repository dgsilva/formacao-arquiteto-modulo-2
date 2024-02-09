package br.com.cotiinformatica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import br.com.cotiinformatica.application.dtos.ErrorResponseDto;
import br.com.cotiinformatica.application.dtos.UserCreateRequestDto;
import br.com.cotiinformatica.application.dtos.UserCreateResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserCreateTests {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
		
	@Test
	@Order(1)
	public void userCreateSuccessTest() throws Exception {
		
		UserCreateRequestDto request = new UserCreateRequestDto();
		Faker faker = new Faker();
		
		//dados que serão enviados para a API (requisição)
		request.setName(faker.name().fullName());
		request.setEmail(faker.internet().emailAddress());
		request.setPassword("@Teste1234");
		
		//realizando a chamada para a API e verificando a resposta obtida
		MvcResult result = mockMvc.perform(post("/api/users/create") //caminho do endpoint
				.contentType("application/json") //formato dos dados
				.content(objectMapper.writeValueAsString(request))) //serializando os dados
				.andExpect(status().isCreated()) //verificando o status da resposta
				.andReturn();
		
		String content = result.getResponse().getContentAsString();
		UserCreateResponseDto response = objectMapper.readValue(content, UserCreateResponseDto.class);
		
		assertNotNull(response.getId());
		assertEquals(response.getName(), request.getName());
		assertEquals(response.getEmail(), request.getEmail());
	}

	@Test
	@Order(2)
	public void userCreateBadRequestTest() throws Exception {
		
		UserCreateRequestDto request = new UserCreateRequestDto();
		
		//dados que serão enviados para a API (requisição)
		request.setName("Administrador");
		request.setEmail("admin@cotiinformatica.com.br");
		request.setPassword("@Teste1234");
		
		//realizando a chamada para a API e verificando a resposta obtida
		MvcResult result = mockMvc.perform(post("/api/users/create") //caminho do endpoint
				.contentType("application/json") //formato dos dados
				.content(objectMapper.writeValueAsString(request))) //serializando os dados
				.andExpect(status().isBadRequest()) //verificando o status da resposta
				.andReturn();
		
		String content = result.getResponse().getContentAsString();
		ErrorResponseDto response = objectMapper.readValue(content, ErrorResponseDto.class);
		
		assertTrue(response.getErrors().get(0).contains("O email informado ja esta cadastrado"));
	}

}
