package com.example.ReactiveEcomExample.functionHandlers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class FluxStringHandler {

	public Mono<ServerResponse> getNames(ServerRequest request){
		Flux<String> names=Flux.just("chethan","charan","sampath");
		return ServerResponse.ok().body(names,String.class);
	} 	
	
}
