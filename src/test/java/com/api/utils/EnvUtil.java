package com.api.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtil {
	private static final Logger LOGGER = LogManager.getLogger(EnvUtil.class);
	public static Dotenv dotenv;
	static {
		LOGGER.info("Loading the .env file...");
		dotenv = Dotenv.load();
	}
	private EnvUtil() {
		
	}
	
	public static String getValue(String varName) {
		LOGGER.info("Reading the value of {} from .env",varName);
		return dotenv.get(varName);
	}
	
	

}
