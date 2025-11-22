package com.api.tests.datadriven;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.dataproviders.api.bean.UserBean;

public class LoginAPIExcelDataDrivenTest {

	@Test(description = "Verifying if the login api is working for user FD user",
			groups = {"api","regression","datadriven"},
			dataProviderClass = com.dataproviders.DataProviderUtils.class,
			dataProvider = "LoginAPIExcelDataProvider")
	public void loginAPITest(UserBean userBean)  {
		
		given()
			.spec(requestSpec(userBean))
		.when()
			.post("login")
		.then()
			.spec(responseSpec_OK())
		.and()
			.body("message",equalTo("Success"))
		.and()
			.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	}
}
