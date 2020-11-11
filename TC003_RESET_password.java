
/*
 * Author: Hemant Choudhari
 * summary: Test Case 3 Reset Password
 * Date: 09/14/2019
 */

/******************************************************
Test Name: Reset password existing user
URI: http: https://api2.fox.com/v2.0/reset
Request Type: POST
Request Payload(Body):

{
	"email":"hemant10@fox.com",
	"password":"abcdef"
}

********* Validations **********
message  and detail Validation
Status Code : 200
Response Time Validation
Status Line : HTTP/1.1 200 OK
Content Type : application/json


*********************************************************/

package com.foxapi.testCases;

import io.restassured.http.Header;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.foxapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class TC003_RESET_password extends TestBase{


	private Response responce;

	@BeforeClass
	void getEmployeeData() throws InterruptedException{

		RestAssured.baseURI = "http://13.126.80.194:8080";
		httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("username", "rupeek");
		requestParams.put("password", "password");

		httpRequest.header("Content-Type", "application/json");
		httpRequest.header("Cache-Control", "no-cache");

		httpRequest.body(requestParams.toJSONString());
		responce = httpRequest.request(Method.POST, "/authenticate");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
	}


	//Test Case - message  and detail Validation
	@Test
	void checkResposeBody()
	{
		String responseBody = response.getBody().asString();
		System.out.println("Response body as string" + responseBody);

	}
		
	//Test Case - Status code validation
	@Test
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode(); 
		Assert.assertEquals(statusCode, 200);
	}
	
	//Test Case - Response Time check
	@Test
	void checkResponseTime()
	{
		long responseTime = response.getTime(); 
		Assert.assertTrue(responseTime<7000);
		
	}
		
	//Test Case - Check status Line
	@Test
	void checkstatusLine()
	{
		String statusLine = response.getStatusLine(); 
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	//Test Case - Check Content-Type
	@Test
	void checkContentType()
	{
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json");
	}

	
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC003_RESET_password  **********");
	}
	
}
