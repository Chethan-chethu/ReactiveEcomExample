package com.example.ReactiveEcomExample.functionHandlers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class ObjectHandler {

	public Mono<ServerResponse> getObject(ServerRequest request){
		Mono<Object> obj = Mono.just(new Object());
		return ServerResponse.ok().body(obj,Object.class);
	}
	
}
