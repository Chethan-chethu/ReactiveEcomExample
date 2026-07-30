package com.example.ReactiveEcomExample.functionalEndpoints.service;

import org.springframework.stereotype.Service;

import com.example.ReactiveEcomExample.functionalEndpoints.model.User;

import java.util.*;

@Service
public class UserService {

	public List<User> getUsers(){
		
		User user1=new User();
		user1.setUserId("awewrhu");
		user1.setUserName("chethan");
		
		User user2=new User();
		user2.setUserId("kjdwaah");
		user2.setUserName("charan");		
	
		return List.of(user1,user2);		
	}
	
	public User getUserById() {
		User user1=new User();
		user1.setUserId("uywkrb");
		user1.setUserName("sampatj");
		
		return user1;
	}
	
	
}
