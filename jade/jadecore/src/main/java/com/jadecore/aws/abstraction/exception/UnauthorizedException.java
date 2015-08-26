/*******************************************************************************
 *
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER. 
 *  Copyright (c) 2015 Jade Global
 *  All Rights Reserved. All content is proprietary and confidential.
 *
 *******************************************************************************/
package com.jadecore.aws.abstraction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED,reason="Parameters provided are not correct")
public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 2392507780567884288L;

	public UnauthorizedException() {
		super();
	}

	public UnauthorizedException(String message) {
		super(message);
	}

}
