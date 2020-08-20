package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.response.Response;

public class Day01GetRequest03 {
	/*
	 * Positive Scenario: When I send a GET request to REST API URL
	 * https://restful-booker.herokuapp.com/booking/7 And Accept type is
	 * application/json” Then HTTP Status Code should be 200 And Response format
	 * should be “application/json” And first name should be “Sally” And lastname
	 * should be “Jackson” And checkin date should be “2015-02-16” And checkout date
	 * should be “2017-06-20"
	 */

	@Test
	public void get01() {
		Response response = given().accept("application/json").when()
				.get("https://restful-booker.herokuapp.com/booking/7");
		response.prettyPrint();

		// Then HTTP Status Code should be 200
		// And Response format should be “application/json”
		// 1.yol
		response.then().assertThat().statusCode(200).contentType("application/json")
				.body("firstname", Matchers.equalTo("Sally")).body("lastname", Matchers.equalTo("Jackson"))
				.body("totalprice", Matchers.equalTo(5000)).body("depositpaid", Matchers.equalTo(true))
				.body("bookingdates.checkin", Matchers.equalTo("2015-02-03"))
				.body("bookingdates.checkout", Matchers.equalTo("2015-03-12"))
				.body("additionalneeds", Matchers.equalTo("BALIK_EKMEK_LIMON_TURSU_ve_Baklavada_Yemiyek_mi"));

		// Tekrarli body kullanmadan
		response.then().assertThat().statusCode(200).contentType("application/json").body("firstname",
				Matchers.equalTo("Sally"), "lastname", Matchers.equalTo("Jackson"), "totalprice",
				Matchers.equalTo(5000), "depositpaid", Matchers.equalTo(true), "bookingdates.checkin",
				Matchers.equalTo("2015-02-03"), "bookingdates.checkout", Matchers.equalTo("2015-03-12"),
				"additionalneeds", Matchers.equalTo("BALIK_EKMEK_LIMON_TURSU_ve_Baklavada_Yemiyek_mi"));

		// 2.yol
		assertEquals(200, response.statusCode());

	}

}
