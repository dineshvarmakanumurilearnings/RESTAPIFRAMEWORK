package stepDefinations;


import java.io.IOException;

import io.cucumber.java.Before;


public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		stepDefinations sd = new stepDefinations();
		
		if(stepDefinations.place_Id == null) {
			
			sd.user_is_with_the_request_payload("Agusta", "French", "Wall Street");
			sd.user_calls_with_http_method("AddPlaceAIP", "POST");
			sd.call_should_pass_with_statuscode("AddPlaceAIP", 200);
		}
	}

}
