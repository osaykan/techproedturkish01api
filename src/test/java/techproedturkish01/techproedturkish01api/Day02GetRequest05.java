package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Day02GetRequest05 {
	/*
	 * 
	 * 
	 * When I send a GET request to REST API URL
	 * https://restful-booker.herokuapp.com/booking/5 Then HTTP Status Code should
	 * be 200 And response content type is “application/JSON” And “firstname” should
	 * be “Mark”, And “totalprice” should be 892 And “checkin” should be
	 * “2018-03-21”
	 * 
	 * 
	 */

	@Test
	public void get01() {
		Response response = given().when().get("https://restful-booker.herokuapp.com/booking/5");

		response.prettyPrint();

		response.then().assertThat().statusCode(200).contentType(ContentType.JSON)
				.body("firstname", Matchers.equalTo("Mark")).body("totalprice", Matchers.equalTo(892))
				.body("bookingdates.checkin", Matchers.equalTo("2018-03-21"));

	}

}
