package com.api.services;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class JobService {

	private static final String CREATE_JOB_ENDPOINT = "/job/create";
	private static final String SEARCH_ENDPOINT = "/job/search";
	private static final Logger LOGGER=LogManager.getLogger(JobService.class);
	
	@Step("Creating Inwarranty Job with Create Job API")
	public Response createJob(Role role, CreateJobPayload createJobPayload) {
		LOGGER.info("Making request to {} with the role {} and payload {}",CREATE_JOB_ENDPOINT,role,createJobPayload);
		return given().spec(requestSpecWithAuth(role, createJobPayload)).when().post(CREATE_JOB_ENDPOINT);
	}

	@Step("Making Search API Request")
	public Response search(Role role, Object payload) {
		LOGGER.info("Making request to {} with the role {} and payload {}",SEARCH_ENDPOINT,role,payload);
		return given().spec(requestSpecWithAuth(role)).body(payload).when().post(SEARCH_ENDPOINT);
	}

}
