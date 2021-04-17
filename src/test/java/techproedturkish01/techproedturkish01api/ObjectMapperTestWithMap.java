package techproedturkish01.techproedturkish01api;

import java.util.HashMap;

import org.junit.Test;

import com.sun.xml.xsom.impl.scd.Iterators.Map;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import utilities.JsonUtil;

public class ObjectMapperTestWithMap extends TestBase  {
	@Test
	public void javaToJson() {
		HashMap<Integer,String> map= new HashMap<>();
		map.put(101,"Ali");
		map.put(102,"Can");
		map.put(103,"Remziye");
		System.out.println(map);//{101=Ali, 102=Can, 103=Remziye}
		
		//Map Java objectini json formatina cevirdik==>Serialization
		String jsonFromMap=JsonUtil.convertJavaToJson(map);
		System.out.println(jsonFromMap);  //{"101":"Ali","102":"Can","103":"Remziye"}
		System.out.println("=========");
	}
	
	
	@Test
	public void jsonToJava() {
		
		Response response = given().
				               spec(spec01).
				            when().
				               get("/booking/3");
		response.prettyPrint();
		
		//API'dan gelen Json formatindaki data'yi Map'e cevirdim ==> De-Serialization
		HashMap<String,Object> jsonToMapApi = JsonUtil.convertJsonToJava(response.asString(),HashMap.class);
		System.out.println("============");
		System.out.println(jsonToMapApi);
		
		/*
		 1)API’dan gelen Json Formatindaki data’yi Map’e cevirdim
		 2)TestCase’de bana verilen data’yi Map’e cevirecegim
		 3)1.adimda olusturdugum Map ile 2.adimda olusturdugum Map’deki data’lari karsilastirarak 
		  verification yapacagim
		 */
		HashMap<String, Object> jsonToMapTestCase = new HashMap<>();
		jsonToMapTestCase.put("firstname", "Jim");
		jsonToMapTestCase.put("lastname", "Jones");
		jsonToMapTestCase.put("totalprice", 764);
		jsonToMapTestCase.put("depositpaid", false);
		
		response.
		   then().
		   assertThat().
		   statusCode(200);
		
		assertEquals(jsonToMapTestCase.get("firstname"),jsonToMapApi.get("firstname"));
		assertEquals(jsonToMapTestCase.get("lastname"),jsonToMapApi.get("lastname"));
		assertEquals(jsonToMapTestCase.get("totalprice"),jsonToMapApi.get("totalprice"));
    	assertEquals(jsonToMapTestCase.get("depositpaid"),jsonToMapApi.get("depositpaid"));

		
	}
	
	
	
	
	
	
	
	
	
	

}
