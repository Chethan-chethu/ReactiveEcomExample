package com.example.ReactiveEcomExample.functionHandlers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class FunctionHandlers {

	@Bean
	public RouterFunction<ServerResponse> Handlerroutes(ObjectHandler objHandler){
		return RouterFunctions.route(RequestPredicates.GET("/routeHandlers/getObject"),
				objHandler::getObject);
	}
	
	@Bean
	public RouterFunction<ServerResponse> handleStringRoute(FluxStringHandler fluxStringHandler){
		return RouterFunctions.route().
				GET("/routeHandlers/getStringNames",fluxStringHandler::getNames).build();
	}
	
}
