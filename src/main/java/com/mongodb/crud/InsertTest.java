package com.mongodb.crud;

import static com.mongodb.util.Helpers.printJson;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class InsertTest {

	public static void main(String[] args) {
		
		MongoClient client = new MongoClient();
		
		MongoDatabase db = client.getDatabase("course").withReadPreference(ReadPreference.secondary());
		
		MongoCollection<Document> coll = db.getCollection("insertTest");
		
		coll.drop();
		
		Document smith = new Document()
				.append("name", "Smith")
				.append("age", 30)
				.append("profession", "programmer");
		
		Document jones = new Document()
				.append("name", "Jones")
				.append("age", 25)
				.append("profession", "hacker");
		
		printJson(smith);
		
		coll.insertMany(Arrays.asList(smith, jones));
		
		printJson(smith);
		printJson(jones);
		
		client.close();
	}
}