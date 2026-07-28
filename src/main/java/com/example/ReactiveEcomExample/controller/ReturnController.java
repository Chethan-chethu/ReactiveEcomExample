package com.example.ReactiveEcomExample.controller;

import java.time.Duration;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("request")
public class ReturnController {

	@GetMapping("/{id}")
	public Mono<Integer> getSerialNo(@PathVariable Integer id){
		return Mono.just(1);
	}
	
	@GetMapping("/names")
	public Flux<String> getNames(){
		return Flux.just("chethan","charan","sampath").map(name->name+"\n");
	}
	
	@GetMapping(value="/stream",produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> stream(){
		return Flux.interval(Duration.ofSeconds(1)).map(i->"Message"+i);
	}
	
	@GetMapping("/monoResponse")
	public Mono<ResponseEntity<Object>> getMonoResponse(){
		return Mono.just(new Object()).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/monoBodyRequest")
	public Mono<ResponseEntity<Object>> createUser(@RequestBody Mono<Object> objectMono){
		return objectMono.map(obj->ResponseEntity.status(HttpStatus.CREATED).body(obj));
	}
	
	
}
