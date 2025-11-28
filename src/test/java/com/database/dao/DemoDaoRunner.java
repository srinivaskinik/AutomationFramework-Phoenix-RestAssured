package com.database.dao;

import java.sql.SQLException;

import org.testng.Assert;

import com.api.request.model.Customer;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerProductDBModel;

public class DemoDaoRunner {
	public static void main(String[] args) throws SQLException {
		CustomerProductDBModel customerProductDBModel= CustomerProductDao.getCustomerProductInfo(113908);
		System.out.println(customerProductDBModel);
	}

}
