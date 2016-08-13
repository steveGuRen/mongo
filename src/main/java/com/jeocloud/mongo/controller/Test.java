package com.jeocloud.mongo.controller;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
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
	
	@RequestMapping("/addMultipleDocuments")
	@ResponseBody
	public String addMultipleDocuments() {
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = database.getCollection("test");
		List<Document> documents = new ArrayList<Document>();
		for (int i = 0; i < 100; i++) {
		    documents.add(new Document("i", i));
		}
		collection.insertMany(documents);
		return JSON.toJSONString(documents);
	}
	
	@RequestMapping("/count")
	@ResponseBody
	public String count() {
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = database.getCollection("test");
		return JSON.toJSONString(collection.count());
	}
	
	
	@RequestMapping("/find")
	@ResponseBody
	public String query() {
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = database.getCollection("test");
		Document myDoc = collection.find().first();
		return myDoc.toJson();
	}

	@RequestMapping("/findAll")
	@ResponseBody
	public void findAll() {
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = database.getCollection("test");
		MongoCursor<Document> cursor = collection.find().iterator();
		try {
		    while (cursor.hasNext()) {
		        System.out.println(cursor.next().toJson());
		    }
		} finally {
		    cursor.close();
		}
	}
	
	/**
	 * 这里注意导入包的时候有使用import static com.mongodb.client.model.Filters.*;
	 * 否则，eq("i", 71)将会报错
	 */
	@RequestMapping("/getSingle")
	@ResponseBody
	public void getSingle() {
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = database.getCollection("test");
		Document myDoc = collection.find(eq("i", 71)).first();
		System.out.println(myDoc.toJson());
	}
	
	
	@RequestMapping("/getSet")
	@ResponseBody
	public void getSet() {
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = database.getCollection("test");
		Block<Document> printBlock = new Block<Document>() {
		     @Override
		     public void apply(final Document document) {
		         System.out.println(document.toJson());
		     }
		};
		collection.find(gt("i", 50)).forEach(printBlock);
	}
}
