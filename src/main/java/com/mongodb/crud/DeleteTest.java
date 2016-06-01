package com.mongodb.crud;

import static com.mongodb.util.Helpers.printJson;
import static com.mongodb.client.model.Filters.*;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DeleteTest {

	public static void main(String[] args) {

		MongoClient client = new MongoClient();

		MongoDatabase db = client.getDatabase("course");

		MongoCollection<Document> collection = db.getCollection("test");

		collection.drop();

		//insert 8 documents
		for(int i = 0; i <8; i++){
				collection.insertOne(new Document().append("_id", i));
		}
		
		
		collection.deleteMany(gt("_id", 4));
		

		for(Document cur : collection.find().into(new ArrayList<Document>())){
			printJson(cur);
		}

		client.close();
	}
}