package TestPackage;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlacePojoSerialize;
import Pojo.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTest {

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
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		RequestSpecification res=given().spec(req).body(a);		
		Response responsse=res.when().post("/maps/api/place/add/json")
		.then().spec(resSpec).extract().response();
		
		String responseString=responsse.asString();

		System.out.println("*****Serialize POJO request builder *******> "+responseString);
	}

}
