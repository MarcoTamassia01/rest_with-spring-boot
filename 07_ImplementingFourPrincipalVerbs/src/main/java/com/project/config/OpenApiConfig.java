package com.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("RESTFUL API WITH JAVA 21 AND SPRING BOOT3")
						.version("v1")
						.description("Some description about you API")
						.termsOfService("https://github.com/MarcoTamassia01")
						.license(
								new License()
								.name("Apache 2.0")
								.url("https://github.com/MarcoTamassia01"))
						);
		
	}

}
