package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredentials;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.ExcelReaderUtil;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JsonReaderUtil;
import com.database.dao.CreateJobPayloadDataDao;
import com.database.dao.MapJobProblemDao;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {
	private static final Logger LOGGER = LogManager.getLogger(DataProviderUtils.class);
	@DataProvider(name="LoginAPIDataProvider", parallel=true)
	public static Iterator<UserBean> loginAPIDataProvider() {
		LOGGER.info("Loading data from csv file testData/LoginCreds.csv");
		return CSVReaderUtil.loadCSV("testData/LoginCreds.csv", UserBean.class);
	}
	
	
	@DataProvider(name="CreateJobDataProvider", parallel=true)
	public static Iterator<CreateJobPayload> createJobDataProvider() {
		LOGGER.info("Loading data from csv file testData/CreateJobData.csv");
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
		LOGGER.info("Generating fake Create job data with the faker count {}",fakerCount);
		Iterator<CreateJobPayload> payloadIterator=FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);
		return payloadIterator;
	}
	
	@DataProvider(name="LoginAPIJsonDataProvider", parallel=true)
	public static Iterator<UserBean> loginAPIJsonDataProvider() {
		LOGGER.info("Loading data from json file testData/loginAPITestData.json");
		return JsonReaderUtil.loadJSON("testData/loginAPITestData.json", UserBean[].class);
	}
	
	@DataProvider(name="CreateJobAPIJsonDataProvider", parallel=true)
	public static Iterator<CreateJobPayload> createJobAPIJsonDataProvider() {
		LOGGER.info("Loading data from json file testData/CreateJobAPIData.json");
		return JsonReaderUtil.loadJSON("testData/CreateJobAPIData.json", CreateJobPayload[].class);
	}
	
	@DataProvider(name="LoginAPIExcelDataProvider", parallel=true)
	public static Iterator<UserBean> LoginAPIExcelDataProvider() {
		LOGGER.info("Loading data from Excel file testData/PhoenixTestData.xlsx and sheet is LoginTestData");
		return ExcelReaderUtil.loadTestData("testData/PhoenixTestData.xlsx","LoginTestData",UserBean.class);
	}
	
	@DataProvider(name="createJobAPIExcelDataProvider", parallel=true)
	public static Iterator<CreateJobPayload> createJobAPIExcelDataProvider() {
		LOGGER.info("Loading data from Excel file testData/PhoenixTestData.xlsx and sheet is CreateJobTestData");
		Iterator<CreateJobBean> createJobBeanIterator = ExcelReaderUtil.loadTestData("testData/PhoenixTestData.xlsx", "CreateJobTestData",CreateJobBean.class);
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
	
	@DataProvider(name="createJobAPIDBDataProvider", parallel=true)
	public static Iterator<CreateJobPayload> createJobAPIDBDataProvider() {
		LOGGER.info("Loading data from database for CreateJobPayLoad");
		List<CreateJobBean> beanList= CreateJobPayloadDataDao.getCreateJobPayLoadData();
		List<CreateJobPayload> payloadList= new ArrayList<CreateJobPayload>();
		for(CreateJobBean createJobBean :beanList) {
			CreateJobPayload payload= CreateJobBeanMapper.mapper(createJobBean);
			payloadList.add(payload);
		}
		return payloadList.iterator();
	}


}
