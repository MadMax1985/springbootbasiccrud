package net.javaguides.springboot.tutorial.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer{
	
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index").setViewName("list");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/add-student").setViewName("signup");
		registry.addViewController("/update-student").setViewName("add");
		//registry.addViewController("/login").setViewName("login");
		//registry.addViewController("/students/login").setViewName("login");
		registry.addViewController("/students/list").setViewName("login");
	}

}
