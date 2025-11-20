package com.api.tests;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;
import com.github.javafaker.Faker;

public class CreateJobAPITest2 {
	CreateJobPayload createJobPayload;
	private final static String country = "India";
	@BeforeMethod(description = "Creating createjob api request payload")
	public void setup() {
		Faker faker = new Faker(new Locale("en-IND"));
		String fname= faker.name().firstName();
		String lname= faker.name().lastName();
		String mobileNumber = faker.numerify("70########");
		String alternateMobileNumber = faker.numerify("70########");
		String customerEmailAddress=faker.internet().emailAddress();
		String altCustomerEmailAddress=faker.internet().emailAddress();
		Customer customer = new Customer(fname, lname, mobileNumber, alternateMobileNumber, customerEmailAddress, altCustomerEmailAddress);
		System.out.println(customer);
		
		String flatNumber = faker.numerify("###");
		String apartmentName = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.numerify("#####");
		String state = faker.address().state();
		CustomerAddress customerAddress =new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area, pincode, country, state);
		System.out.println(customerAddress);
		
		//CustomerProduct fake object creation
		String dop=DateTimeUtil.getTimeWithDaysAgo(10);
		String imeiSerialNumber = faker.numerify("###############");
		String popUrl=faker.internet().url();
		CustomerProduct customerProduct= new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber, popUrl, 1, 1);
		System.out.println(customerProduct);
		
		String fakeRemark = faker.lorem().sentence(5);
		//I want to generate a random number between 1 and 27
		Random random = new Random();
		int problemId=random.nextInt(27)+1; //excluding 28 (here we get 0 to 26 add one to it to meet our requirement)
		Problems problems=new Problems(problemId,fakeRemark);
		System.out.println(problems);
		
		List<Problems> problemList=new ArrayList<Problems>();
		problemList.add(problems);
		
		createJobPayload=new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
		
	}
	
	
	@Test(description = "Verify if the create job API is able to create Inwarranty job",groups = {"api","smoke","regression"})
	public void createJobAPITest() {
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
