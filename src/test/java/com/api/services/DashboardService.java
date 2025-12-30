package com.api.services;

import static com.api.constant.Role.FD;
import static com.api.utils.SpecUtil.*;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Role;

import io.restassured.response.Response;

public class DashboardService {
	private static final String COUNT_ENDPOINT="/dashboard/count";
	private static final String DETAIL_ENDPOINT="/dashboard/details";
	private static final Logger LOGGER=LogManager.getLogger(DashboardService.class);
	
	public Response count(Role role) {
		LOGGER.info("Making request to the {} for the role {}",COUNT_ENDPOINT, role);
		Response response = given()
		.spec(requestSpecWithAuth(role))
		.when()
		.get(COUNT_ENDPOINT);
		
		return response;
	}
	
	public Response countWithNoAuth() {
		LOGGER.info("Making request to the {} with no Auth Token",COUNT_ENDPOINT);
		Response response = given().spec(requestSpec())
		.when()
		.get(COUNT_ENDPOINT);
		
		return response;
	}
	
	public Response details(Role role, Object payload) {
		LOGGER.info("Making request to the {} with the role {} and the payload {}",DETAIL_ENDPOINT,role, payload);
		Response response = given()
				.spec(requestSpecWithAuth(role))
				.body(payload)
				.when()
				.post(DETAIL_ENDPOINT);
				
				return response;
		
	}
}
