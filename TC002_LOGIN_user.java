package com.foxapi.testCases;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;

import org.testng.Assert;
import org.testng.annotations.*;

import com.foxapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.security.PublicKey;

public class TC002_LOGIN_user extends TestBase{

	static RequestSpecification httpRequest;
	static Response response, response01;
	static String username = "rupeek";
	static String password = "password12345";
	private static JSONObject json;



	@BeforeClass
	public void getEmployeeData() throws InterruptedException {
		logger.info("********* Started TC002_LOGIN_user **********");

		RestAssured.baseURI = "http://13.126.80.194:8080";
		httpRequest = RestAssured.given();

		response = TC001_REG_user.getResponce();
		httpRequest.header("Authorization", "Bearer " + response.getBody().jsonPath().get("token"));

		response01 = httpRequest.request(Method.GET,"/api/v1/users");
		System.out.println(response.getBody().print());

	}


	//Test Case - Status code validation
	@Test
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode(); 
		Assert.assertEquals(statusCode, 200);
	}

	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC002_LOGIN_user  **********");
	}
	
}
