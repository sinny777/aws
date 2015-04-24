package com.jadecore.aws.abstraction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason="No results found for this request")
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2392507780567884288L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message) {
		super(message);
	}

}
