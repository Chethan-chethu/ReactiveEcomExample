package com.example.ReactiveEcomExample.webclients;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.ReactiveEcomExample.model.User;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("webclient")
public class ControllerCode {
	
	private final WebClient webClient;

	@GetMapping("redirect/data")
	public Mono<String> getUser(){
		return webClient.post().uri("http://localhost:7001/webclient/data").bodyValue(
				User.builder().id((long) 1).name("user1").cityName("sity1").build())
				.retrieve().bodyToMono(String.class);
	}
	
}
