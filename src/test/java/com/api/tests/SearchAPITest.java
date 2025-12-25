package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.Detail;
import com.api.request.model.Search;
import com.api.services.DashboardService;
import com.api.services.JobService;
import com.api.utils.SpecUtil;

public class SearchAPITest {
	private JobService jobService;
	private static final String JOB_NUMBER="JOB_128654";
	private Search searchPayload;
	
	@BeforeMethod(description = "Instantiating the job service and creating search payload")
	public void setup() {
		jobService=new JobService();
		searchPayload = new Search(JOB_NUMBER);
	}
	
	@Test(description = "Verify if the search API is working properly", groups= {"api","smoke","regression"})
	public void searchAPITest() {
		jobService.search(Role.FD, searchPayload)
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message", Matchers.equalTo("Success"));
	}

}
