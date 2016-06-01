package com.mongodb;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class App {

	public static void main(String[] args) {
		
		MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
		
		MongoClient client = new MongoClient(new ServerAddress("localhost", 27017), options);
		
		MongoDatabase db = client.getDatabase("test").withReadPreference(ReadPreference.secondary());
		
		MongoCollection<Document> coll = db.getCollection("test");
		
		coll.count();
		
		client.close();
	}
}