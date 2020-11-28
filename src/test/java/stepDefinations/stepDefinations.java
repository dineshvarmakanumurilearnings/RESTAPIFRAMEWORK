package stepDefinations;

import Utils.APIResources;
import Utils.Utils;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import junit.framework.AssertionFailedError;
import pojoclasses.AddPlaceResponse;
import pojoclasses.DeletePlaceResponse;
import pojoclasses.GetPlaceResponse;
import requestUtils.RequestPayloads;

public class stepDefinations extends Utils {
	
	RequestPayloads rp =new RequestPayloads();
	Response res;
	AddPlaceResponse addplaceResponse ;
	GetPlaceResponse getplaceResponse;
	DeletePlaceResponse deleteplaceResponse;
	RequestSpecification request;
	static String place_Id;
	
	@Given("user is with the request payload {string} {string} {string}")
	public void user_is_with_the_request_payload(String name, String language, String address) throws IOException {
	   
		request = given().spec(request_Specification()).body(rp.addPlaceRequestPayload(name, language, address));
	}
	
	@Given("user is with DeletePlaceAPI")
	public void user_is_with_delete_place_api() throws IOException {
		request= given().spec(request_Specification()).body(rp.deletePlaceRequestPayload(place_Id));
	}

	@When("user calls {string} with {string} http method")
	public void user_calls_with_http_method(String apiName, String method) throws IOException {
		//Here we use Enum concept to shrink code, so we get the resource of APIs Using ENUM
		APIResources apiResource = APIResources.valueOf(apiName);
		String resource=apiResource.getresource();
		//now we got the resource based on the apiName parameter
		
		if(method.equalsIgnoreCase("POST"))
	            res = request.when().post(resource);
		else if(method.equalsIgnoreCase("DELETE"))
			 res = request.when().post(resource);
		else if (method.equalsIgnoreCase("PUT"))
			 res = request.when().put(resource);
		else if (method.equalsIgnoreCase("GET"))
		 res = given().spec(request_Specification()).queryParam("place_id", place_Id).when().get(resource);
	}

	@Then("{string} call should pass with statuscode {int}")
	public void call_should_pass_with_statuscode(String resource, Integer int1) {
		
		if(resource.equals("AddPlaceAIP")) {
			addplaceResponse = res.then().spec(response_Specification()).extract().as(AddPlaceResponse.class);
			place_Id=addplaceResponse.getPlace_id();
			assertEquals(200,200);
		}
		else if(resource.equals("GetPlaceAPI")) {
		 getplaceResponse = res.then().spec(response_Specification()).extract().as(GetPlaceResponse.class);
			assertEquals(200,200);
		}
		else if(resource.equals("DeletePlaceAPI")) {
			deleteplaceResponse = res.then().spec(response_Specification()).extract().as(DeletePlaceResponse.class);
				assertEquals(200,200);
			}
		 
	}

	@And("{string} in response must be {string}")
	public void in_response_must_be(String string, String string2) {
		if(string.equals("status")) {
			assertEquals(addplaceResponse.getStatus(),string2);
			System.out.println(addplaceResponse.getStatus());
		}
		else if(string.equals("scope")){
			assertEquals(addplaceResponse.getScope(),string2);
			System.out.println(addplaceResponse.getScope());
		}
		else if(string.equals("name")){
			
			assertEquals(getplaceResponse.getName(),string2);
		}
		if(string.equals("delete-status")) {
			System.out.println(deleteplaceResponse.getStatus());
			System.out.println(string2);
			assertEquals(deleteplaceResponse.getStatus(),string2);
			
		}
		
		
		
		
		
	}

}
