package Utils;

public enum APIResources {
	//Enum is a special class in java that contains collection of constants and methods
	//and methods are separated by comma and at the last method we use semi-colon 
	//we need to declare a constructor with argument same as method parameter type
	AddPlaceAIP("/maps/api/place/add/json"),
	DeletePlaceAPI("/maps/api/place/delete/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	UpdatePlaceAPI("/maps/api/place/update/json");
	
	String resource;
	
	
	APIResources(String resource){
		this.resource=resource;
	}
	
	
	public String  getresource() {
		return this.resource;
	}

}
