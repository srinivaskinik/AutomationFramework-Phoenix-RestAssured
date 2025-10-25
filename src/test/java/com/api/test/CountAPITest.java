package com.api.test;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static io.restassured.RestAssured.*;

public class CountAPITest {
	
	@Test
	public void verifyCountAPIResponse() {
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.header("Authorization",AuthTokenProvider.getToken(Role.FD))
		.log().uri()
		.log().headers()
		.log().method()
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(200)
		.body("message",equalTo("Success"))
		.time(lessThan(1000L))
		.body("data", notNullValue())
		.body("data.size()",equalTo(3))
		.body("data.count", everyItem(greaterThanOrEqualTo(0)))
		.body("data.label", not(blankOrNullString()))
		.body("data.key", containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
		.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"));
	}
	
	@Test
	public void countAPITest_MissingAuthToken() {
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.log().uri()
		.log().headers()
		.log().method()
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(401);
	}

}
