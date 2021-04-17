package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import com.google.gson.Gson;

import io.restassured.response.Response;

public class Day04GetRequest11 extends TestBase{
	/*
	 GSON: GSON,1) Json formatindaki datalari Java objectlerine dönusturur.(DE-Serialization)
	 			2) Java Objectlerini Json formatindaki datalara donusturur.(Serialization)
	 	Note: GSON ile ayni isi yapan ObjectMapper class'da vardir.
	 	
	 */
	
	@Test
	public void get01() {
		Response response= given().
				             spec(spec03).
				          when().
				             get("/2");
		response.prettyPrint();
		/*
		 {
    "userId": 1,
    "id": 2,
    "title": "quis ut nam facilis et officia qui",
    "completed": false
}  Json formatinda ve biz HashMap'e cevirecegiz
		 */
		HashMap<String,String> map=response.as(HashMap.class);//De-Serialization
		System.out.println(map);
		//{id=2.0, completed=false, title=quis ut nam facilis et officia qui, userId=1.0}
		System.out.println(map.keySet());//[id, completed, title, userId]
		System.out.println(map.values());//[2.0, false, quis ut nam facilis et officia qui, 1.0]
		
		//completed key inin degerinin false oldugunu verify edin
		SoftAssert softAssert= new SoftAssert();
		softAssert.assertEquals(map.get("completed"),false,"false olmaliydi ama degil");
		
		
		//userId, id, ve title degerlerini verify ediniz
		softAssert.assertEquals(map.get("userId"),1);
		softAssert.assertEquals(map.get("id"),2);
		softAssert.assertEquals(map.get("title"),"quis ut nam facilis et officia qui");
		softAssert.assertAll();
		
		
		System.out.println("************");
		////Java Object ini Json formatina cevirme   Serialization
		
		Gson gson=new Gson();
		String jsonFromMap=gson.toJson(map);
		System.out.println(jsonFromMap);
		
	}
	

}
