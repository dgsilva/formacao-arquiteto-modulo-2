package br.com.cotiinformatica;

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
import br.com.cotiinformatica.application.dtos.UserAuthRequestDto;
import br.com.cotiinformatica.application.dtos.UserAuthResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserAuthTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	@Order(1)
	public void userAuthSuccessTest() throws Exception {

		//criando os dados da requisição
		UserAuthRequestDto dto = new UserAuthRequestDto();
		dto.setEmail("admin@cotiinformatica.com.br");
		dto.setPassword("@Admin1234");
		
		//fazendo a requisição para o serviço
		MvcResult result = mockMvc.perform(post("/api/users/auth") //endpoint do serviço
				.contentType("application/json") //formato dos dados
				.content(objectMapper.writeValueAsString(dto))) //conteúdo dos dados
				.andExpect(status().isOk()) //verificando a resposta
				.andReturn(); //capturar o retorno da chamada
		
		//deserializar o retorno do serviço (UserAuthResponseDto)
		String content = result.getResponse().getContentAsString();
		UserAuthResponseDto response = objectMapper.readValue(content, UserAuthResponseDto.class);
		
		//verificando se o token foi gerado
		assertNotNull(response.getAccessToken());
		assertNotNull(response.getExpiration());
	}

	@Test
	@Order(2)
	public void userAuthBadRequestTest() throws Exception {

		UserAuthRequestDto dto = new UserAuthRequestDto();
		Faker faker = new Faker();
		
		dto.setEmail(faker.internet().emailAddress());
		dto.setPassword("@Teste1234");
		
		//fazendo a requisição para o serviço
		MvcResult result = mockMvc.perform(post("/api/users/auth") //endpoint do serviço
				.contentType("application/json") //formato dos dados
				.content(objectMapper.writeValueAsString(dto))) //conteúdo dos dados
				.andExpect(status().isBadRequest()) //verificando a resposta
				.andReturn(); //capturar o retorno da chamada
		
		//deserializar o retorno do serviço (UserAuthResponseDto)
		String content = result.getResponse().getContentAsString();
		ErrorResponseDto response = objectMapper.readValue(content, ErrorResponseDto.class);
		
		assertTrue(response.getErrors().get(0).contains("Acesso negado"));
	}
}
