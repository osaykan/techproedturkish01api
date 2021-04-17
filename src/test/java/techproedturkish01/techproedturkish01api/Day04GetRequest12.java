package techproedturkish01.techproedturkish01api;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import com.google.gson.Gson;

import io.restassured.response.Response;

public class Day04GetRequest12 extends TestBase {
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
	             get();
         response.prettyPrint();
   //Listlerin icine nonprimitive ler String gibi konulur.Yani Objectler,dolayisiyla Map de yazilir. 
         //Json formatini Java Object ine GSON kullanarak cevirme==> De-Serialization
         List<Map<String,Object>> listofMapsByGson = response.as(ArrayList.class);
         
       System.out.println( listofMapsByGson);
       System.out.println(listofMapsByGson.get(0));
       
   	SoftAssert softAssert = new SoftAssert();
   	//200 tane id oldugunu verify ediniz
	softAssert.assertTrue(listofMapsByGson.size()==200,"Id sayisi istenen gibi degil ");
	softAssert.assertAll();
         
       //121. elemanin completed degerinin true oldugunu verify ediniz
	softAssert.assertEquals(listofMapsByGson.get(120).get("completed"),true,"Istenen gibi degil");
	
	//Sondan bir önceki elemanin title inin "numquam repellendus a magnam" oldugunu verify ediniz
	softAssert.assertEquals(listofMapsByGson.get(listofMapsByGson.size()-2).get("title"),"numquam repellendus a magnam");
	
	softAssert.assertAll();
         

	
	//Java Object ini Json formatina cevirme
	
	Gson gson=new Gson();
	String jsonFromList=gson.toJson(listofMapsByGson);
	System.out.println(jsonFromList);
	//Classtan bir obje olusturup uygun bir method secip Serielizetion yaparim
	
	
		
	}

}
