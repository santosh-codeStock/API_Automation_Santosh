package Payloads;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class LibraryAPI {
	
//without parameter	
//	@Test
//	public void addBook()
//	{
//		RestAssured.baseURI="http://216.10.245.166";
//		String response=given().header("Content-Type","application/json").body(AddPayload.addBook())
//		.when().post("/Library/Addbook.php")
//		.then().log().all().assertThat().statusCode(200).extract().response().asString();
//		
//		JsonPath js=new JsonPath(response);
//		String id=js.getString("ID");
//		System.out.println("id is== "+id);
//	}
	
//-------- with parameter-----------
//	@Test
//	public void addBook()
//	{
//		RestAssured.baseURI="http://216.10.245.166";
//		String response=given().header("Content-Type","application/json").body(AddPayload.addBook("bcdfrt","227276"))
//		.when().post("/Library/Addbook.php")
//		.then().log().all().assertThat().statusCode(200).extract().response().asString();
//		
//		JsonPath js=new JsonPath(response);
//		String id=js.getString("ID");
//		System.out.println("id is== "+id);
//	}
	
///-----using object array (mainly for static json)--------
	@Test(dataProvider="testDataApi")
	public void addBook(String isbn,String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().header("Content-Type","application/json").body(AddPayload.addBook(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js=new JsonPath(response);
		String id=js.getString("ID");
		System.out.println("id is== "+id);
	}
	
	@DataProvider(name="testDataApi")
	public Object[][] testData()
	{
		return new Object[][] {{"abvfr","1678"},{"arvfr","1608"},{"abyfr","1478"}};
	}
}
