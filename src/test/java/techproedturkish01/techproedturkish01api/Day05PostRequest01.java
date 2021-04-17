package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.given;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Day05PostRequest01 extends TestBase {
	/*
	 Post Request olusturmak icin gerekenler:
	      1)EndPoint
	      2)Request body
	      3)Authorization
	      4)Accept Type istege baglidir bazen kullanilir bazen kullanilmaz
	      5)Content Type istege baglidir bazen kullanilir bazen kullanilmaz
	      
	      GET ile POST Requestlerin farklari nedir?
	      (Önce get i anlat. Geti data cekmek icin kullaniriz. Endpoint yeterlidir.
	       Post yeni bir data olusturmak icin kullanilir.Endpoint ve body lazim...)
	       1)GET Request olusturmak icin sadece Endpoint yeterlidir, ama POST Request olusturmak icin Endpoint yaninde
	         Request body de gereklidir.
	       2)GET data cekmek icin, POST yeni data olusturmak icin kullanilir.
	       
	        NOTE: API Developerlar olusturulacak datanin bazi bölümlerinin bos birakilmamasina karar vermislerse
	              POST Request olustururken kesinlikle o kisimlara deger verilerek POST Request olusturulmalidir.
	              Eger buna dikkat etmezseniz 400 Bad Request status code alirsiniz
	        NOTE: API Developerlar olusturulacak datanin bazi bölümlerinin tekrarli olmamasina karar vermislerse
	              POST Request olustururken kesinlikle o kisimlara tekrarli degerler verilerek POST Request 
	              olusturulmamalidir. Eger buna dikkat etmezseniz 400 Bad Request status code alirsiniz      
	
	 
	 
					 POST Scenario:
				 Accept type Json olsun (Whenden önce yazilmissa Content Type demektir)
				 When 
				 POST request yolladigimda
				 1) https://restful-booker.herokuapp.com/booking
				 2) Request Body 
				 {
				 "firstname": "Osman",
				 "lastname": "Aykan",
				 "totalprice": 123,
				 "depositpaid": true,
				 "bookingdates": {
				 "checkin": "2020-05-02",
				 "checkout": "2020-05-05"
				 },
				 "additionalneeds": "Wifi"
				 }
				 Then 
				 Status Code 200 olmali
				 Response Body nin, Request Body ile ayni oldugunu verify ediniz.
					 
					 
					 */
	@Test
	public void post01() {
	//1.Way: Iyi degil
	String jsonRequestBody ="{\n" + 
			"\"firstname\": \"Osman\",\n" + 
			"\"lastname\": \"Aykan\",\n" + 
			"\"totalprice\": 123,\n" + 
			"\"depositpaid\": true,\n" + 
			"\"bookingdates\": {\n" + 
			"\"checkin\": \"2020-05-02\",\n" + 
			"\"checkout\": \"2020-05-05\"\n" + 
			"},\n" + 
			"\"additionalneeds\": \"Wifi\"\n" + 
			"}";
	
	Response response = given().                            //get de acceptType() kullaniyoruz. Kabul etmek
						  contentType(ContentType.JSON).   //post kullanirken contentType() yazacagiz. Icerik tipi
						  spec(spec01).
						  auth().
						  basic("admin","password123").
						  body(jsonRequestBody).
						  when().
						  post("/booking");

	response.prettyPrint();
		
			response.then().assertThat().statusCode(200);
			
			//JsonPath kullanarak assertion yapalim
			JsonPath json=response.jsonPath();
			SoftAssert softAssert = new SoftAssert();
			//firstname assertion
			
	softAssert.assertEquals(json.getString("booking.firstname"),"Osman");
	softAssert.assertEquals(json.getString("booking.lastname"),"Aykan");
	softAssert.assertEquals(json.getInt("booking.totalprice"),123);
	softAssert.assertEquals(json.getBoolean("booking.depositpaid"),true);
	softAssert.assertEquals(json.getString("booking.bookingdates.checkin"),"2020-05-02");
	softAssert.assertEquals(json.getString("booking.bookingdates.checkout"),"2020-05-05");
	softAssert.assertEquals(json.getString("booking.additionalneeds"),"Wifi");
	
	
	softAssert.assertAll();
	
	
	
	
	
	}
	

}
