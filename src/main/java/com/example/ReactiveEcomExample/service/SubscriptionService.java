package com.example.ReactiveEcomExample.service;

import java.time.Duration;

import org.reactivestreams.Subscription;
import org.springframework.stereotype.Service;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SubscriptionService {

	public Mono<String> responseSignlExample(){
		
		Flux<Integer> flux=Flux.range(1, 100);
		flux.subscribe(new BaseSubscriber<Integer>() {
////			@Override
////			protected void hookOnSubscribe(Subscription subscription) {
////				System.out.println("Subscribed");
////				request(5);
////			}
//			
////			@Override
////			protected void hookOnNext(Integer value) {
////				System.out.println(value);
////			}
			
		});
		
		return Mono.just("responseSignlExample");
	}
	
	
	public Mono<String> cancelExample(){
		Flux.interval(Duration.ofSeconds(1)).subscribe(new BaseSubscriber<Long>() {
			
			@Override
			protected void hookOnSubscribe(Subscription subscription) {
				request(Long.MAX_VALUE);
			}
			
			@Override
			protected void hookOnNext(Long value) {
				System.out.println(value);
				if(value==5)cancel();
			}
			
		});
		
		return Mono.just("cancelExample");
	}
	
	public Mono<String> backPressureExample(){
		
		Flux.range(1, 100).subscribe(new BaseSubscriber<Integer>() {
			
			@Override
			protected void hookOnSubscribe(Subscription subscription) {
				request(10);
			}
			
			@Override
			protected void hookOnNext(Integer value) {
				System.out.println(value);
				if(value%5==0)request(10);
			}
			
		});
		
		return Mono.just("backPressureExample");
	}
	
	
}
