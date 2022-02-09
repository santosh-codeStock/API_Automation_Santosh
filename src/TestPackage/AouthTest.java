package TestPackage;
import static io.restassured.RestAssured.given;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class AouthTest {

	public static void main(String[] args) {
		
		String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWg-4YRIRAoCkKwym1Qmt39DldkDNIn_blKApIcDmQQCIBtTyYYQ5NXqzss7g7sLaA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String code1=url.split("code=")[1];
		String code2=code1.split("&scope=")[0];
		System.out.println(code2);
		
		String ressponse=given().urlEncodingEnabled(false).
		queryParams("code",code2).
		queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").
//		queryParams("state", "verifyfjdss").
//		queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8").
//		queryParams("scope", "https://www.googleapis.com/auth/userinfo.email").
		queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php").
		queryParams("grant_type","authorization_code").
        when().log().all()
        .post("https://www.googleapis.com/oauth2/v4/token").asString();
		System.out.println("***************************"+ressponse);
		
		JsonPath js=new JsonPath(ressponse);
		String accessToken=js.getString("access_token");
		
		System.out.println(accessToken);

		String courseList= given().contentType("application/json").
		queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON).
		when().get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println("^^^^^^^CourseList^^^^^^^^^^^"+courseList);
		

	}

}
