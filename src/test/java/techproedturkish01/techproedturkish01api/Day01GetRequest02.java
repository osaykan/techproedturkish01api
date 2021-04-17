package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.restassured.response.Response;

public class Day01GetRequest02 {
	/*
	 * Positive Scenario: when() Bir GET Request asagida verilen Endpoint'e yollanir
	 * "https://restful-booker.herokuapp.com/booking/5" and() Accept Type'i
	 * "application/json" dir. then() status code 200 dur and() content type
	 * "application/json"
	 * 
	 */
	@Test
	public void get01() {
		given().accept("application/json").when().get("https://restful-booker.herokuapp.com/booking/5").then()
				.assertThat().statusCode(200).contentType("application/json");

	}

	/*
	 * Negative Scenario: when() Bir GET Request asagida verilen Endpoint’e yollanir
	 * https://restful-booker.herokuapp.com/booking/1001 and() Accept Type’i
	 * “application/json” dir. then() status code 404'dur.
	 */
	@Test
	public void get02() {
		Response response = given().accept("application/json").when()
				.get("https://restful-booker.herokuapp.com/booking/1001");
		response.prettyPrint();// önce görmek icin yazdik

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
		Response response = given().when().get("https://restful-booker.herokuapp.com/booking/1001");
		response.prettyPrint();
		assertEquals(404, response.getStatusCode());
		assertTrue(response.asString().contains("Not Found"));
		assertFalse(response.asString().contains("Suleyman"));

	}

}
