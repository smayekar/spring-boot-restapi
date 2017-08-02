package com.ctl.restapi;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.google.common.base.Predicates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class RestApiApplication {
	@Bean
	public Docket serviceApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("rest-api")
				.apiInfo(apiInfo()).select().paths(Predicates.not(PathSelectors.regex("/error"))).build().pathMapping("/");
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Common REST API Services")
				.description("Common REST API Services")
				.termsOfServiceUrl("http://www.centurylink.com/")
				.contact(new Contact("CenturyLink Inc.", "http://www.centurylink.com/", ""))
				.licenseUrl("http://www.centurylink.com/").version("1.0").build();
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
		return new Jackson2ObjectMapperBuilderCustomizer() {
			@Override
			public void customize(Jackson2ObjectMapperBuilder builder) {
				builder.dateFormat(new ISO8601DateFormat());
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}
}
