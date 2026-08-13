package com.example.ReactiveEcomExample.functionHandlers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class DoubleHandler {

	public Mono<ServerResponse> getDoubleValues(ServerRequest request){
		Mono<Double> doubleMono=Mono.just(0.03);
		return ServerResponse.ok().body(doubleMono,Double.class);
	}
	
}
