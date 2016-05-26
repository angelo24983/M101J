package com.mongodb.sparksamples;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkRoutes {

	public static void main( String[] args ) {

		Spark.get("/", new Route() {

			public Object handle(Request arg0, Response arg1) throws Exception {

				return "Hello World \n";
			}
		});

		Spark.get("/test", new Route() {

			public Object handle(Request arg0, Response arg1) throws Exception {

				return "This is a test page \n";
			}
		});

		Spark.get("/echo/:thing", new Route() {

			public Object handle(Request arg0, Response arg1) throws Exception {

				return arg0.params(":thing");
			}
		});
	}
}