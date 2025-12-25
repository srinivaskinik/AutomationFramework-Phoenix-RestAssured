package com.api.services;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;

import io.restassured.response.Response;

public class JobService {

	private static final String CREATE_JOB_ENDPOINT = "/job/create";
	private static final String SEARCH_ENDPOINT = "/job/search";

	public Response createJob(Role role, CreateJobPayload createJobPayload) {
		return given().spec(requestSpecWithAuth(role, createJobPayload)).when().post(CREATE_JOB_ENDPOINT);
	}

	public Response search(Role role, Object payload) {
		return given().spec(requestSpecWithAuth(role)).body(payload).when().post(SEARCH_ENDPOINT);
	}

}
