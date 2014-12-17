package util;

import com.jayway.restassured.RestAssured;

public class RestAssuredUtil {
	public static final void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "";
	}
}
