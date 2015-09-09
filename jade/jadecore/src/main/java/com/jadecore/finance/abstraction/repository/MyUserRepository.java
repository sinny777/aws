/*******************************************************************************
 *
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER. 
 *  Copyright (c) 2015 Jade Global
 *  All Rights Reserved. All content is proprietary and confidential.
 *
 *******************************************************************************/

package com.jadecore.finance.abstraction.repository;

import java.util.List;

import com.jadecore.finance.abstraction.exception.BadRequestException;
import com.jadecore.finance.abstraction.exception.NotFoundException;
import com.jadecore.finance.abstraction.model.MyUser;

/*
 * @author gurvinder.singh
 * @Date 24-Apr-2015
 */

public interface MyUserRepository {

	List<MyUser> list() throws NotFoundException;

	MyUser get(Long id) throws NotFoundException;

	MyUser save(MyUser myUser) throws BadRequestException;

	Boolean deleteMyUser(Long id) throws BadRequestException;

}
