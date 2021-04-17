package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class Day03GetRequest08 extends TestBase{
    
    /*
     When I send a GET request to REST API URL
     * https://restful-booker.herokuapp.com/booking/5
     * Then HTTP Status Code should be 200
     * And response content type is “application/JSON”
     * And response body should be like;
     * {
     * “firstname”: “Sally”, 
     * “lastname”: “Ericsson”, 
     * “totalprice”: 111,
     * “depositpaid”: false, 
     * “bookingdates”: { 
     *                  “checkin”: “2017-05-23", 
     *                  “checkout”:“2019-07-02" }
     * }
     */
    @Test
    public void get01() {
    	spec01.pathParam("bookingid", 5);  //"/booking/5" yerine yazildi
        
        Response response = given().
                                spec(spec01).
                            when().get("/booking/{bookingid}");
        response.prettyPrint();
        
        JsonPath json = response.jsonPath();
        
        System.out.println(json.getString("firstname"));// Susan
        //assertEquals("firstname istenilen gibi degil", "Jim", json.getString("firstname"));
        
        System.out.println(json.getString("lastname"));// Jackson
        //assertEquals("lastname stenilen gibi degil", "Jones", json.getString("lastname"));
        
        System.out.println(json.getInt("totalprice"));// 367
        //assertEquals("TotalPrice istenilen gibi degil",596,json.getInt("totalprice"));
        
        System.out.println(json.getBoolean("depositpaid")); // false
        //assertEquals("Depozitpaid istenilen gibi degil",false,json.getBoolean("depozitpaid"));
        
        System.out.println(json.getString("bookingdates"));// bookingdates.checkin dersek checkin tarihini verir.
        assertEquals("Booking dates istenilen gibi degil", "2019-05-18", json.getString("bookingdates.checkin"));
    }
}









