package com.api.services;

import static com.api.constant.Role.FD;
import static com.api.utils.SpecUtil.*;
import static io.restassured.RestAssured.given;

import com.api.constant.Role;

import io.restassured.response.Response;

public class DashboardService {
	private static final String COUNT_ENDPOINT="/dashboard/count";
	private static final String DETAIL_ENDPOINT="/dashboard/details";

	public Response count(Role role) {
		Response response = given()
		.spec(requestSpecWithAuth(role))
		.when()
		.get(COUNT_ENDPOINT);
		
		return response;
	}
	
	public Response countWithNoAuth() {
		Response response = given().spec(requestSpec())
		.when()
		.get(COUNT_ENDPOINT);
		
		return response;
	}
	
	public Response details(Role role, Object payload) {
		Response response = given()
				.spec(requestSpecWithAuth(role))
				.body(payload)
				.when()
				.post(DETAIL_ENDPOINT);
				
				return response;
		
	}
}
