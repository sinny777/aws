package com.jadecore.finance.di;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ AppConfig.class })
@ComponentScan({ "com.jadecore.finance.service",
		"com.jadecore.finance.repository" })
public class TestConfig {

}
