package Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;




public class Utils {

	
	public static RequestSpecification reqSpecBuilder;
	ResponseSpecification resSpecBuilder;
	
	public RequestSpecification request_Specification() throws IOException {
		//this if condition is used to avoid logs overriding.
		if(reqSpecBuilder==null)
		{
		
		PrintStream stream = new PrintStream(new FileOutputStream("logging.txt"));
		  reqSpecBuilder = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI")).addQueryParam("key", "qaclick123")
				  .addFilter(RequestLoggingFilter.logRequestTo(stream)).addFilter(ResponseLoggingFilter.logResponseTo(stream))
				  .setContentType(ContentType.JSON).build();
		
		return reqSpecBuilder;
		}
		return reqSpecBuilder;
	}
	
    public ResponseSpecification response_Specification() {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		 resSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200).build();
		
		return resSpecBuilder;
	}
    
    
    public static String getGlobalValue(String key) throws IOException {
    	Properties prop = new Properties();
    	FileInputStream fis = new FileInputStream("E:\\New folder\\RESTAPIFramework\\config.properties");
    	prop.load(fis);
    	
    	String key1 = prop.getProperty(key);
    	
    	return key1;
    	
    }
}
