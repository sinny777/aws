package com.jadecore.aws.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class RestWebAppInitializer implements WebApplicationInitializer {

	private static final String REST_CONTEXT_PATH = "/*";

	private static final String CORS_FILTER_NAME = "CorsFilter";

	private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

	private static final String[] SCAN_PATHS = new String[] { "com.jadecore.aws" };

	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
		root.scan(SCAN_PATHS);
		servletContext.addListener(new ContextLoaderListener(root));
		servletContext.addFilter(CORS_FILTER_NAME, CorsFilter.class)
				.addMappingForUrlPatterns(null, false, REST_CONTEXT_PATH);

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
				DISPATCHER_SERVLET_NAME, new DispatcherServlet(root));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping(REST_CONTEXT_PATH);
	}

}
