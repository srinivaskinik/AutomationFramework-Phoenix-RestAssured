package com.dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.api.utils.CSVReaderUtil;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {
	
	@DataProvider(name="LoginAPIDataProvider", parallel=true)
	public static Iterator<UserBean> loginAPIDataProvider() {
		return CSVReaderUtil.loadCSV("testData/LoginCreds.csv");
	}
	
	//Dataprovider needs to return something
	//[]
	//[][]
	//Iterator<>

}
