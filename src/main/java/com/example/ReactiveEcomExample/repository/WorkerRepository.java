package com.example.ReactiveEcomExample.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ReactiveEcomExample.model.Worker;

@Repository
public interface WorkerRepository extends ReactiveCrudRepository<Worker,Long> {

	
	
}
