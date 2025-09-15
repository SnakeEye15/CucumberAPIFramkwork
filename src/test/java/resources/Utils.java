package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    public static RequestSpecification req;
   // public static String place_id; // Global storage for place_id

    public RequestSpecification requestSpecification() throws IOException {
        if(req ==null)
        {
        PrintStream log= new PrintStream(new FileOutputStream("Logging.txt"));
        req= new RequestSpecBuilder().setBaseUri(getGlobal("BaseUri")).addQueryParam("key","qaclick123")
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType(ContentType.JSON).build();
        return req;
        }
        return req;
    }


    public static String getGlobal(String Key) throws IOException {
        Properties prop= new Properties();
        FileInputStream file= new FileInputStream("/Users/dheerajv/IdeaProjects/CucumberAPIFramkwork/src/test/java/resources/global.properties");
        prop.load(file);
        return prop.getProperty(Key);
    }

    public String getJsonPath(Response response, String key) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }



}
