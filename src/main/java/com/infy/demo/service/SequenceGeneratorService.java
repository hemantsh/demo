package com.infy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.infy.demo.model.DatabaseSequence;

import reactor.core.publisher.Mono;

@Service
public class SequenceGeneratorService {

	@Autowired
    private ReactiveMongoOperations mongoOperations;
	
    
	public Mono<DatabaseSequence> generateSequence(String seqName) {
	    Mono<DatabaseSequence> counter = mongoOperations.findAndModify(Query.query(Criteria.where("id").is(seqName)),
	      new Update().inc("sequence",1), FindAndModifyOptions.options().returnNew(true).upsert(true),
	      DatabaseSequence.class);
	    
	    
	    return counter;
	}
}
