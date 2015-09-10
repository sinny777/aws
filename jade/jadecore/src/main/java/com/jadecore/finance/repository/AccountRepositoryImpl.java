/*******************************************************************************
 *
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER. 
 *  Copyright (c) 2015 Jade Global
 *  All Rights Reserved. All content is proprietary and confidential.
 *
 *******************************************************************************/

package com.jadecore.finance.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.eclipse.persistence.config.CacheUsage;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jadecore.finance.abstraction.exception.BadRequestException;
import com.jadecore.finance.abstraction.exception.NotFoundException;
import com.jadecore.finance.abstraction.model.Account;
import com.jadecore.finance.abstraction.repository.AccountRepository;

/*
 * @author gurvinder.singh
 * @Date 09-Sep-2015
 */

@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = false)
@Repository
public class AccountRepositoryImpl implements AccountRepository {

	@PersistenceContext(unitName = "entitymanageFactory")
	private EntityManager entityManager;

	@Override
	public List<Account> list(Long userId) throws NotFoundException {
		CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Account> criteraQuery = queryBuilder
				.createQuery(Account.class);
		TypedQuery<Account> query = entityManager.createQuery(criteraQuery);
		query.setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		return query.getResultList();
	}

	@Override
	public Account get(String accountNumber) throws NotFoundException {
		return entityManager.find(Account.class, accountNumber);
	}

	@Override
	public Account save(Account account) throws BadRequestException {
		return entityManager.merge(account);
	}

	@Override
	public Boolean deactivateAccount(String accountNumber)
			throws BadRequestException {
		// entityManager.remove(get(id));
		return true;
	}

}
