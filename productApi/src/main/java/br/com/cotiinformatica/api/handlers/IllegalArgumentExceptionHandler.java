package br.com.cotiinformatica.api.handlers;

import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IllegalArgumentExceptionHandler {
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponseHandler handleIllegalArgumentException(IllegalArgumentException e) {
		ErrorResponseHandler response = new ErrorResponseHandler();
		response.setStatus(HttpStatus.BAD_REQUEST);
		response.setErrors(new ArrayList<String>());
		response.getErrors().add(e.getMessage());
		return response;
	}
}
