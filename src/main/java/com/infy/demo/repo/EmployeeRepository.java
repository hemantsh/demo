package com.infy.demo.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.infy.demo.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, Long>  {

	Flux<Employee> findAllByBaseLocation( String baseLocation );
	Mono<Employee> findByEmployeeId( Long empId );
	Mono<Void> deleteByEmployeeId( Long empId );
}
