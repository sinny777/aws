/*******************************************************************************
 *
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER. 
 *  Copyright (c) 2015 Jade Global
 *  All Rights Reserved. All content is proprietary and confidential.
 *
 *******************************************************************************/
package com.jadecore.aws.di;

import java.util.Timer;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class InfraConfig {
	
	private static final String DATABASE_PROPS = "database.properties";
	private static final String APPLICATION_PROPS = "application.properties";
	
	@Bean 
	public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer() {
		PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
		Resource[] resourceLocations = new Resource[] {
				new ClassPathResource(DATABASE_PROPS),
				new ClassPathResource(APPLICATION_PROPS)
        };
		propertyPlaceholderConfigurer.setLocations(resourceLocations);
		return propertyPlaceholderConfigurer;
	}
	
	@Bean 
	public Timer getTimerConfigurer() {
		return new Timer();
	}

}
