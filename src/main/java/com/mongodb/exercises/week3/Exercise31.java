package com.mongodb.exercises.week3;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.util.Helpers.printJson;

public class Exercise31 {
	
	private static final String HOMEWORK = "homework";
	
	private static final String SCORES = "scores";
	
	private static final String SCORE = "score";
	
	private static final String TYPE = "type";
	
	
	public static void main(String[] args) {

		MongoClient client = new MongoClient();

		MongoDatabase db = client.getDatabase("school");

		MongoCollection<Document> collection = db.getCollection("students");

		System.out.println("La dimensione iniziale della collection e': " + collection.count());
		
		List<Document> studentList = collection.find().into(new ArrayList<Document>());
		
		System.out.println("La dimensione della lista ordinata e': " + studentList.size());
		
		for(int i = 0; i< studentList.size(); i++){
			
			Document student = studentList.get(i);
			
			int idStudent = student.getInteger("_id");
			
			List<Document> scores = student.get(SCORES, List.class);
			
			int idScoreToBeDeleted = -1;
			
			double scoreToBeDeleted = 100;
			
			for(int j = 0; j< scores.size(); j++){
				
				Document score = scores.get(j);
				
				double currentScore = score.getDouble(SCORE);
				
				String currentType  = score.getString(TYPE);
				
				if(HOMEWORK.equalsIgnoreCase(currentType) && currentScore < scoreToBeDeleted ){
					
					scoreToBeDeleted = currentScore;
					
					idScoreToBeDeleted = j;
				}
			}
			
			scores.remove(idScoreToBeDeleted);
			
			student.remove(SCORES);
			
			student.put(SCORES, scores);
			
			collection.findOneAndReplace(eq("_id", idStudent), student);
		}
		
		for(int i = 0; i< studentList.size(); i++){
			
			Document cur = studentList.get(i);
			
			printJson(cur);
		}
		
		client.close();
	}
}