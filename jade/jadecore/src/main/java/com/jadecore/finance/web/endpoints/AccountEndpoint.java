/*******************************************************************************
 *
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER. 
 *  Copyright (c) 2015 Jade Global
 *  All Rights Reserved. All content is proprietary and confidential.
 *
 *******************************************************************************/
package com.jadecore.finance.web.endpoints;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jadecore.finance.abstraction.model.Account;
import com.jadecore.finance.abstraction.service.AccountService;

@RestController
@RequestMapping(value = "/account")
public class AccountEndpoint {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AccountEndpoint.class);

	private AccountService accountService;

	@Autowired
	public AccountEndpoint(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{userId}")
	public List<Account> list(@PathVariable("userId") Long userId) {
		LOGGER.info("IN AccountEndpoint.list >>>>>>>>>>>>>>");
		return accountService.list(userId);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{accountNumber}")
	public Account get(@PathVariable("accountNumber") String accountNumber) {
		LOGGER.info("IN AccountEndpoint.get User for accountNumber: "
				+ accountNumber);
		return accountService.get(accountNumber);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Account save(@RequestBody Account account) {
		LOGGER.info("IN AccountEndpoint.save Account: " + account);
		return accountService.save(account);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{accountId}")
	public Account updateAccount(@PathVariable("accountId") Long accountId,
			@RequestBody Account account) {
		LOGGER.info("IN AccountEndpoint.updateAccount Account: " + account);
		return accountService.save(account);
	}

}
