package com.jakartalabs.rest.assured.tests;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import restassured.assignment.utils.DataUtils;

public abstract class BaseAPITest {

	Faker faker = new Faker();
	protected RequestSpecification commonSpec = new RequestSpecBuilder()
			.setBaseUri(DataUtils.getDataFromExcel("Config", "BaseAPIUrl")).setAccept("application/json")
			.setContentType("application/json").build().log().all();

	protected void verifyAPIStatusTimeAndHeader(Response response, int expectedStatusCode) {
		assertEquals(response.getStatusCode(), expectedStatusCode);
		assertEquals(response.getTimeIn(TimeUnit.SECONDS) < 10, true);
		assertEquals(response.getHeader("X-Frame-Options"), "SAMEORIGIN");

	}

	protected <T> T getDataFromResponseUsingJsonPath(Response response, String jsonPath) {

		JsonPath jpath = response.jsonPath();

		return jpath.get(jsonPath);
	}

}
