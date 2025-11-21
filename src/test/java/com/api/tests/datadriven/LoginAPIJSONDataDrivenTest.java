package com.api.tests.datadriven;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;

public class LoginAPIJSONDataDrivenTest {

	@Test(description = "Verifying if the login api is working for user FD user", groups = { "api", "regression",
			"datadriven" }, dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider = "LoginAPIJsonDataProvider")
	public void loginAPITest(UserCredentials userCredentials) {

		given().spec(requestSpec(userCredentials)).when().post("login").then().spec(responseSpec_OK()).and()
				.body("message", equalTo("Success")).and()
				.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	}
}
