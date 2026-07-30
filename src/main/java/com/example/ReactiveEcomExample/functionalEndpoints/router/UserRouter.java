package com.example.ReactiveEcomExample.functionalEndpoints.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.ReactiveEcomExample.functionalEndpoints.handler.UserHandler;

@Configuration
public class UserRouter {
	
	@Bean
	RouterFunction<ServerResponse> routes(UserHandler handler){
		return RouterFunctions.route().GET("/api/users",handler::getAllUsers).build();
	}

}
