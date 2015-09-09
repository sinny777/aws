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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jadecore.finance.abstraction.exception.BadRequestException;
import com.jadecore.finance.abstraction.exception.NotFoundException;
import com.jadecore.finance.abstraction.model.MyUser;
import com.jadecore.finance.abstraction.repository.MyUserRepository;

/*
 * @author gurvinder.singh
 * @Date 24-Apr-2015
 */

@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = false)
@Repository
public class MyUserRepositoryImpl implements MyUserRepository {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MyUserRepositoryImpl.class);

	@PersistenceContext(unitName = "entitymanageFactory")
	private EntityManager entityManager;

	@Override
	public List<MyUser> list() throws NotFoundException {
		CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MyUser> criteraQuery = queryBuilder
				.createQuery(MyUser.class);
		TypedQuery<MyUser> query = entityManager.createQuery(criteraQuery);
		query.setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		return query.getResultList();
	}

	@Override
	public MyUser get(Long id) throws NotFoundException {
		return entityManager.find(MyUser.class, id);
	}

	@Override
	public MyUser save(MyUser myUser) throws BadRequestException {
		return entityManager.merge(myUser);
	}

	@Override
	public Boolean deleteMyUser(Long id) throws BadRequestException {
		entityManager.remove(get(id));
		return true;
	}

}
