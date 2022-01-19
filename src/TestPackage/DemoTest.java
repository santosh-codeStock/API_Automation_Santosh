package TestPackage;

import Payloads.demo;
import io.restassured.path.json.JsonPath;

public class DemoTest {

	public static void main(String[] args) {
	int sum=0;
		JsonPath js=new JsonPath(demo.demos());
		
		int courseSize=js.getInt("courses.size()");
		System.out.println(courseSize);
		
		//get price and title
//		for(int i=0;i<courseSize;i++)
//		{
//			String title=js.getString("courses["+i+"].title");
//			String price=js.getString("courses["+i+"].price");
//			System.out.println(title+" - "+price);
//		}
  //verify purchase amount =total amount
		int expected=js.getInt("dashboard.purchaseAmount");
		System.out.println("expected- "+expected);
		for(int i=0;i<courseSize;i++)
			{
				int title=js.getInt("courses["+i+"].price");
				int price=js.getInt("courses["+i+"].copies");
				System.out.println(title*price);
				int total=title*price;
				sum=sum+total;
			}	
		System.out.println(sum);
	}

}
