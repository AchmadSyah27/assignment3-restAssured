package com.jakartalabs.rest.assured.tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;
import com.jakartalabs.rest.assured.APIEndpoints;
import com.jakartalabs.rest.assured.JsonPaths;

import io.restassured.response.Response;
import restassured.assignment.utils.DataUtils;
import restassured.assignment.utils.TestUtils;

public class APITests extends BaseAPITest {

	String authToken = "token not found";
	String Email = null;
	String Message = "Logged out successfully";

	@Test(priority = 1)
	public void signUp() {

		LinkedTreeMap<String, Object> signUpMap = TestUtils.convertJsonToMap(DataUtils
				.getDataFromExcel("Payload", "SignUpAPI").replace("uniqueEmail", faker.name().username() + "@gmail.com")
				.replace("uniquePhone", "+62-81215685596").replace("dfsdfsdfsd", faker.name().firstName() + ""));
		System.out.println(signUpMap);
		System.out.println("test log");

		Response response = given().spec(commonSpec).body(signUpMap).when().post(APIEndpoints.signUpAPI);

		verifyAPIStatusTimeAndHeader(response, 200);

		authToken = getDataFromResponseUsingJsonPath(response, JsonPaths.authToken);
		System.out.println(authToken);

		Email = getDataFromResponseUsingJsonPath(response, JsonPaths.email);
		System.out.println(Email);
	}

	@Test(priority = 2)
	public void profileAPI() {

		Response responseProfileAPI = given().spec(commonSpec).header("authtoken", authToken).when()
				.get(APIEndpoints.profileAPI);

		System.out.println(authToken);

		verifyAPIStatusTimeAndHeader(responseProfileAPI, 200);

		String verifyEmail = getDataFromResponseUsingJsonPath(responseProfileAPI, "email");
		assertEquals(verifyEmail, Email);

	}

	@Test(priority = 3)
	public void logOutAPI() {

		Response responseLogOutAPI = given().spec(commonSpec).param("authtoken", authToken).when()
				.delete(APIEndpoints.logOutAPI);

		System.out.println(authToken);

		verifyAPIStatusTimeAndHeader(responseLogOutAPI, 200);

		String verifyResponseMessage = getDataFromResponseUsingJsonPath(responseLogOutAPI, "message");
		AssertJUnit.assertEquals(verifyResponseMessage, Message);

	}

}
