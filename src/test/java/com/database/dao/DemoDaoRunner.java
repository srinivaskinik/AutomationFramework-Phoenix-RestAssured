package com.database.dao;

import java.sql.SQLException;

import org.testng.Assert;

import com.api.request.model.Customer;
import com.database.model.CustomerDBModel;

public class DemoDaoRunner {
	public static void main(String[] args) throws SQLException {
		CustomerDBModel customerDBData= CustomerDao.getCustomerInfo();
		System.out.println(customerDBData);
		Customer customer=new Customer("Evan", "Heaney", "540-606-7064", null, "Camron96@hotmail.com", null);
		Assert.assertEquals(customerDBData.getFirst_name(), customer.first_name());
	}

}
