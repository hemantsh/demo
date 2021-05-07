package com.infy.demo.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.infy.demo.model.Employee;

import reactor.core.publisher.Flux;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String>  {

	Flux<Employee> findAllByBaseLocation( String baseLocation );
}
