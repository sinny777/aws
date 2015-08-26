/*******************************************************************************
 *
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER. 
 *  Copyright (c) 2015 Jade Global
 *  All Rights Reserved. All content is proprietary and confidential.
 *
 *******************************************************************************/
package com.jadecore.aws.di;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import({ InfraConfig.class })
@EnableTransactionManagement
public class DatabaseConfig {

	@Value("${db.username}")
	private String userName;

	@Value("${db.password}")
	private String password;

	@Value("${db.url}")
	private String url;

	@Value("${db.name}")
	private String databaseName;

	@Value("${db.driverClassName}")
	private String driverClassName;

	@Bean(name = "dataSource")
	public DataSource getDataSource() throws SQLException, NamingException {

		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(this.driverClassName);
		ds.setUrl(String.format(this.url, this.databaseName));
		ds.setUsername(this.userName);
		ds.setPassword(this.password);
		return ds;
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate getJdbcTemplate(
			@Qualifier("dataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean(name = "entitymanageFactory")
	public EntityManagerFactory entityManagerFactory() throws SQLException,
			NamingException {
		Map<String, String> jpaProperties = new HashMap<String, String>();
		jpaProperties.put("eclipselink.weaving", "static");
		jpaProperties.put("jpaDialect",
				"org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect");
		return Persistence.createEntityManagerFactory("poc_persistence",
				jpaProperties);
	}

	@Bean(name = "entityManager")
	public EntityManager entityManager(
			@Qualifier("entitymanageFactory") EntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.createEntityManager();
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager() throws SQLException,
			NamingException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory());
		return transactionManager;
	}

	@Bean
	public EclipseLinkJpaDialect eclipseLinkJpaDialect() {
		return new EclipseLinkJpaDialect();
	}
}
