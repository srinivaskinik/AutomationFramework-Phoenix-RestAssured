package com.api.services;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constant.Role;

import io.restassured.response.Response;

public class UserService {
	private static final String USERDETAILS_ENDPOINT="/userdetails";
	
	public Response userDetails(Role role) {
		Response response = given()
		.spec(requestSpecWithAuth(role))
		.when()
			.get(USERDETAILS_ENDPOINT);
		
		return response;
	}
}
