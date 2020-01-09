package com.brijframework.useraccount.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/api/", "api/csrf", "/actuator/*", "/swagger-resources/**", "/api/swagger-resources",
						"/api/swagger-resources/**", "/api/configuration/ui", "/api/webjars/**", "/api/swagger-ui.html",
						"/api/configuration/security", "/v2/api-docs", "/api/v2/api-docs")
				.permitAll().anyRequest().authenticated();
	}
}