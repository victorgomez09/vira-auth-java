package com.virasoftware.apigateway.configs;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.virasoftware.apigateway.filters.AuthenticationFilter;

@Configuration
public class GatewayConfig {
	
	private final AuthenticationFilter authFilter;

	public GatewayConfig(AuthenticationFilter authFilter) {
		this.authFilter = authFilter;
	}

	@Bean
	RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("auth-service",
						r -> r.path("/api/v1/auth/**").filters(f -> f.filter(authFilter)).uri("lb://auth-service"))
				.route("auth-service-email",
						r -> r.path("/api/v1/email").filters(f -> f.filter(authFilter)).uri("lb://auth-service"))
				.route("user-service",
						r -> r.path("/api/v1/user/**", "/api/v1/user").filters(f -> f.filter(authFilter))
								.uri("lb://user-service"))
				.route("docs-service",
						r -> r.path("/api/v1/doc/**", "/api/v1/doc").filters(f -> f.filter(authFilter))
						.uri("lb://docs-service"))
				.route("swagger-redirect",
						r -> r.path("/v3/api-docs/**")
								.filters(f -> f.rewritePath("/v3/api-docs/(?<name>.*)", "/${name}/v3/api-docs"))
								.uri("lb://api-gateway"))
				.build();
	}

}
