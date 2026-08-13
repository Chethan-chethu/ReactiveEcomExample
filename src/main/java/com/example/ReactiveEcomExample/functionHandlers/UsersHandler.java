package com.example.ReactiveEcomExample.functionHandlers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.ReactiveEcomExample.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UsersHandler {

	public Mono<ServerResponse> getUsers(ServerRequest request){
		
		User u1=User.builder().id(1l).name("user1").cityName("city 1").build();
		User u2=User.builder().id(2l).name("user2").cityName("city 2").build();
		User u3=User.builder().id(3l).name("user3").cityName("city 3").build();
		
		
		Flux<User> users=Flux.just(
				u1,u2,u3);
		
		return ServerResponse.ok().body(users,User.class);

		
//		User user= User.builder().id(1l).name("user1").cityName("city").build();				
	}
	
	public Mono<ServerResponse> getUserById(ServerRequest request){
		String id=request.pathVariable("id");
		
		User user=User.builder().id(Long.parseLong(id)).name("user1").cityName("city1").build();
		Mono<User> monoUser=Mono.just(user);
		
		return ServerResponse.ok().body(monoUser,User.class);
		
	}
	

	public Mono<ServerResponse> getString(ServerRequest request){
		return ServerResponse.ok().bodyValue("string");
	}
	
	public Mono<ServerResponse> getName(ServerRequest request){
		return ServerResponse.ok().bodyValue("Name");
	}
	
	
}
