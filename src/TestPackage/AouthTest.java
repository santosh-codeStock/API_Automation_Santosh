package TestPackage;
import static io.restassured.RestAssured.given;

import java.util.List;

import Pojo.GetCourse;
import Pojo.WebAutomation;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class AouthTest {

	public static void main(String[] args) {
		
		String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWi1lUHfqldrmdd10VERPLFYXt2eVBbhVMvpRkfwePi0wLNRjCwBWuFC6mZD7M90ow&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String code1=url.split("code=")[1];
		String code2=code1.split("&scope=")[0];
		System.out.println(code2);
		
		String ressponse=given().urlEncodingEnabled(false).
		queryParams("code",code2).
		queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").
		queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php").
		queryParams("grant_type","authorization_code").
        when().log().all()
        .post("https://www.googleapis.com/oauth2/v4/token").asString();
		System.out.println("***************************"+ressponse);
		
		JsonPath js=new JsonPath(ressponse);
		String accessToken=js.getString("access_token");
		
		System.out.println("ACCESS TOKEN---> "+accessToken);
//************without POJO*************
//		String courseList= given().contentType("application/json").
//		queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON).
//		when().get("https://rahulshettyacademy.com/getCourse.php").asString();
//		System.out.println("^^^^^^^CourseList^^^^^^^^^^^"+courseList);
		
//***WITH POJO**********************************
		GetCourse gc= given().contentType("application/json").
				queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON).
				when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
		System.out.println("instructor name-> "+gc.getInstructor());
//get value of nesteed json from response
		gc.getCourses().getWebAutomation().get(1).getCourseTitle();
	List<WebAutomation> listWebAuto=gc.getCourses().getWebAutomation();
	for(int i=0;i<listWebAuto.size();i++)
	{
		System.out.print("WebAutomation course title & price -> "+listWebAuto.get(i).getCourseTitle()+" || "+listWebAuto.get(i).getPrice());
		System.out.println();
	}
		
	}

}
