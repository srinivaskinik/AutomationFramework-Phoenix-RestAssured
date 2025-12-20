package com.api.tests;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.services.JobService;
import com.api.utils.FakerDataGenerator;
import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerDao;
import com.database.dao.CustomerProductDao;
import com.database.dao.JobHeadDao;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerProductDBModel;
import com.database.model.JobHeadModel;

import io.restassured.response.Response;

public class CreateJobAPITestWithFakeData {
	CreateJobPayload createJobPayload;
	private JobService jobService;
	@BeforeMethod(description = "Creating createjob api request payload and instantiating the job service")
	public void setup() {
		createJobPayload=FakerDataGenerator.generateFakeCreateJobData();
		jobService=new JobService();
	}
	
	
	@Test(description = "Verify if the create job API is able to create Inwarranty job",groups = {"api","smoke","regression"})
	public void createJobAPITest() {
		//Creating the CreateJobPayload object
	
		Response response=jobService.createJob(Role.FD, createJobPayload)
		.then()
		.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number", startsWith("JOB_"))
		.extract().response();
		
		int customerId=response.then().extract().body().jsonPath().getInt("data.tr_customer_id");
		
		Customer expectedCustomerData=createJobPayload.customer();
		CustomerDBModel actualCustomerDataInDB=CustomerDao.getCustomerInfo(customerId);
		Assert.assertEquals(actualCustomerDataInDB.getFirst_name(), expectedCustomerData.first_name());
		Assert.assertEquals(actualCustomerDataInDB.getLast_name(), expectedCustomerData.last_name());
		Assert.assertEquals(actualCustomerDataInDB.getMobile_number(), expectedCustomerData.mobile_number());
		Assert.assertEquals(actualCustomerDataInDB.getMobile_number_alt(), expectedCustomerData.mobile_number_alt());
		Assert.assertEquals(actualCustomerDataInDB.getEmail_id(), expectedCustomerData.email_id());
		Assert.assertEquals(actualCustomerDataInDB.getEmail_id_alt(), expectedCustomerData.email_id_alt());
	
		CustomerAddressDBModel customerAddressFromDB=CustomerAddressDao.getCustomerAddressData(actualCustomerDataInDB.getTr_customer_address_id());
		
		CustomerAddress expectedCustomerAddressData=createJobPayload.customer_address();
		Assert.assertEquals(customerAddressFromDB.getFlat_number(), expectedCustomerAddressData.flat_number());
		Assert.assertEquals(customerAddressFromDB.getApartment_name(), expectedCustomerAddressData.apartment_name());
		Assert.assertEquals(customerAddressFromDB.getStreet_name(), expectedCustomerAddressData.street_name());
		Assert.assertEquals(customerAddressFromDB.getLandmark(), expectedCustomerAddressData.landmark());
		Assert.assertEquals(customerAddressFromDB.getArea(), expectedCustomerAddressData.area());
		Assert.assertEquals(customerAddressFromDB.getPincode(), expectedCustomerAddressData.pincode());
		Assert.assertEquals(customerAddressFromDB.getCountry(), expectedCustomerAddressData.country());
		Assert.assertEquals(customerAddressFromDB.getState(), expectedCustomerAddressData.state());
		
		int productId =response.then().extract().jsonPath().getInt("data.tr_customer_product_id");
		
		CustomerProduct expectedCustomerProductData=createJobPayload.customer_product();
		CustomerProductDBModel customerProductDBData = CustomerProductDao.getCustomerProductInfo(productId);
		Assert.assertEquals(customerProductDBData.getImei1(), expectedCustomerProductData.imei1());
		Assert.assertEquals(customerProductDBData.getImei2(), expectedCustomerProductData.imei2());
		Assert.assertEquals(customerProductDBData.getSerial_number(), expectedCustomerProductData.serial_number());
		Assert.assertEquals(customerProductDBData.getDop(), expectedCustomerProductData.dop());	
		Assert.assertEquals(customerProductDBData.getPopurl(), expectedCustomerProductData.popurl());	
		Assert.assertEquals(customerProductDBData.getMst_model_id(), expectedCustomerProductData.mst_model_id());	
	
		JobHeadModel jobHeadDataFromDB = JobHeadDao.getJobHeadInfo(customerId);
		Assert.assertEquals(jobHeadDataFromDB.getMst_oem_id(), createJobPayload.mst_oem_id());
		Assert.assertEquals(jobHeadDataFromDB.getMst_platform_id(), createJobPayload.mst_platform_id());
		Assert.assertEquals(jobHeadDataFromDB.getMst_service_location_id(), createJobPayload.mst_service_location_id());
		Assert.assertEquals(jobHeadDataFromDB.getMst_warrenty_status_id(), createJobPayload.mst_warrenty_status_id());
	}

}
