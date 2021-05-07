package com.infy.demo.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

//@Configuration
@EnableReactiveMongoRepositories
@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
public class MongoConfig extends AbstractReactiveMongoConfiguration {
	 
	@Autowired
    private Environment env;
	
    @Override
    protected String getDatabaseName() {
        return env.getProperty("spring.data.mongodb.database");
    }
 
    @Override
    public MongoClient reactiveMongoClient() {
        ConnectionString connectionString = new ConnectionString( env.getProperty("spring.data.mongodb.uri") );
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
        
        return MongoClients.create(mongoClientSettings);
    }
 
    @Override
    public Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.infy");
    }
}