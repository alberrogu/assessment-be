package com.ista.isp.assessment.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {

	/** The default serial version UID */
	private static final long serialVersionUID = 1L;

	private String message;

	public ValidationException(String message) {
		super();
		setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
