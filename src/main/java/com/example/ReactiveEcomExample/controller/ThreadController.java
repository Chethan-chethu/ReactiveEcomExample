package com.example.ReactiveEcomExample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/thread")
public class ThreadController {

	@GetMapping("/publishOnExample")
	public Mono<String> publishOnExample(){
		
		Flux.range(1, 5).map(i->{
			System.out.println("Map1: " + Thread.currentThread().getName());
			return i;
		}).publishOn(Schedulers.parallel()).map(i->{System.out.println("Map2: " + Thread.currentThread().getName());
        return i * 2;}).subscribe();
		
		return Mono.just("publishOnExample");
		
	}
	
	
	
}
