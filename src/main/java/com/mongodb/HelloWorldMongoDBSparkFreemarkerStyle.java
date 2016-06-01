package com.mongodb;

import java.io.StringWriter;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldMongoDBSparkFreemarkerStyle {

	public static void main( String[] args ) {

		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldMongoDBSparkFreemarkerStyle.class, "/");
		
		MongoClient client = new MongoClient();
		
		MongoDatabase database = client.getDatabase("course");
		final MongoCollection<Document> collection = database.getCollection("hello");
		
		collection.drop();
		
		collection.insertOne(new Document("name", "MongoDB"));

		Spark.get("/", new Route() {

			@Override
			public Object handle(Request arg0, Response arg1) throws Exception {

				StringWriter writer = new StringWriter();

				try {

					Template helloTemplate = configuration.getTemplate("hello.ftl");
					
					Document document = collection.find().first();
					
					helloTemplate.process(document, writer);

					System.out.println(writer);

				} catch (Exception e) {
					Spark.halt(500);
					e.printStackTrace();
				}

				return writer;
			}
		});
	}
}