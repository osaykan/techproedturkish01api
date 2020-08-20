package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.restassured.response.Response;

public class Day02GetRequest07 extends TestBase {
	// Among the data there are someones whose first name is “Susan”
	// URL spec01 from TestBase

	@Test
	public void get01() {
		Response response = given().spec(spec01).when().get("/booking?firstname=Susan");
		response.prettyPrint();

		assertTrue(response.getBody().asString().contains("bookingid"));

	}

	// string yazmayi sevmezler.Alternatif olarak
	@Test
	public void get02() {
		// spec01.queryParam("firstname","Susan");
		// spec01.queryParam("depositpaid",true);
		// "/booking?firstname=Susan&depositpaid=true" seklindede olabilir.
		spec01.queryParams("firstname", "Susan", "depositpaid", true);// daha iyi

		Response response = given().spec(spec01).when().get("/booking");
		response.prettyPrint();

		assertTrue(response.getBody().asString().contains("bookingid"));

	}

}
