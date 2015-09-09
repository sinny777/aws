/*******************************************************************************
 *
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER. 
 *  Copyright (c) 2015 Jade Global
 *  All Rights Reserved. All content is proprietary and confidential.
 *
 *******************************************************************************/
package com.jadecore.aws.web.endpoints;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jadecore.aws.abstraction.model.MyUser;
import com.jadecore.aws.abstraction.service.MyUserService;

@RestController
@RequestMapping(value = "/user")
public class MyUserEndpint {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MyUserEndpint.class);

	private MyUserService userService;

	@Autowired
	public MyUserEndpint(MyUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<MyUser> list() {
		LOGGER.info("IN MyUserEndpint.list >>>>>>>>>>>>>>");
		return userService.list();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{userId}")
	public MyUser get(@PathVariable("userId") Long userId) {
		LOGGER.info("IN MyUserEndpint.get User for userId: " + userId);
		return userService.get(userId);
	}

	@RequestMapping(method = RequestMethod.POST)
	public MyUser save(@RequestBody MyUser myUser) {
		LOGGER.info("IN MyUserEndpint.save User: " + myUser);
		return userService.save(myUser);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{userId}")
	public MyUser updateUser(@PathVariable("userId") Long userId,
			@RequestBody MyUser myUser) {
		LOGGER.info("IN MyUserEndpint.updateUser User: " + myUser);
		return userService.save(myUser);
	}

}
