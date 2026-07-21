package com.example.ReactiveEcomExample.service;

import org.springframework.stereotype.Service;

import com.example.ReactiveEcomExample.model.Department;
import com.example.ReactiveEcomExample.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

	public Flux<Employee> getEmployees(){
		return Flux.just(new Employee(1,"John","IT",50000.0),
				new Employee(2,"Alice","HR",45000.0),
                new Employee(3,"Bob","IT",70000.0),
                new Employee(4,"David","Finance",80000.0),
                new Employee(5,"Alice","HR",45000.0));
	}
	
	public Mono<Employee> getEmployee(Integer id){
		return getEmployees().filter(e->e.getId().equals(id)).next();
	}
	
	public Mono<Department> getDepartment(String name){
		return Mono.just(new Department(101,name));
	}
	
	public Flux<Employee> getContractEmployees(){
		return Flux.just( new Employee(6,"Kevin","IT",30000.0),
	            new Employee(7,"Rose","Finance",35000.0));
	}
	
	public Flux<String> getDepartments(){
		return Flux.just("IT","HR","Finance");
	}
	
}
