package requestUtils;

import java.util.ArrayList;
import java.util.List;

import pojoclasses.AddPlaceReq;
import pojoclasses.Location;

public class RequestPayloads {
	
	public AddPlaceReq addPlaceRequestPayload(String name,String language,String address) {
		
		AddPlaceReq apr = new AddPlaceReq();
		Location l = new Location();
		l.setLat(-44.74384);
		l.setLng(54.434355);
		apr.setLocation(l);
		apr.setAccuracy(100);
		apr.setName(name);
		apr.setPhone_number("(+91) 999 666 4444");
		apr.setAddress(address);
		List ty =new ArrayList();
		ty.add("ALL IN ONE MART");
		ty.add("ONE STOP PLACE");
		ty.add("DINESH VARMA MART");
		apr.setTypes(ty);
		apr.setWebsite("https://mart.east.dinesh.com");
		apr.setLanguage(language);
		
		return apr;
	}

	public String deletePlaceRequestPayload(String placeid) {
		return "{\r\n    \"place_id\":\""+placeid+"\"\r\n}";
	}
}
