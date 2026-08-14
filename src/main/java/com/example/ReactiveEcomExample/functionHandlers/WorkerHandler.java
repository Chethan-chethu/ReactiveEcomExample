package com.example.ReactiveEcomExample.functionHandlers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.ReactiveEcomExample.functionalEndpoints.service.WorkerService;
import com.example.ReactiveEcomExample.model.Worker;

import reactor.core.publisher.Mono;

@Component
public class WorkerHandler {
	
	private final WorkerService workerService;
	
	public WorkerHandler(WorkerService workerService) {
		this.workerService=workerService;
	}

	public Mono<ServerResponse> getWorkerById(ServerRequest request){
		Long id=Long.parseLong(request.pathVariable("id"));		
		return ServerResponse.ok().body(workerService.getUserById(id),Worker.class);
	}
	
}
