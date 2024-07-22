package com.example.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("vehicle_service_route", r -> r.path("/vehicle-api/**")
						.uri("lb://vehicle-service"))
				.route("stock_service_route", r -> r.path("/stock-api/**")
						.uri("lb://stock-service"))
				.route("user_service_route", r -> r.path("/user-api/**")
						.uri("lb://user-service"))
				.route("discovery_service_route", r -> r.path("/euraka/web")
						.uri("http://localhost:8761"))
				.route("discovery_service_static_route", r -> r.path("/euraka/**")
						.uri("http://localhost:8761"))
				.build();
				
	}

}
