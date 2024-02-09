package br.com.cotiinformatica.infrastructure.repositories;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.cotiinformatica.domain.entities.User;
import br.com.cotiinformatica.infrastructure.components.Sha1Component;

@Component
public class LoadData implements ApplicationRunner {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Sha1Component sha1Component;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		String email = "admin@cotiinformatica.com.br";
		
		User user = userRepository.find(email);
		if(user == null) {
			user = new User();
			user.setId(UUID.randomUUID());	
			user.setEmail(email);
		}
		
		user.setName("Administrador");		
		user.setPassword(sha1Component.sha1Hash("@Admin1234"));
		
		userRepository.save(user);
	}
}
