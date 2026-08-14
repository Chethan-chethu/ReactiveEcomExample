package com.example.ReactiveEcomExample.functionalEndpoints.service;

import org.springframework.stereotype.Service;

import com.example.ReactiveEcomExample.model.Worker;
import com.example.ReactiveEcomExample.repository.WorkerRepository;

import reactor.core.publisher.Mono;

@Service
public class WorkerService {

	private final WorkerRepository workerRepository;
	
	public WorkerService(WorkerRepository workerRepository) {
		this.workerRepository=workerRepository;
	}
	
	public Mono<Worker> getUserById(Long id){
		return workerRepository.findById(id);
	}
	
}
