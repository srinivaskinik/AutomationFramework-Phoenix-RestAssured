package com.api.tests;


import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.api.constant.Role.*;
import com.api.services.UserService;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class UserDetailsAPITest {
	private UserService userService;
	@BeforeMethod(description = "Setting up the UserService instance")
	public void setup() {
		userService = new UserService();
	}
	@Test(description = "Verify if the Userdetails API response is shown correctly",groups = {"api","smoke","regression"})
	public void userDetailsAPITest() throws IOException {
		
		userService.userDetails(FD)
		.then()
			.spec(responseSpec_OK())
		.and()
			.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
			
	}

}
