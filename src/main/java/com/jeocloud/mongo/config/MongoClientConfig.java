package com.jeocloud.mongo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

@Configuration
public class MongoClientConfig {
	
	@Bean
	public MongoClient config() {
		MongoClientURI connectionString = new MongoClientURI("mongodb://172.20.110.143:27017");
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase("test");
		return mongoClient;
	}
	
}
