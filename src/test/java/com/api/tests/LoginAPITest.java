package com.api.tests;

import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.services.AuthService;
import com.dataproviders.api.bean.UserBean;

public class LoginAPITest {
	private UserBean userCredentials;
	private AuthService authService;
	@BeforeMethod(description = "Create the payload for login API")
	public void setup() {
	 userCredentials = new UserBean("iamfd", "password");
	 authService=new AuthService();
	}
	@Test(description = "Verifying if the login api is working for user FD user",groups = {"api","regression","smoke"})
	public void loginAPITest() throws IOException {
		
		
		authService.login(userCredentials)
			.then()
			.spec(responseSpec_OK())
		.and()
			.body("message",equalTo("Success"))
		.and()
			.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	}
}
