package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.poiji.bind.Poiji;

public class ExcelReaderUtil {
	private static final Logger LOGGER = LogManager.getLogger(ExcelReaderUtil.class);
	private ExcelReaderUtil() {

	}

	public static <T> Iterator<T> loadTestData(String fileName, String sheetName,Class<T> clazz) {
		LOGGER.info("Reading the test data from .xlsx file {} and the sheet name is {} ",fileName, sheetName);
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(fileName);
		XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook(is);
		} catch (IOException e) {
			LOGGER.error("Cannot read the excel {}",fileName,e);
			e.printStackTrace();
		}
		// focus on the sheet
		XSSFSheet mySheet = myWorkBook.getSheet(sheetName);
		LOGGER.info("Converting the XSSFSheet {} to POJO class of type {} ",fileName,clazz);

		List<T> dataList = Poiji.fromExcel(mySheet, clazz);
		return dataList.iterator();
	}

}
