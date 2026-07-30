package com.example.ReactiveEcomExample.functionalEndpoints.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.ReactiveEcomExample.functionalEndpoints.model.User;
import com.example.ReactiveEcomExample.functionalEndpoints.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.*;

@Component
public class UserHandler {

	@Autowired
	UserService userService;
	
	public Mono<ServerResponse> getAllUsers(ServerRequest request){
		List<User> userList=userService.getUsers();
		Flux<User> users=Flux.just(userList.get(0),userList.get(1));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(users,User.class);
	}
	
}
