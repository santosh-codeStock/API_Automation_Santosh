package TestPackage;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import Payloads.AddPayload;

public class BasicApiClass {

	public static void main(String[] args) {
		//Add place api
	RestAssured.baseURI="https://rahulshettyacademy.com";
	
//	given().queryParam("key", "qaclick123").header("content-type","application/json")
//	.body(AddPayload.addPlacePayload()).when().post("/maps/api/place/add/json").then().log().all().statusCode(200);
	
	String response=given().log().all().queryParam("key", "qaclick123").header("content-type","application/json")
	.body(AddPayload.addPlacePayload())
	.when().post("/maps/api/place/add/json")
	.then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
	System.out.println(response);

	JsonPath js=new JsonPath(response);
	String placeID=js.getString("place_id");
	System.out.println("place ID -> "+placeID);
	
	//update place
	String newAddress="70 winter walk, USA TEST";
	given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID).header("content-type","application/json")
	.body("{\r\n"
			+ "\"place_id\":\""+placeID+"\",\r\n"
			+ "\"address\":\""+newAddress+"\",\r\n"
			+ "\"key\":\"qaclick123\"\r\n"
			+ "}\r\n"
			+ "")
	.when().put("/maps/api/place/update/json")
	.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"))
	.extract().response().asString();
	
	//get the updated place
	String addressRes=given().log().all().queryParam("key", "qaclick123")
	.queryParam("place_id", placeID)
	.when().get("/maps/api/place/get/json")
	.then().assertThat().statusCode(200).extract().response().asString();
	
	JsonPath js1=new JsonPath(addressRes);
	String actualAddress=js1.getString("address");
	System.out.println("actual address*************"+actualAddress);
	System.out.println("actual address****########**"+newAddress);
	Assert.assertEquals(actualAddress, newAddress);
	}

}
