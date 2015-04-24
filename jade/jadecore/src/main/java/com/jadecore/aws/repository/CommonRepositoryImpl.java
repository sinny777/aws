/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER. 
 * Copyright (c) 2014 Kinetic Networks, Inc. 
 * All Rights Reserved. All content is proprietary and confidential.
 */

package com.jadecore.aws.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jadecore.aws.abstraction.repository.CommonRepository;

/*
 * @author gurvinder.singh
 * @Date 24-Apr-2015
 */

@Repository
public class CommonRepositoryImpl implements CommonRepository {

	private static final Logger logger = LoggerFactory
			.getLogger(CommonRepositoryImpl.class);

	@Override
	public String helloWorld() {
		return "Hello World from Repository";
	}

}
