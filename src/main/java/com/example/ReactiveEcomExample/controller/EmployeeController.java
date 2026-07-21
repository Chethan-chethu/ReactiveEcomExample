package com.example.ReactiveEcomExample.controller;

import java.util.Map;
import java.util.List;
import java.time.Duration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReactiveEcomExample.model.Department;
import com.example.ReactiveEcomExample.model.Employee;
import com.example.ReactiveEcomExample.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;
	
	@GetMapping("/map")
	public Flux<Employee> map(){
		return employeeService.getEmployees().map(emp->{
			emp.setSalary(emp.getSalary()*1.1);
			return emp;	
		});
	}
	
	@GetMapping("/flatMap")
	public Flux<Department> flatMap(){
		return employeeService.getEmployees().flatMap(emp->employeeService.getDepartment(emp.getDepartment()));
	}
	
	@GetMapping("/concat")
	public Flux<Employee> concat(){
		return Flux.concat(
				employeeService.getEmployees(),
				employeeService.getContractEmployees()
				);
	}
	
	@GetMapping("/combineLatest")
	public Flux<String> combineLatest(){
		return Flux.combineLatest(employeeService.getEmployees(), employeeService.getDepartments(),(emp,dept)->emp.getName()+" -> "+dept);
	}
	
	@GetMapping("/first")
	public Flux<Employee> first(){
		Flux<Employee> slow=employeeService.getEmployees().delayElements(Duration.ofSeconds(2));
		Flux<Employee> fast=employeeService.getContractEmployees().delayElements(Duration.ofSeconds(1));
		
		return Flux.firstWithSignal(slow,fast);		
	}
	
	@GetMapping("/collectList")
	public Mono<List<Employee>> collectList(){
		return employeeService.getEmployees().collectList();
	}
	
	@GetMapping("/collectMap")
	public Mono<Map<String,Employee>> collectMap(){
		return employeeService.getEmployees().collectMap(Employee::getName);
	}
	
	@GetMapping("/totalSalary")
	public Mono<Double> totalSalary(){
		return employeeService.getEmployees().map(Employee::getSalary).reduce(0.0,Double::sum);
	}	
	
	@GetMapping("/count")
	public Mono<Long> count(){
		return employeeService.getEmployees().count();
	}		
	
	// checks if all the elements in the Flux satisfy the condition , 
	// return Mono<Boolean> as response , similar to "AND" operator
	@GetMapping("/all")
	public Mono<Boolean> allEmployeesAbove40k(){
		return employeeService.getEmployees().all(emp->emp.getSalary()>400000);
	}
	
	// checks if any element within the flux matches the condition 
	// , stops processing if any element returns true , similar to "OR" operator
	@GetMapping("/any")
	public Mono<Boolean> anyEmployeeFinance(){
		return employeeService.getEmployees().any(emp->emp.getDepartment().equals("Finance"));
	}
	
	// OnErrorReturn is fallback code for Exceptions
	@GetMapping("/error")
	public Flux<Employee> errorExample(){
		return employeeService.getEmployees().map(emp->{
			try {
				if(emp.getId()==3) throw new RuntimeException("Employee not found");		
			}						
			catch(Exception e) {}
			return emp;		
		}).onErrorReturn(new Employee(999,"Default","NA",0.0));
	}
	
	// onErrorResume is useful , when we want to resume despite facing exceptions ,
	// like fetch data from redis cache or from any other publisher
	@GetMapping("/onErrorResume")
	public Flux<Employee> onErrorResume(){
		return employeeService.getEmployees().map(emp->{
			if(emp.getId()==3)throw new RuntimeException("DB down");
			return emp;
		}).onErrorResume(ex->{
			return employeeService.getContractEmployees();
		});
	}	
	
	// onErrorMap is used for mapping one exception to another
	@GetMapping("/onErrorMap")
	public Flux<Employee> onErrorMap(){
		return employeeService.getEmployees().map(emp->{ if(emp.getId()==3){

            throw new RuntimeException("Database Exception");

        }

        return emp;}).onErrorMap(ex->new IllegalArgumentException("Employee Service Error"));
	}
	
	@GetMapping("/retry")
	public Flux<Employee> retry(){
		return employeeService.getEmployees().map(emp->{

            if(emp.getId()==3){

                throw new RuntimeException("Temporary Error");

            }

            return emp;

        }).retry(2);
	}
	
	// same method as above but can provide delay time parameter
	@GetMapping("/retryWhen")
	public Flux<Employee> retryWhen(){
		return employeeService.getEmployees() .map(emp->{

            if(emp.getId()==3){

                throw new RuntimeException("Temporary");

            }

            return emp;

        }).retryWhen(reactor.util.retry.Retry.fixedDelay(3, Duration.ofSeconds(3)));
	}
	
	@GetMapping("/doOnNext")
	public Flux<Integer> doOnNext(){
		return employeeService.getEmployees().doOnNext(emp->emp.getId()).map(Employee::getId);
	}
	
	@GetMapping("/doOnSubscribe")
	public Flux<Employee> doOnSubscribe(){
		return employeeService.getContractEmployees().doOnSubscribe(sub->System.out.println("client subscribed to the flux"));
	}
	
	// to trigger a task irrespective of error/stream termination
	@GetMapping("/doFinally")
	public Flux<Employee> doFinallyExample(){
		  return employeeService.getEmployees()
		            .doOnNext(emp ->
		                    System.out.println("Processing : " + emp.getName()))
		            .doFinally(signal->System.out.println("Stream finished because : " + signal));
	}
	
	@GetMapping("/doOnSuccess")
	public Mono<Employee> doOnSuccessEmployee(){
		return employeeService.getEmployee(1).doOnSuccess(emp ->
        System.out.println("Employee Found : " + emp.getName()));
	}
	
}
