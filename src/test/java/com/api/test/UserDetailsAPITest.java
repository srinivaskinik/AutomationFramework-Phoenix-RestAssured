package com.api.test;


import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constant.Role;
import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class UserDetailsAPITest {
	
	@Test(description = "Verify if the Userdetails API response is shown correctly",groups = {"api","smoke","regression"})
	public void userDetailsAPITest() throws IOException {
		
		given()
		.spec(requestSpecWithAuth(Role.FD))
		.when()
			.get("userdetails")
		.then()
			.spec(responseSpec_OK())
		.and()
			.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
			
	}

}
