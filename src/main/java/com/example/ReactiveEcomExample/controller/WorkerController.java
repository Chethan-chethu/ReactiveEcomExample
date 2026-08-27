package com.example.ReactiveEcomExample.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReactiveEcomExample.model.Worker;
import com.example.ReactiveEcomExample.repository.WorkerRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/worker")
@RequiredArgsConstructor
public class WorkerController {
	
	private final WorkerRepository workerRepository;

//	@GetMapping("allWorkers")
//	public Flux<Worker> getWorkers(){
//		return workerRepository.findAll();
//	}
//	
//	@GetMapping("/worker/{id}")
//	public Mono<Worker> getWorkerById(@PathVariable Long id){
//		return workerRepository.findById(id);
//	}
	
	
// 	Responsive Entity example which is also a duplicate of the above Flux Request return type	
	@GetMapping("responsive/allWorkers")
	public ResponseEntity<Flux<Worker>> getResponsiveWorkers(){
		Flux<Worker> workers=workerRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).header("X-My-Header", "My header Value").body(workers);
	}
		
	
}
