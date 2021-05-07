package com.infy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.infy.demo.model.Employee;
import com.infy.demo.repo.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	SequenceGeneratorService seqService;
	

	public Mono<Employee> getEmployeeDetails( long id) {
		
		return empRepo.findByEmployeeId(id);
	}
	

	public Mono<Void> deleteEmployeeDetails( long id ) {
		return empRepo.deleteByEmployeeId(id);
	}
	
	
	public Mono<Void> deleteEmployeeDetails( Employee emp ) {
		return empRepo.delete( emp );
	}
	

	public Mono<Employee> saveEmployeeDetails( Employee emp ) {
//		System.out.println(" SAVE ============"+ emp.getEmployeeId() );
		return empRepo.save( emp );
	}
	
	
	public Mono<Employee> insertEmployeeDetails( Employee emp ) {

//		if( emp.getEmployeeId() > 0) {
//			Mono<ServerResponse> badReq = ServerResponse.badRequest().bodyValue("Employee id must be empty");
//			return badReq;
//		}
		seqService.generateSequence(Employee.SEQUENCE_NAME)
    	.doOnSuccess(result -> {
    		emp.setEmployeeId(result.getSequence());
//    		System.out.println( " Setting new id ============"+ emp.getEmployeeId() );
    		//empRepo.save( emp );
    	})
    	.subscribe();
		
//		System.out.println( " INSERT ============"+ emp.getEmployeeId() );
		return empRepo.save( emp );
	}


	public Mono<Employee> updateEmployeeDetails( Employee emp ) {
		return empRepo.save( emp );
	}
	
	public Flux<Employee> getEmployeeByLocation( String location ) {
		if( location != null) {
			location = location.toUpperCase();
		}
		return empRepo.findAllByBaseLocation(location);
	}
	
	public Flux<Employee> getAllEmployees(  ) {
		return empRepo.findAll();
	}
}
