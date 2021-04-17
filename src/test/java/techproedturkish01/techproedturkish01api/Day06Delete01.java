package techproedturkish01.techproedturkish01api;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.restassured.response.Response;

public class Day06Delete01 extends TestBase {
	//Sadece Endpoit yeterli
	
	@Test
	public void delete01() {
		Response responseBeforeDelete=given().
				                    spec(spec03).
				                    when().
				                    get("/19");
		responseBeforeDelete.prettyPrint();
		
		Response responseAfterDelete=given().
				                  spec(spec03).
				                  when().
				                  delete("/19");
		responseAfterDelete.prettyPrint();
		
		//sildikten sonra tekrar almaya calisalim
		Response getResponseBeforeDelete=given().
                spec(spec03).
                when().
                get("/19");
          responseBeforeDelete.prettyPrint();
		
          responseAfterDelete.then().assertThat().statusCode(200);
          
          //asString stringe cevirir
          //toString string olarak bilgi cekeriz
		
	}

}
