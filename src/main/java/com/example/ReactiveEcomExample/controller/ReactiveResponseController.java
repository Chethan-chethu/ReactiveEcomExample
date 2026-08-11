package com.example.ReactiveEcomExample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReactiveEcomExample.functionalEndpoints.service.ReactiveResponseService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactiveResponse")
@RequiredArgsConstructor
public class ReactiveResponseController {
	
	private final ReactiveResponseService reactiveResponseService;

	@GetMapping("/object/{id}")
	public Mono<ResponseEntity<Object>> getObjectById(@PathVariable Long id){
		return reactiveResponseService.getObjectById(id)
				.map(object-> ResponseEntity.ok(object)).defaultIfEmpty(ResponseEntity.notFound().build());
	} 
	
	
}
