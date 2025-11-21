package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredentials;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JsonReaderUtil;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {
	
	@DataProvider(name="LoginAPIDataProvider", parallel=true)
	public static Iterator<UserBean> loginAPIDataProvider() {
		return CSVReaderUtil.loadCSV("testData/LoginCreds.csv", UserBean.class);
	}
	
	
	@DataProvider(name="CreateJobDataProvider", parallel=true)
	public static Iterator<CreateJobPayload> createJobDataProvider() {
		Iterator<CreateJobBean> createJobBeanIterator = CSVReaderUtil.loadCSV("testData/CreateJobData.csv", CreateJobBean.class);
		List<CreateJobPayload> payloadList=new ArrayList<CreateJobPayload>();
		CreateJobBean tempBean;
		CreateJobPayload tempPayload;
		while(createJobBeanIterator.hasNext()) {
			tempBean=createJobBeanIterator.next();
			tempPayload= CreateJobBeanMapper.mapper(tempBean);
			payloadList.add(tempPayload);
		}
		
		return payloadList.iterator();
	}
	
	@DataProvider(name="CreateJobAPIFakerDataProvider", parallel=true)
	public static Iterator<CreateJobPayload> createJobFakeDataProvider() {
		String fakerCount=System.getProperty("fakerCount","5");
		int fakerCountInt=Integer.parseInt(fakerCount);
		Iterator<CreateJobPayload> payloadIterator=FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);
		return payloadIterator;
	}
	
	@DataProvider(name="LoginAPIJsonDataProvider", parallel=true)
	public static Iterator<UserCredentials> loginAPIJsonDataProvider() {
		return JsonReaderUtil.loadJSON("testData/loginAPITestData.json", UserCredentials[].class);
	}
	
	@DataProvider(name="CreateJobAPIJsonDataProvider", parallel=true)
	public static Iterator<CreateJobPayload> createJobAPIJsonDataProvider() {
		return JsonReaderUtil.loadJSON("testData/CreateJobAPIData.json", CreateJobPayload[].class);
	}

}
