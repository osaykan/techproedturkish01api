package techproedturkish01.techproedturkish01api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class Day01GetRequestTekrar {
	/*
	 * Positive Scenario: 
	 * when() Bir GET Request asagida verilen Endpoint'e yollanir
	 * "https://restful-booker.herokuapp.com/booking/5" 
	 * And() Accept Type'i "application/json" dir. 
	 * Then() status code 200 dur 
	 * And() content type
	 * "application/json"
	 */
	@Test
	public void get01() {
		Response response= given().accept("application/json").when().get("https://restful-booker.herokuapp.com/booking/5");
		
		response.prettyPrint();
		
		response.then().assertThat().statusCode(200).contentType("application/json");
		
		
	}

	/*
	 * Negative Scenario: when() Bir GET Request asagida verilen Endpoint’e yollanir
	 * https://restful-booker.herokuapp.com/booking/1001 and() Accept Type’i
	 * “application/json” dir. then() status code 404'dur.
	 */

	@Test
	public void get02() {
		
		Response response=given().
				            accept("application/json").
				            when().get("https://restful-booker.herokuapp.com/booking/1001");
		  response.prettyPrint();
		
		response.then().assertThat().statusCode(404);
		
	}
	
	
	
	/*
	 * Negative Scenario: when() Bir GET Request asagida verilen Endpoint’e yollanir
	 * https://restful-booker.herokuapp.com/booking/1001
	 * 
	 * then() status code 404'dur. and() Response body de "Not Found" var and()
	 * Response body de "Suleyman" yok
	 */

	@Test
	public void get03() {
		
		Response response=given().when().get("https://restful-booker.herokuapp.com/booking/1001");
		
		   response.then().assertThat().statusCode(404);
		   assertTrue(response.asString().contains("Not Found"));
		   assertFalse(response.asString().contains("Süleyman"));
		
	}
	
	/*
	 * Positive Scenario: When I send a GET request to REST API URL
	 * https://restful-booker.herokuapp.com/booking/7 And Accept type is
	 * application/json” Then HTTP Status Code should be 200 
	 * And Response format should be “application/json” 
	 * And first name should be “Jim” 
	 * And lastname should be “Brown” 
	 * And checkin date should be “2015-02-16”
	 *  And checkout date  should be “2017-06-20"
	 */
	@Test
	public void get04() {
		
		Response response=given().accept("application/json").when().get("https://restful-booker.herokuapp.com/booking/7");
		
		response.prettyPrint();
		
		
		   assertEquals(200,response.statusCode());
		   response.then().assertThat().contentType("application/json").
		   body("firstname", Matchers.equalTo("Jim"),"lastname", Matchers.equalTo("Brown"),
				   "bookingdates.checkin", Matchers.equalTo("2016-12-15"),
				   "bookingdates.checkout", Matchers.equalTo("2020-04-15"));
		   
		
		
	}
	
}
