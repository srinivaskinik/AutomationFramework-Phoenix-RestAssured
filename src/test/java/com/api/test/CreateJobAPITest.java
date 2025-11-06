package com.api.test;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtil;

public class CreateJobAPITest {
	
	
	
	@Test
	public void createJobAPITest() {
		//Creating the CreateJobPayload object
		Customer customer=new Customer("Srinvas","K","9811122334","","test@gmail.com","");
		CustomerAddress customerAddress = new CustomerAddress("010", "Apt111", "Homadevanahalli", "Near Podar", "Bangalore", "560083", "Karnataka", "India");
		CustomerProduct customerProduct = new CustomerProduct("2025-08-31T18:30:00.000Z", "12995812527880","12995812527880", "12995812527880", "2025-08-31T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1, "Battery Issue");
		Problems[] problemsArray=new Problems[1];
		problemsArray[0]=problems;
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.body(createJobPayload)
		.log().all()
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtil.responseSpec_OK());
	}

}
