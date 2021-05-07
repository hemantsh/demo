package com.infy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.infy.demo.model.Employee;

@Component
public class EmployeeModelListener extends AbstractMongoEventListener<Employee>  {

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Employee> event) {
    	
//        if (event.getSource().getEmployeeId() < 1) {
//        	
//        	sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME)
//        	.doOnNext(result -> {
//        		//event.getSource().setEmployeeId(result.getSequence());
////        		System.out.println( "onBeforeConvert seq===="+ event.getSource().getEmployeeId() );
//        	})
//        	.subscribe();
//        }
        //super.onBeforeSave(event);
//        System.out.println( "after seq===="+ event.getSource().getEmployeeId() );
    }
}
