/*******************************************************************************
 *
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER. 
 *  Copyright (c) 2015 Jade Global
 *  All Rights Reserved. All content is proprietary and confidential.
 *
 *******************************************************************************/

package com.jadecore.finance.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jadecore.finance.abstraction.exception.BadRequestException;
import com.jadecore.finance.abstraction.exception.NotFoundException;
import com.jadecore.finance.abstraction.model.MyUser;
import com.jadecore.finance.abstraction.repository.MyUserRepository;
import com.jadecore.finance.abstraction.service.MyUserService;

/*
 * @author gurvinder.singh
 * @Date 09-Sep-2015
 */

@Service
public class MyUserServiceImpl implements MyUserService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MyUserServiceImpl.class);

	private final MyUserRepository userRespository;

	@Autowired
	public MyUserServiceImpl(MyUserRepository userRespository) {
		this.userRespository = userRespository;
	}

	@Override
	public List<MyUser> list() throws NotFoundException {
		LOGGER.info("IN MyUserServiceImpl.list >>>>>>>>>>>>>>>");
		return userRespository.list();
	}

	@Override
	public MyUser get(Long id) throws NotFoundException {
		LOGGER.info("IN MyUserServiceImpl.get User for Id: " + id);
		return userRespository.get(id);
	}

	@Override
	public MyUser save(MyUser myUser) throws BadRequestException {
		LOGGER.info("IN MyUserServiceImpl.save: " + myUser);
		return userRespository.save(myUser);
	}

	@Override
	public Boolean deleteMyUser(Long id) throws BadRequestException {
		LOGGER.info("IN MyUserServiceImpl.deleteUser for Id: " + id);
		return userRespository.deleteMyUser(id);
	}

}
