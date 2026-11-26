package com.list20.SMSMitraPortal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SmsMitraPortalApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(SmsMitraPortalApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SmsMitraPortalApplication.class);
	}
}
