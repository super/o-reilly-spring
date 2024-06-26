package com.keltenfalez.oreillyspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
/*
I use swagger to provide API documentation details. I view them here: http://localhost:8080/swagger-ui/#
 */
public class SwaggerConfiguration {
	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Invoice API",
				"API for Invoice microservice",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Kelten Falez", "https://keltenfalez.com", "kelten@live.ca"),
				"API License",
				"https://keltenfalez.com",
				Collections.emptyList()
		);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.keltenfalez"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiDetails());
	}
}
