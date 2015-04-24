/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER. 
 * Copyright (c) 2014 Kinetic Networks, Inc. 
 * All Rights Reserved. All content is proprietary and confidential.
 */

package com.jadecore.aws.abstraction.repository;

import java.util.List;

import com.jadecore.aws.abstraction.exception.BadRequestException;
import com.jadecore.aws.abstraction.exception.NotFoundException;
import com.jadecore.aws.abstraction.model.MyUser;

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
