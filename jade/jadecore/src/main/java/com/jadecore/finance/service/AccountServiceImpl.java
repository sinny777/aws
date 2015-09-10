/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER. 
 * Copyright (c) 2015 Kinetic Networks, Inc. 
 * All Rights Reserved. All content is proprietary and confidential.
 */

package com.jadecore.finance.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jadecore.finance.abstraction.exception.BadRequestException;
import com.jadecore.finance.abstraction.exception.NotFoundException;
import com.jadecore.finance.abstraction.model.Account;
import com.jadecore.finance.abstraction.repository.AccountRepository;
import com.jadecore.finance.abstraction.service.AccountService;

/*
 * @author gurvinder.singh
 * @Date 10-Sep-2015
 */

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AccountServiceImpl.class);

	private final AccountRepository accountRespository;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRespository) {
		this.accountRespository = accountRespository;
	}

	@Override
	public List<Account> list(Long userId) throws NotFoundException {
		LOGGER.info("IN AccountServiceImpl.list for UserId: " + userId);
		return accountRespository.list(userId);
	}

	@Override
	public Account get(String accountNumber) throws NotFoundException {
		return accountRespository.get(accountNumber);
	}

	@Override
	public Account save(Account account) throws BadRequestException {
		return accountRespository.save(account);
	}

	@Override
	public Boolean deactivateAccount(String accountNumber)
			throws BadRequestException {
		return accountRespository.deactivateAccount(accountNumber);
	}

}
