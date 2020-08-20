package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.given;

import org.junit.Test;

import io.restassured.response.Response;

public class Day01GetRequest01 {
	// Biz Rest-Assured kullanarak API Testing yapacagiz.
	// given(). daki kirmizilik icin import static io.restassured.RestAssured.*;
	// importu gerekli ama otomatik cikmiyor.Manual
	// pom daki her degisiklikten sonra =>save=> proje=>sag tikla=>Maven=>Update
	// Project
	// yapilmali
	// when() den sonra get ,post,put,patch,delete den biri gelir.
	// then dogrulamak icin kullanilir. Method isminin üstüne gelip=>sag=>run yap
	// sol üstte yesil varsa status code 200 demek.
	@Test
	public void getMethod01() {

		given().when().get("https://restful-booker.herokuapp.com/booking").then().assertThat().statusCode(200);

	}

	// get ile aldigim datayi konsolda görmek istiyorum. Response data type olarak
	// alinir
	@Test
	public void getMethod02() {
		Response response = given().when().get("https://restful-booker.herokuapp.com/booking/5");

		response.prettyPrint(); // pretty=>güzel demek. Yazdirmak icin kullaniyoruz.

		// statuscode'u consolede görmek icin
		System.out.println(response.getStatusCode()); // 200

		// statusline'i ekranda yazdirmak icin
		System.out.println("Status Line: " + response.getStatusLine()); // HTTP/1.1 200 OK

		// Response body'deki datanin content(icerik) type
		System.out.println("Content Type 1. Yol: " + response.getContentType());// Content Type: application/json;
																				// charset=utf-8
		System.out.println("Content Type 2.Yol: " + response.getHeader("Content-Type"));

		// Headers taki tüm bilgileri almak icin
		System.out.println("Headers: " + response.getHeaders());

		// Headers dan istenen specific bir datayi almak
		System.out.println(response.getHeader("Date")); // Sun, 16 Aug 2020 18:32:24 GMT

		// Assertion yapalim

		// 1)Status code 200
		// assertThat() kullanirsaniz "Hard Assertion" yapiyorsunuz demektir.
		// Yani ilk hatada code execution durur ve hata raporu verilir.Ilk hatadan
		// sonraki kodlar calismaz
		response.then().assertThat().statusLine("HTTP/1.1 200 OK").contentType("application/json; charset=utf-8")
				.statusCode(200);

	}

}
