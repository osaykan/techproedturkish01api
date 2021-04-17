package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Day03GetRequest09 extends TestBase{
	
	
	
	@Test
	public void get01() {
		Response response=given().spec(spec02).
				          when().get();
		response.prettyPrint();
		
		//JsonPath objesi olusturun
		JsonPath json=response.jsonPath();
		//tüm employee isimlerini console'a yazdiriniz
		System.out.println(json.get("data.employee_name"));//json.getList("data.employee_name") olabilirdi
		
		//Ikinci iscinin(index 1) isminin Garret Winters oldugunu verify(soft assertion) ediniz
		//data bir list oldugundan istenen elemana index ile ulasiriz
		 assertEquals("Isim istenen gibi degil","Garrett Winters",json.getString("data[1].employee_name"));//hard assertion
		/*
		 Soft Assertion icin 3 adim takip edilmelidir:1) SoftAssert classindan bir obje(softAssert) olustur
		 											  2) Objeyi (softAssert) kullanarak assertion yap
		 											  3) softAssert.assertAll();
		 */
		
		
		 SoftAssert softAssert=new SoftAssert();
		 softAssert.assertEquals(json.getString("data[1].employee_name"), "Garrett Winters","Isim istenen gibi degil");
		//softAssert.assertAll();
		
		 //Iscilerin arasinda Herrod Chandler'in var oldugunu "verify" ediniz.
		softAssert.assertTrue(json.getList("data.employee_name").contains("Herrod Chandler"),"Herrod Chandler yok");
		//softAssert.assertAll();
		
		softAssert.assertEquals(json.getList("data.id").size(),24,"24 isci yok");
		//softAssert.assertAll();
		
		//7.iscinin maasinin 137500 oldugunu verify ediniz
		softAssert.assertEquals(json.getString("data[6].employee_salary"),"137500","Maas istenen gibi degil degil");
		softAssert.assertAll();
	}

}
