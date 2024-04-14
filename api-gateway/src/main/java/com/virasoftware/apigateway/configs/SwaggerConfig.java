package com.virasoftware.apigateway.configs;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    List<GroupedOpenApi> apis(RouteDefinitionLocator locator) {
        ArrayList<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();

        if (definitions != null) {        	
        	definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
        		String name = routeDefinition.getId().replace("-service", "");
        		groups.add(GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build());
        	});
        }
        
        return groups;
    }

}
