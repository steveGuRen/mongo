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
	
	@RequestMapping("/add")
	@ResponseBody
	public String add() {
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = database.getCollection("test");
		Document doc = new Document("name", "MongoDB")
	               .append("type", "database")
	               .append("count", 1)
	               .append("info", new Document("x", 203).append("y", 102));
		collection.insertOne(doc);
		return "success";
	}
	@RequestMapping("/add2")
	@ResponseBody
	public String add2() {
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = database.getCollection("test");
		Document doc = new Document("name", "MongoDB")
				.append("type", "database")
//				.append("count", 1)
				.append("info", new Document("x", 203).append("y", 102));
		collection.insertOne(doc);
		return doc.toJson();
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public String query() {
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = database.getCollection("test");
		Document myDoc = collection.find().first();
		return myDoc.toJson();
	}
	
	
}
