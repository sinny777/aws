/*******************************************************************************
 *
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER. 
 *  Copyright (c) 2015 Jade Global
 *  All Rights Reserved. All content is proprietary and confidential.
 *
 *******************************************************************************/

package com.jadecore.finance.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jadecore.finance.abstraction.repository.CommonRepository;
import com.jadecore.finance.abstraction.service.CommonService;

/*
 * @author gurvinder.singh
 * @Date 24-Apr-2015
 */

@Service
public class CommonServiceImpl implements CommonService {

	private static final Logger logger = LoggerFactory
			.getLogger(CommonServiceImpl.class);

	private final CommonRepository commonRepository;

	@Autowired
	public CommonServiceImpl(CommonRepository commonRepository) {
		this.commonRepository = commonRepository;
	}

	@Override
	public String helloWorld() {
		logger.info("IN CommonServiceImpl.helloWorld :>>>>>>>>>>>>>>  ");
		return commonRepository.helloWorld();
	}

}
