package com.app.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.app.profile")
public class ProfileApplication extends SpringBootServletInitializer{

	private static final Class<ProfileApplication> applicationClass = ProfileApplication.class;
	public static void main(String[] args) {
		SpringApplication.run(ProfileApplication.class, args);
	}

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
}
