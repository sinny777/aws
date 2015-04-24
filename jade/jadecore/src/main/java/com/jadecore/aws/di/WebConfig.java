package com.jadecore.aws.di;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
@Import(AppConfig.class)
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	private static final int DEFAULT_PAGE_NUMBER = 1;
	private static final int DEFAULT_PAGE_SIZE = 10;

	@Bean
	public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
		List<HttpMessageConverter<?>> list = new ArrayList<>();
		list.add(new MappingJackson2HttpMessageConverter());

		requestMappingHandlerAdapter.setMessageConverters(list);
		return requestMappingHandlerAdapter;
	}

	/*
	 * @Bean(name = "multipartResolver") public CommonsMultipartResolver
	 * multiPartResolver(){ return new CommonsMultipartResolver(); }
	 */

	@Override
	public void addArgumentResolvers(
			List<HandlerMethodArgumentResolver> argumentResolvers) {
		PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
		resolver.setFallbackPageable(new PageRequest(DEFAULT_PAGE_NUMBER,
				DEFAULT_PAGE_SIZE));
		argumentResolvers.add(resolver);
	}
}
