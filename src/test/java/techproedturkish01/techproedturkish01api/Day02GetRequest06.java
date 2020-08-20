package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Day02GetRequest06 extends TestBase {
	// TestBase class olusturup her testte kullanilan datalari TestBase class'a
	// koyup
	// tekrar tekrar ayni seyleri yazmaktan kurtulacagiz.
	// src/main in icine TestBase class olusturacagiz
	// TestBase nin icinde protected RequestSpecification spec01; objesini
	// olusturuyoruz
	// ve import ediyoruz. io hale kirmizi ise projeyi update yapin ve
	/*
	 * <dependency> <groupId>io.rest-assured</groupId>
	 * <artifactId>rest-assured</artifactId> <version>4.3.0</version>
	 * 
	 * dependencieslerde versiondan sonra <scope>test</scope> varsa silin
	 * 
	 * </dependency>
	 * 
	 * oldugunu kontrol edin save yapin. Olmazsa kapa ac yap Extends yap
	 * 
	 */

	/*
	 * 
	 * When I send a GET request to REST API URL
	 * https://restful-booker.herokuapp.com/booking/5 Then HTTP Status Code should
	 * be 200 And response content type is “application/JSON” And response body
	 * should be like; { “firstname”: “Sally”, “lastname”: “Jones”, “totalprice”:
	 * 249, “depositpaid”: false, “bookingdates”: { “checkin”: “2016-04-18",
	 * “checkout”: “2016-11-15" }
	 */

	// get() ici normalde bos ama farkli birsey varsa /booking/5 ilave edilir
	// Matchers.equalTo() daki
	// Matchers leri kaldirmak icin import org.hamcrest.Matchers; basina static
	// sonuna .* koymaliyiz
	@Test
	public void get01() {

		Response response = given().spec(spec01).when().get("/booking/5");

		response.prettyPrint();
		response.then().assertThat().statusCode(200).contentType(ContentType.JSON).body("firstname", equalTo("Sally"),
				"lastname", equalTo("Jones"), "totalprice", equalTo(249), "depositpaid", equalTo(false),
				"bookingdates.checkin", equalTo("2016-04-18"), "bookingdates.checkout", equalTo("2019-07-02"));
	}

}
