package com.api.filters;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter {
	Logger LOGGER = LogManager.getLogger(SensitiveDataFilter.class);

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		LOGGER.info("******************* REQUEST DETAILS **********************");
		LOGGER.info("BASE URI: {}", requestSpec.getURI());
		LOGGER.info("HTTP METHOD: {}", requestSpec.getMethod());
		// headers for the request section
		redactHeader(requestSpec);
		redactPayload(requestSpec);
		LOGGER.info("******************* REQUEST DETAILS **********************");
		Response response = ctx.next(requestSpec, responseSpec);
		LOGGER.info("STATUS : {}", response.getStatusLine());
		LOGGER.info("RESPONSE TIME ms: {}", response.timeIn(TimeUnit.MILLISECONDS));
		LOGGER.info("RESPONSE HEADERS: \n {}", response.getHeaders());
		redactResponseBody(response);
		return response;
	}
	

	private void redactHeader(FilterableRequestSpecification requestSpec) {
		List<Header> headerList = requestSpec.getHeaders().asList();
		for (Header h : headerList) {
			if (h.getName().equalsIgnoreCase("Authorization")) {
				LOGGER.info("HEADER {} : {}",h.getName(), "\"[REDACTED]\"");
			} else {
				LOGGER.info("HEADER {} : {}" ,h.getName() , h.getValue());
			}
				
		}
	}

	private void redactResponseBody(Response response) {
		String responseBody = response.asPrettyString();
		responseBody = responseBody.replaceAll("\"token\"\s*:\s*\"[^\"]+\"", "\"token\": \"[REDACTED]\"");
		LOGGER.info("RESPONSE BODY: {}", responseBody);
	}

	public void redactPayload(FilterableRequestSpecification requestSpec) {
		if (requestSpec.getBody() != null) {
			String requestPayload = requestSpec.getBody().toString();
			requestPayload = requestPayload.replaceAll("\"password\"\s*:\s*\"[^\"]+\"", "\"password\": \"[REDACTED]\"");
			LOGGER.info("REQUEST PAYLOAD: {}", requestPayload);
		}
	}

}
