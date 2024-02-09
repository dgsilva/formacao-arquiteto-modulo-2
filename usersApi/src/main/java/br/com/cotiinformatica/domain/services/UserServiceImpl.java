package br.com.cotiinformatica.domain.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cotiinformatica.application.dtos.UserMessageDto;
import br.com.cotiinformatica.domain.entities.User;
import br.com.cotiinformatica.domain.exceptions.UserException;
import br.com.cotiinformatica.domain.interfaces.UserService;
import br.com.cotiinformatica.infrastructure.components.JwtTokenComponent;
import br.com.cotiinformatica.infrastructure.components.Sha1Component;
import br.com.cotiinformatica.infrastructure.producers.UserMessageProducer;
import br.com.cotiinformatica.infrastructure.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired UserRepository userRepository;
	@Autowired Sha1Component sha1Component;
	@Autowired JwtTokenComponent jwtTokenComponent;
	@Autowired ObjectMapper objectMapper;
	@Autowired UserMessageProducer userMessageProducer;
	
	@Override
	public User create(User user) throws UserException {

		user.setId(UUID.randomUUID());
		user.setPassword(sha1Component.sha1Hash(user.getPassword()));
		
		if(userRepository.find(user.getEmail()) != null)
			throw new UserException("O email informado ja esta cadastrado.");
		
		userRepository.save(user);			
		createWelcomeMessage(user);
		
		return user;
	}

	@Override
	public User authenticate(String email, String password) throws UserException {

		User user = userRepository.find(email, sha1Component.sha1Hash(password));
		
		if(user == null)
			throw new UserException("Acesso negado. Usuario invalido");
		
		user.setAccessToken(jwtTokenComponent.generateToken(email));
		user.setExpiration(jwtTokenComponent.getExpiration());
		
		return user;
	}
	
	/*
	 * Método para criar a mensagem de boas vindas
	 * que o sistema deverá enviar para o usuário
	 */
	private void createWelcomeMessage(User user) {
		
		UserMessageDto dto = new UserMessageDto();
		dto.setEmailTo(user.getEmail());
		dto.setSubject("Conta criada com sucesso - API de usuários.");
		
		StringBuilder sb = new StringBuilder();
		sb.append("<div>");
		sb.append("<p>Parabéns " + user.getName() + ", sua conta de usuário foi criada com sucesso</p>");
		sb.append("<p>Att,</p>");
		sb.append("<p>Equipe COTI Informática,</p>");
		sb.append("</div>");
		
		dto.setBody(sb.toString());
		
		try {
			String message = objectMapper.writeValueAsString(dto);
			userMessageProducer.sendMessage(message);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
