package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.given;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class Day03GetRequest10 extends TestBase {
	/*
	 When I send GET Request to URL
	 http://dummy.restapiexample.com/api/v1/employees
	 Then
	  Status code is 200
	  1)10'dan buyuk tum id’leri console’a yazdir
	  10'dan buyuk 14 tane id oldugunu verify et
	  2)30'dan kucuk tum yaslari console’a yazdir
	  30 dan kucuk en buyuk yasin 23 oldugunu verify et
	  3)Maasi 350000'den cok olan iscilerin isimlerini console’a yazdir
	  Charde Marshall’in maasinin 350000'den buyuk oldugunu verify et
	 
	 */
	
	@Test
	public void get01() {
	Response response=given().spec(spec02).
	          when().get();
		response.prettyPrint();
		      response.
		        then().
		        assertThat().
		          statusCode(200);
		   JsonPath json =response.jsonPath(); 
		   SoftAssert softAssert=new SoftAssert();
		      //1)10'dan buyuk tum id’leri console’a yazdir										it=data
		     List<String> idList =json.getList("data.findAll{Integer.valueOf(it.id)>10}.id");//it(o)==this gibi
		     System.out.println(idList);
		//verify
		     softAssert.assertEquals(idList.size(), 14,"Eleman sayisi istenen gibi degil");
		    
		   
		     //  2)30'dan kucuk tum yaslari console’a yazdir
			  //30 dan kucuk en buyuk yasin 23 oldugunu verify et

		     List<String> yasList=json.getList("data.findAll{Integer.valueOf(it.employee_age)<30}.employee_age");
       System.out.println(yasList);
       Collections.sort(yasList);//[19,21,22,22,23,23]
       softAssert.assertTrue(yasList.get(yasList.size()-1).equals("23"),"Yas istenen gibi degil");
      // softAssert.assertAll();
       
       //  3)Maasi 350000'den cok olan iscilerin isimlerini console’a yazdir
 	 // Charde Marshall’in maasinin 350000'den buyuk oldugunu verify et
       List<String> nameList=json.
    		   getList("data.findAll{Integer.valueOf(it.employee_salary)>350000}.employee_name");
       System.out.println(nameList);
       softAssert.assertTrue(nameList.contains("Charde Marshall"),"Maas listesinde yok");
       softAssert.assertAll();
       
       
       
       
	}
	
	

}
