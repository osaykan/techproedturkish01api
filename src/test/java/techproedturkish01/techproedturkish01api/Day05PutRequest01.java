package techproedturkish01.techproedturkish01api;

import org.json.JSONObject;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class Day05PutRequest01 extends TestBase {
	//Var olan datayi degistirecegiz
	//put kullanirsak endpoint ve body lazim. Bazen contentType de gerekebilir
	
	@Test
	public void put01() {
		//Önce datayi alalim sonra degisikligi görelim
		Response response= given().spec(spec03).when().get("/20");
		response.prettyPrint();
		
		
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("title","Osman");
		jsonObject.put("userId",389);
		jsonObject.put("id",789);   //id yi degistirmedi
		jsonObject.put("completed",false);
		
		Response responseAfterPut=given(). //contentType olmadan calismadi
				contentType(ContentType.JSON).spec(spec03).
				body(jsonObject.toString()).when().put("/20");
		
		responseAfterPut.prettyPrint();
	}

}
