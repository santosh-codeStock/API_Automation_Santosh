package TestPackage;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlacePojoSerialize;
import Pojo.Location;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Serialize {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		AddPlacePojoSerialize a=new AddPlacePojoSerialize();
		a.setAccuracy(50);
		a.setAddress("29, side layout, cohen 09");
		a.setLanguage("French-IN");
		a.setPhone_number("(+91) 983 893 3937");
		a.setWebsite("https://rahulshettyacademy.com");
		a.setName("Frontline house");
		List<String> myList=new ArrayList<>();
		myList.add("shoe park");
		myList.add("shop");
		a.setTypes(myList);
		Location l=new Location();
		l.setLng(33.427362);
		l.setLat(-38.383494);
		a.setLocation(l);
			
		String res=given().queryParam("key","qaclick123").body(a)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();

		System.out.println("*****Serialize POJO*******> "+res);
	}

}
