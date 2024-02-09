package br.com.cotiinformatica.application.controllers;

import java.time.Instant;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.application.dtos.UserAuthRequestDto;
import br.com.cotiinformatica.application.dtos.UserAuthResponseDto;
import br.com.cotiinformatica.application.dtos.UserCreateRequestDto;
import br.com.cotiinformatica.application.dtos.UserCreateResponseDto;
import br.com.cotiinformatica.domain.entities.User;
import br.com.cotiinformatica.domain.exceptions.UserException;
import br.com.cotiinformatica.domain.interfaces.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/users")
public class UsersController {

	@Autowired
	UserService userService;

	@Autowired
	ModelMapper modelMapper;

	@PostMapping("create")
	public ResponseEntity<UserCreateResponseDto> create(@RequestBody @Valid UserCreateRequestDto dto)
			throws UserException {

		User user = modelMapper.map(dto, User.class);
		userService.create(user);

		UserCreateResponseDto response = modelMapper.map(user, UserCreateResponseDto.class);
		response.setCreatedAt(Instant.now());

		return ResponseEntity.status(201).body(response);
	}

	@PostMapping("auth")
	public ResponseEntity<UserAuthResponseDto> auth(@RequestBody @Valid UserAuthRequestDto dto) throws UserException {

		User user = userService.authenticate(dto.getEmail(), dto.getPassword());

		UserAuthResponseDto response = modelMapper.map(user, UserAuthResponseDto.class);
		return ResponseEntity.status(200).body(response);
	}
}
