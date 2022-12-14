package br.com.asap.seguradora.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket swagger() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.asap.seguradora"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaInfo());
	}
	
	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo(
			"Seguradora de Ve?culos API REST",
			"API REST de seguradora de ve?culos.",
			"1.0",
			"Termos de servi?o",
			new Contact("Carlos Oliveira", "Fone: 4588-6116", "carlos.oliveira@viavarejo.com.br"),
			"Apache License Version 2.0",
			"https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
		);
		return apiInfo;
	}
}
