package com.mongodb.exercises.week2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.util.Helpers.printJson;

public class Exercise23 {
	
	private static final String HOMEWORK = "homework";
	
	private static final String STUDENT_ID = "student_id";
	
	private static final String SCORE = "score";
	
	public static void main(String[] args) {

		MongoClient client = new MongoClient();

		MongoDatabase db = client.getDatabase("students");

		MongoCollection<Document> collection = db.getCollection("grades");

		System.out.println("La dimensione iniziale della collection e': " + collection.count());
		
		Bson filter = eq("type", HOMEWORK);
		
		Bson sort = descending(STUDENT_ID, SCORE);
		
		List<Document> filteredAndOrderedBeforeList = collection
												.find(filter)
												.sort(sort)
												.into(new ArrayList<Document>());
		
		System.out.println("La dimensione della lista ordinata e': " + filteredAndOrderedBeforeList.size());
		
		Document previousStudent = null;
		
		Document currentStudent = null;
		
		for(int i = 0; i< filteredAndOrderedBeforeList.size(); i++){
			
			if(i == 0){
				previousStudent = filteredAndOrderedBeforeList.get(0);
			}
			else{
				
				previousStudent = filteredAndOrderedBeforeList.get(i-1);
			}
			
			currentStudent = filteredAndOrderedBeforeList.get(i);
			
		  	int currentStudentId = currentStudent.getInteger(STUDENT_ID, 0);
		  	
		  	int previousStudentId = previousStudent.getInteger(STUDENT_ID, 0);
		  	
		  	if(currentStudentId != previousStudentId){
		  		
		  		collection.deleteOne(previousStudent);
		  	}
		  	else if (i == filteredAndOrderedBeforeList.size() -1){
		  		collection.deleteOne(currentStudent);
		  	}
		}
		
		System.out.println("La dimensione finale della collection e': " + collection.count());
		
		List<Document> filteredAndOrderedAfterList = collection
				.find(filter)
				.sort(sort)
				.into(new ArrayList<Document>());
		
		for(int i = 0; i< filteredAndOrderedAfterList.size(); i++){
			
			Document cur = filteredAndOrderedAfterList.get(i);
			
			printJson(cur);
		}
		
		client.close();
	}
}