package com.mongodb.exercises.week2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.util.Helpers.printJson;

public class Exercise23 {

	public static void main(String[] args) {

		MongoClient client = new MongoClient();

		MongoDatabase db = client.getDatabase("students");

		MongoCollection<Document> collection = db.getCollection("grades");

		System.out.println("La dimensione iniziale della collection e': " + collection.count());

        for(Document student :collection.find()){

        }



        /*
		//insert 10 documents
		for(int i = 0; i <10; i++){
			collection.insertOne(new Document()
					.append("x", new Random().nextInt(2))
					.append("y", new Random().nextInt(100))
					);
		}

//		Bson filter = new Document("x", 0)
//				.append("y", new Document("$gt", 10).append("$lt", 90));
		
		Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90) );

		List<Document> all = collection.find(filter).into(new ArrayList<Document>());

		for(Document cur : all){
			printJson(cur);
		}

		long count = collection.count(filter);
		System.out.println("Count:");
		System.out.println();
		System.out.println(count);

		*/
		
		client.close();
	}
}