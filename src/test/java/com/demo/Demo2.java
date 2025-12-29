package com.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Demo2 {
	private static Logger logger = LogManager.getLogger(Demo2.class);

	public static void main(String[] args) {
		System.out.println("Inside the main method");
		logger.info("Inside the main method");
		int a = 10;
		logger.info("Value of a {}", a);
		int b = 0;
		if (b == 0) {
			logger.warn("Value of b {}", b);
		} else {
			logger.info("Value of b {}", b);
		}

		try {
			int result = a / b;

		} catch (ArithmeticException e) {
			logger.error("Operation cannot happen", e);
		}

	}

}
