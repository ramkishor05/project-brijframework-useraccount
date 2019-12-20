package com.brijframework.useraccount.config;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build().apiInfo(apiInfo()).securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.newArrayList(apiKey())).forCodeGeneration(true);
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder().title("Useraccount API").description("Useraccount api for application").version("1.0").termsOfServiceUrl("Terms of service").build();
		return apiInfo;
	}

	 private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/.*")).build();
     }

     private List<SecurityReference> defaultAuth() {
       final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
       final AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
       return Collections.singletonList(new SecurityReference("Bearer", authorizationScopes));
     }

     private ApiKey apiKey() {
       return new ApiKey("Bearer", "Authorization", "header");
     } 
}
