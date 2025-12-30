package com.api.tests.datadriven;

import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.services.AuthService;
import com.dataproviders.api.bean.UserBean;

public class LoginAPIJSONDataDrivenTest {

	private AuthService authService;

	@BeforeMethod(description = "Setting up the AuthService reference")
	public void setup() {
		authService = new AuthService();
	}

	@Test(description = "Verifying if the login api is working for user FD user", groups = { "api", "regression",
			"datadriven" }, dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider = "LoginAPIJsonDataProvider")
	public void loginAPITest(UserBean userCredentials) {

		authService.login(userCredentials).then().spec(responseSpec_OK()).and().body("message", equalTo("Success"))
				.and().body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	}
}
