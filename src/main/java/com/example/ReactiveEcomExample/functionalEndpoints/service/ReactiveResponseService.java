package com.example.ReactiveEcomExample.functionalEndpoints.service;


//import java.util.*;

import org.springframework.stereotype.Service;

//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
import reactor.core.publisher.Mono;

@Service
public class ReactiveResponseService {

	public Mono<Object> getObjectById(Long id) {
		return  Mono.just(new Object());
	}
}
