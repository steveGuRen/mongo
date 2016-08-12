package com.jeocloud.mongo.controller;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Controller
@RequestMapping("/test")
public class Test {

	@Autowired
	MongoClient mongoClient;
	
	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = database.getCollection("test");
		Document doc = new Document("name", "MongoDB")
	               .append("type", "database")
	               .append("count", 1)
	               .append("info", new Document("x", 203).append("y", 102));
		collection.insertOne(doc);
		return "success";
	}
}
