package com.example.ReactiveEcomExample.functionalEndpoints.service;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;

import com.example.ReactiveEcomExample.model.Worker;
import com.example.ReactiveEcomExample.repository.WorkerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WorkerService {

	private final WorkerRepository workerRepository;
	private final DatabaseClient databaseClient;
	
	public WorkerService(WorkerRepository workerRepository,DatabaseClient databaseClient) {
		this.workerRepository=workerRepository;
		this.databaseClient=databaseClient;
	}
	
	public Mono<Worker> getUserById(Long id){
		return workerRepository.findById(id);
	}
	
	public Flux<Worker> getWorkers(){
		return databaseClient.sql("select id, name, email, age from worker").map((rowData,metaData)->{
			Worker worker=new Worker();
			worker.setId(rowData.get("id",Long.class));
			worker.setName(rowData.get("name",String.class));
			worker.setEmail(rowData.get("email",String.class));
			worker.setAge(rowData.get("age",Integer.class));
			return worker;
		}).all();
	}
	
}
