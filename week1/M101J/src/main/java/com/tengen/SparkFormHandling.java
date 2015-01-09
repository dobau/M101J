package com.tengen;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dobau on 08/01/2015.
 */
public class SparkFormHandling {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");

        Spark.get("/", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                StringWriter writer = new StringWriter();

                try {
                    Template fruitTemplate = configuration.getTemplate("fruitPicker.ftl");

                    Map<String, Object> fruitMap = new HashMap<String, Object>();
                    fruitMap.put("fruits", Arrays.asList("banana", "orange", "apple", "grape"));

                    fruitTemplate.process(fruitMap, writer);
                } catch(Exception e) {
                    Spark.halt(500);
                    e.printStackTrace();
                }

                return writer;
            }
        });

        Spark.post("/favorite_fruit", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String fruit = request.queryParams("fruit");

                if (fruit == null) {
                    return "Why don't pick one?";
                } else {
                    return "Your favorite fruit is "+fruit;
                }
            }
        });
    }

}
