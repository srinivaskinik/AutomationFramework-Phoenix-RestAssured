package com.api.tests.datadriven;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;

public class CreateJobAPIFakeDataDrivenTest {

	
	@Test(description = "Verify if the create job API is able to create Inwarranty job",
			groups = {"api","dataDriven","regression"},
			dataProviderClass =com.dataproviders.DataProviderUtils.class,
			dataProvider = "CreateJobAPIFakerDataProvider")
	public void createJobAPITest(CreateJobPayload createJobPayload) {
		//Creating the CreateJobPayload object
	
		given()
		.spec(requestSpecWithAuth(Role.FD, createJobPayload))
		.body(createJobPayload)
		.log().all()
		.when()
		.post("/job/create")
		.then()
		.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number", startsWith("JOB_"));
	}

}
