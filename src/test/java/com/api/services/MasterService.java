package com.api.services;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constant.Role;

import io.restassured.response.Response;

public class MasterService {
	private static final String MASTER_ENDPOINT = "/master";

	public Response master(Role role) {
		return given().spec(requestSpecWithAuth(role)).when().post(MASTER_ENDPOINT); // default content-type
																					// application/url-formencoded;

	}

	public Response master() {
		return given().spec(requestSpec()).and().contentType("").log().all().when().post("master");
	}
}
