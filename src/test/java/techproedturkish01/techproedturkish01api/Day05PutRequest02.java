package techproedturkish01.techproedturkish01api;

import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class Day05PutRequest02 extends TestBase{
	/*
	 1)
	 */

	
	@Test
	public void put01() {
		Response response=given().spec(spec03).when().get("/15");
		response.prettyPrint();
		
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("title","Osman");
		jsonObject.put("userId",389);
		jsonObject.put("id",789);   //id yi degistirmedi
		jsonObject.put("completed",false);
		
		Response responseAfterPut=given().
				          contentType(ContentType.JSON).
				          spec(spec03).
				          body(jsonObject.toString()).when().put("/15");
		responseAfterPut.prettyPrint();
		
		responseAfterPut.then().assertThat().statusCode(200);  //statuscode 201 de olabilir problem degil
		
		JsonPath json=responseAfterPut.jsonPath();
		SoftAssert softAssert=new SoftAssert();
		
		//completed degerini verify ediniz
		softAssert.assertEquals(json.getBoolean("completed"), jsonObject.get("completed"));
		
		//title
		softAssert.assertEquals(json.getString("title"), jsonObject.get("title"));
		
		//userId degerini verify ediniz
		softAssert.assertEquals(json.getInt("userId"), jsonObject.get("userId"));
		

		
		
		
		
		softAssert.assertAll();
	}
	
	
}
