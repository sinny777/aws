package com.jadecore.aws.abstraction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason="Parameters provided are not correct")
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 2392507780567884288L;

	public BadRequestException() {
		super();
	}

	public BadRequestException(String message) {
		super(message);
	}

}
