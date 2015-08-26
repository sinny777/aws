/*******************************************************************************
 *
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER. 
 *  Copyright (c) 2015 Jade Global
 *  All Rights Reserved. All content is proprietary and confidential.
 *
 *******************************************************************************/

package com.jadecore.aws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jadecore.aws.abstraction.exception.BadRequestException;
import com.jadecore.aws.abstraction.exception.NotFoundException;
import com.jadecore.aws.abstraction.model.MyUser;
import com.jadecore.aws.abstraction.repository.MyUserRepository;
import com.jadecore.aws.abstraction.service.MyUserService;

/*
 * @author gurvinder.singh
 * @Date 24-Apr-2015
 */

@Service
public class MyUserServiceImpl implements MyUserService {

	private final MyUserRepository userRespository;

	@Autowired
	public MyUserServiceImpl(MyUserRepository userRespository) {
		this.userRespository = userRespository;
	}

	@Override
	public List<MyUser> list() throws NotFoundException {
		return userRespository.list();
	}

	@Override
	public MyUser get(Long id) throws NotFoundException {
		return userRespository.get(id);
	}

	@Override
	public MyUser save(MyUser myUser) throws BadRequestException {
		return userRespository.save(myUser);
	}

	@Override
	public Boolean deleteMyUser(Long id) throws BadRequestException {
		return userRespository.deleteMyUser(id);
	}

}
