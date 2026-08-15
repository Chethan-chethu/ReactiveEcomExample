package com.example.ReactiveEcomExample.functionHandlers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.ReactiveEcomExample.functionalEndpoints.service.WorkerService;

import reactor.core.publisher.Mono;

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
	
	@Bean
	public RouterFunction<ServerResponse> handleDoubleRoute(DoubleHandler doubleHandler){
		return RouterFunctions.route()
				.GET("/routeHandlers/getDoubleValues",doubleHandler::getDoubleValues).build();
	}
	
	@Bean 
	public RouterFunction<ServerResponse> handleFluxUsers(UsersHandler usersHandler){
		return RouterFunctions.route().
		GET("/routeHandlers/handleFluxUsers",usersHandler::getUsers).
		GET("/routeHandlers/handleFluxUsersById/{id}",usersHandler::getUserById)
		.build();
	}
	
	@Bean
	public RouterFunction<ServerResponse> getNestedUserRoute(UsersHandler usersHandler){
		return RouterFunctions.nest(
				RequestPredicates.path("/nest/user"),
				RouterFunctions.route()
				.GET("",usersHandler::getString)
				.GET("getName",usersHandler::getName)
				.build()
				);
	}
	
	@Bean
	public RouterFunction<ServerResponse> getR2dbcRouter(WorkerHandler workerHandler){
		return RouterFunctions.nest(
				RequestPredicates.path("/r2dbc/worker"),
				RouterFunctions.route()
				.GET("/getWorkerById/{id}",workerHandler::getWorkerById)
				.GET("/getWorkersList",workerHandler::getWorkersList).build()
				);
		
	}
	
	
}
