package com.mongodb.crud;

import static com.mongodb.util.Helpers.printJson;

import java.util.Arrays;
import java.util.Date;

import org.bson.Document;
import org.bson.types.ObjectId;



public class DocumentTest {

	public static void main(String[] args) {
	
		Document document = new Document()
							.append("str", "MongoDB, Hello")
							.append("int", 42)
							.append("l", 1L)
							.append("double", 1.1)
							.append("b", false)
							.append("date", new Date())
							.append("objectId", new ObjectId())
							.append("null", null)
							.append("embeddedDoc", new Document("x", 0))
							.append("list", Arrays.asList(1, 2, 3));
		
		printJson(document);
	}
}