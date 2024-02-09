package br.com.cotiinformatica.domain.interfaces;

import br.com.cotiinformatica.domain.entities.User;
import br.com.cotiinformatica.domain.exceptions.UserException;

public interface UserService {

	User create(User user) throws UserException;
	User authenticate(String email, String password) throws UserException;
}
