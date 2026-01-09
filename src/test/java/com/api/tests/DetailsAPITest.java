package com.api.tests;

import static com.api.utils.SpecUtil.responseSpec_OK;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.api.constant.Role.*;
import com.api.request.model.Detail;
import com.api.services.DashboardService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Listeners(com.listeners.APITestListener.class)
@Epic("Job Management")
@Feature("Job Details")
public class DetailsAPITest {
	
	private Detail detailPayload;
	private DashboardService dashboardService;
	
	@BeforeMethod(description = "Instantiating the dashboard service and creating detail payload")
	public void setup() {
		dashboardService=new DashboardService();
		detailPayload = new Detail("created_today");
	}
	
	@Story("Job details is shown correctly for FD")
	@Description("Verify if details api is working properly")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify if details api is working properly", groups= {"api","smoke","regression"})
	public void detailAPITest() {
		dashboardService.details(FD, detailPayload)
		.then().spec(responseSpec_OK())
		.body("message", equalTo("Success"));
	}

}
