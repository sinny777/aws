package com.jadecore.aws.di;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@EnableIntegration
@ComponentScan({ "com.jadecore.aws.reposirory", "com.jadecore.aws.service",
		"com.jadecore.aws.web" })
@Import({ DatabaseConfig.class })
@EnableAspectJAutoProxy
public class AppConfig {

}
