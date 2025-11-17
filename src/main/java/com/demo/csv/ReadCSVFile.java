package com.demo.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile {

	public static void main(String[] args) throws IOException, CsvException {
		// Code to read the CSV file in java
		/*
		 * 
		 * 		File csvFile = new File(
				"C:\\Users\\Srinivas\\eclipse-workspace_09_09_2025\\PhoenixTestAutomationFramework_RestAssured\\src\\main\\resources\\testData\\LoginCreds.csv");
		FileReader fileReader = new FileReader(csvFile);
		
		 */
		
		InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginCreds.csv");

		InputStreamReader isr=new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr);
		// CSVReader requires constructor

		List<String[]> dataList = csvReader.readAll();

		for (String[] dataArray : dataList) {
			for (String data : dataArray) {
				System.out.print(data + " ");

			}
			System.out.println("");
		}

	}

}
