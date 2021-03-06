package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Before;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	protected RequestSpecification spec01;
	protected RequestSpecification spec02;
	protected RequestSpecification spec03;
	protected Map<String,String> bookingDatesMap;
	protected Map<String,Object> requestBodyMap;
	protected 	JSONObject jsonBookingDatesBody;
	protected JSONObject jsonRequestBody;

	@Before

	public void setUp01() {

		spec01 = new RequestSpecBuilder().
				                setBaseUri("https://restful-booker.herokuapp.com").
				                build();
	}
	@Before
	public void setUp02() {
		spec02=new RequestSpecBuilder().
				           setBaseUri("http://dummy.restapiexample.com/api/v1/employees").
				           build();
	}

	@Before
	public void setUp03() {
		spec03=new RequestSpecBuilder().
				           setBaseUri("https://jsonplaceholder.typicode.com/todos").
				           build();
	}

	protected Response createRequestBodyByJsonObjectClass() {
		jsonBookingDatesBody = new JSONObject();
		jsonBookingDatesBody.put("checkin", "2020-05-02");
		jsonBookingDatesBody.put("checkout", "2020-05-05");
		
		 jsonRequestBody = new JSONObject();
		jsonRequestBody.put("firstname", "Osman");
		jsonRequestBody.put("lastname", "Aykan");
		jsonRequestBody.put("totalprice", 123);
		jsonRequestBody.put("depositpaid", true);
		jsonRequestBody.put("bookingdates", jsonBookingDatesBody);//bookingdates'in value'su bir Json
		jsonRequestBody.put("additionalneeds", "Wifi");
		
		Response response = given().
	               contentType(ContentType.JSON).
	               spec(spec01).
	               auth().
	               basic("admin", "password123").
	               body(jsonRequestBody.toString()).
	            when().
	               post("/booking");
		
		
		return response;
	}
	protected Response createRequestBodyByMap() {
	   //Map i yukarda olusturduk
		bookingDatesMap=new HashMap<>();
		bookingDatesMap.put("checkin", "2020-05-02");
		bookingDatesMap.put("checkout", "2020-05-05");
		
		//Mapi yine yukarda olusturduk.
		requestBodyMap= new HashMap<>();
		requestBodyMap.put("firstname","Osman");
		requestBodyMap.put("lastname","Aykan");
		requestBodyMap.put("totalprice",123);
		requestBodyMap.put("depositpaid",true);
		requestBodyMap.put("bookingdates",bookingDatesMap);
		requestBodyMap.put("additionalneeds","Wifi");
		
		Response response = given().
			               contentType(ContentType.JSON). // or ???application/json???
			               spec(spec01).
			               auth().
			               basic("admin","password123").
			               body(requestBodyMap).
	                   when().
	                      post("/booking");
		
		
		
		return response;
	}
}
