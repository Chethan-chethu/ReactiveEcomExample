package com.example.ReactiveEcomExample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReactiveEcomExample.service.SubscriptionService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionExampleController {
	
	private final SubscriptionService subscriptionService;

	@GetMapping("responseSignal")
	public Mono<String> responseSignlExample(){
		return subscriptionService.responseSignlExample();
	}
	
	@GetMapping("cancelExample")
	public Mono<String> cancelExample(){
		return subscriptionService.cancelExample();
	}	
	
	@GetMapping("backPressureExample")
	public Mono<String> backPressureExample(){
		return subscriptionService.backPressureExample();
	}
	
	
}
