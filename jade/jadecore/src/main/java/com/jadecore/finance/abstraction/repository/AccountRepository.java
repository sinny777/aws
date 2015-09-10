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
import com.jadecore.finance.abstraction.model.Account;

/*
 * @author gurvinder.singh
 * @Date 09-Sep-2015
 */

public interface AccountRepository {

	List<Account> list(Long userId) throws NotFoundException;

	Account get(String accountNumber) throws NotFoundException;

	Account save(Account account) throws BadRequestException;

	Boolean deactivateAccount(String accountNumber) throws BadRequestException;

}
