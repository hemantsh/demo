package com.infy.demo.handler;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.infy.demo.model.Employee;
import com.infy.demo.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class EmployeeHandler {

	@Autowired
	private EmployeeService empService;
	
	static Mono<ServerResponse> notFound = ServerResponse.notFound().build();
	
	public Mono<ServerResponse> getEmployeeDetails(ServerRequest request) {
		
		String id = request.pathVariable("id");
		//TODO: id validation
		Mono<Employee> emp = empService.getEmployeeDetails( Long.parseLong(id) );
		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body( emp, Employee.class );
	}
	
	public Mono<ServerResponse> getEmployees(ServerRequest request) {
		Optional<String> loc = request.queryParam("location");
		Flux<Employee> empList = null;
		
		if( loc.isPresent() ) {
			empList = empService.getEmployeeByLocation( loc.get() );
		}else {
			empList = empService.getAllEmployees();
		}
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(empList, Employee.class)
				.switchIfEmpty(notFound);
	}
	
	public Mono<ServerResponse> deleteEmployee(ServerRequest request) {
		String id = request.pathVariable("id");
		//TODO: id validation
		Mono<Void> deleteItem = empService.deleteEmployeeDetails( Long.parseLong(id) );
		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(deleteItem, Void.class);
		
	}
	
	public Mono<ServerResponse> insertEmployee( ServerRequest request ) {
		Mono<Employee> empToSave = request.bodyToMono(Employee.class);
		
		
		return empToSave.flatMap(emp ->
			ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body( empService.insertEmployeeDetails(emp) , Employee.class) );
	}
	
	public Mono<ServerResponse> updateEmployee(ServerRequest request) {
		
		//TODO: id validation
		Mono<Employee> empToSave = request.bodyToMono(Employee.class);
		
		return empToSave.flatMap(emp ->
			ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body( empService.saveEmployeeDetails(emp) , Employee.class) )
				.switchIfEmpty(notFound);
	}
}
