package com.example.ReactiveEcomExample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/keyclock")
public class KeyCloakController {

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Hello from Spring!");
    }	
	
}
