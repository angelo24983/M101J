package com.mongodb.sparksamples;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SparkFormHandling {

	public static void main( String[] args ) {

		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");

		//serve per configurare la pagina iniziale con il form
		Spark.get("/", new Route() {

			@Override
			public Object handle(Request arg0, Response arg1) throws Exception {

				try {

					Template fruitPickerTemplate = configuration.getTemplate("fruitPicker.ftl");
					Map<String, Object> fruitsMap = new HashMap<String, Object>();
					fruitsMap.put("fruits",
							Arrays.asList("mela", "arancia", "banana", "pesca"));
					StringWriter writer = new StringWriter();
					fruitPickerTemplate.process(fruitsMap, writer);

					return writer;

				} catch (Exception e) {
					Spark.halt(500);
					return null;
				}
			}
		});

		//serve per configurare il salvataggio della selezione nel form
		Spark.post("/favorite_fruit", new Route() {

			@Override
			public Object handle(Request arg0, Response arg1) throws Exception {

				final String fruit = arg0.queryParams("fruit");

				if(fruit == null){

					return "Nessun frutto selezionato";
				}
				else{

					return "Il frutto selezionato e': " + fruit;
				}
			}
		});
	}
}