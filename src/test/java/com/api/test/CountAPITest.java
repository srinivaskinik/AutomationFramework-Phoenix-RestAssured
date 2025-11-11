package com.api.test;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

public class CountAPITest {
	
	@Test(description = "Verify if the count API is giving correct response",groups = {"api","smoke","regression"})
	public void verifyCountAPIResponse() {
		given()
		.spec(requestSpecWithAuth(FD))
		.when()
		.get("/dashboard/count")
		.then()
		.spec(responseSpec_OK())
		.body("data", notNullValue())
		.body("data.size()",equalTo(3))
		.body("data.count", everyItem(greaterThanOrEqualTo(0)))
		.body("data.label", not(blankOrNullString()))
		.body("data.key", containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
		.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"));
	}
	
	@Test(description = "Verify if the count API is giving correct status code for invalid token",groups = {"api","negative","smoke","regression"})
	public void countAPITest_MissingAuthToken() {
		given()
		.spec(requestSpec())
		.when()
		.get("/dashboard/count")
		.then()
		.spec(responseSpec_TEXT(401));
	}

}
