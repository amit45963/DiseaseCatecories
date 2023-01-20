package com.nitara.UserLogin;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.nitara.base.GenericBase;
import com.nitara.utilities.ExcelUtils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Login extends GenericBase{

	@Test
	public String SPToken(String url) throws Exception {

		//String url = prop.getProperty("baseurl");
		RestAssured.baseURI = url;
		String filepath = prop.getProperty("AccountManagement");
		// Read Farmer credentials from Excel
		ExcelUtils exceldata = new ExcelUtils();
		JSONObject username = exceldata.readRowField("GeneralData", "SPusername", filepath);
		JSONObject password = exceldata.readRowField("GeneralData", "SPpassword", filepath);


		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("countryCode", "+91"); // Cast
		requestParams.put("phone",username.getString("SPusername")); // 
		requestParams.put("Pin",password.getString("SPpassword")); //
		requestParams.put("deviceName", "DELL_PC"); 		 
		requestParams.put("deviceType",  "LAPTOP");
		requestParams.put("PreferredLanguage","EN");
		//requestParams.put("key",  "com.nitara.farmer");
		requestParams.put("key",  "com.nitara.serviceprovider");

		System.out.println(requestParams);
		request.body(requestParams.toString());

		request.header("Content-Type", "application/json");

		Response response = request.post("/AM/v1/UserKeyLoginWithPinWithPreferredLanguage");

		System.out.println("\n\"/AM/v1/UserKeyLoginWithPinWithPreferredLanguage\"\n");
		//Print response
		response.prettyPeek();

		Assert.assertEquals(response.getStatusCode(),200);



		String jsonString = response.asString();
		String  message = JsonPath.from(jsonString).get("message");
		//Validate success message
		//Assert.assertEquals(message,"Logged in successfully.");

		String token = JsonPath.from(jsonString).get("token");
		return (token);
	}

	@Test
	public String userToken(String url) throws Exception {

		//String url = prop.getProperty("baseurl");
		RestAssured.baseURI = url;

		//String filepath = prop.getProperty("AccountManagement");

		// Read Farmer credentials from Excel
	//	ExcelUtils exceldata = new ExcelUtils();


		//JSONObject	username = exceldata.readRowField("GeneralData", "username", filepath);
		//JSONObject	password = exceldata.readRowField("GeneralData", "Password", filepath);


		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("countryCode", "+91"); // Cast
		requestParams.put("phone", "7777777777"); //
		requestParams.put("Pin","123456");
		//requestParams.put("Pin",password.getString("Password")); // 
		requestParams.put("deviceName", "DELL_PC"); 		 
		requestParams.put("deviceType",  "LAPTOP");
		requestParams.put("key",  "com.nitara.serviceprovider");
		requestParams.put("PreferredLanguage","EN");
		//requestParams.put("key",  "com.nitara.serviceprovider");
		System.out.println(requestParams);
		request.body(requestParams.toString());

		request.header("Content-Type", "application/json");

		Response response = request.post("/AM/v1/UserKeyLoginWithPinWithPreferredLanguage");

		System.out.println("\n\"/AM/v1/UserKeyLoginWithPinWithPreferredLanguage\"\n");

		//Print response
		response.prettyPeek();

		//Validate status code
		Assert.assertEquals(response.getStatusCode(),200);

		String jsonString = response.asString();
		String  message = JsonPath.from(jsonString).get("message");
		
		//Validate success message
		//Assert.assertEquals(message,"Logged in successfully.");

		String token = JsonPath.from(jsonString).get("token");
		return (token);
	}

	@Test
	public String FarmerToken(String url) throws Exception {

		//String url = prop.getProperty("baseurl");
		RestAssured.baseURI = url;

		RequestSpecification request = RestAssured.given();

		String filepath = prop.getProperty("AccountManagement");
		ExcelUtils exceldata = new ExcelUtils();

		JSONObject	username = exceldata.readRowField("GeneralData", "username", filepath);
		JSONObject	password = exceldata.readRowField("GeneralData", "Password", filepath);
		JSONObject requestParams = new JSONObject();
		requestParams.put("countryCode", "+91"); // Cast
		requestParams.put("phone",username.getString("username")); // username.getString("username")
		requestParams.put("Pin",password.getString("Password")); 
		requestParams.put("deviceName", "DELL_PC"); 		 
		requestParams.put("deviceType",  "LAPTOP");
		requestParams.put("key",  "com.nitara.farmer");
		//requestParams.put("key",  "com.nitara.serviceprovider");

		request.body(requestParams.toString());

		request.header("Content-Type", "application/json");

		Response response = request.post("/v3/AM/UserLogin");
		//Print response
		response.prettyPeek();


		int  statusCode = response.getStatusCode();

		System.out.println("The status code recieved: " + statusCode);

		//Validate Status code
		Assert.assertEquals(response.getStatusCode(),200);

		String jsonString = response.asString();
		String  message = JsonPath.from(jsonString).get("message");
		//Validate success message
		//Assert.assertEquals(message,"Logged in successfully.");

		String token = JsonPath.from(jsonString).get("token");
		return (token);
	}




	public String userLogin(String username, String password,String url) {

		RestAssured.baseURI = "http://test.nitara.co.in";


		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("countryCode", "+91"); // Cast
		requestParams.put("phone", username);
		requestParams.put("Password", password);
		requestParams.put("deviceName", "DELL_PC"); 		 
		requestParams.put("deviceType",  "LAPTOP");

		request.body(requestParams.toString());

		request.header("Content-Type", "application/json");

		Response response = request.post("/AM/Login");

		int  statusCode = response.getStatusCode();

		System.out.println("The status code recieved: " + statusCode);




		String jsonString = response.asString();


		return (jsonString);
	}
	
	
	
	@Test
	public String SPToken1(String url) throws Exception {

		//String url = prop.getProperty("baseurl");
		RestAssured.baseURI = url;
		String filepath = prop.getProperty("AccountManagement");
		// Read Farmer credentials from Excel
		ExcelUtils exceldata = new ExcelUtils();
		JSONObject username = exceldata.readRowField("GeneralData", "SPusername", filepath);
		JSONObject password = exceldata.readRowField("GeneralData", "SPpassword", filepath);


		RequestSpecification request = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("countryCode", "+91"); // Cast
		requestParams.put("phone","9911223344"); // 
		requestParams.put("Pin","123456"); //
		requestParams.put("deviceName", "DELL_PC"); 		 
		requestParams.put("deviceType",  "LAPTOP");
		requestParams.put("PreferredLanguage","EN");
		//requestParams.put("key",  "com.nitara.farmer");
		requestParams.put("key",  "com.nitara.serviceprovider");

		System.out.println(requestParams);
		request.body(requestParams.toString());

		request.header("Content-Type", "application/json");

		Response response = request.post("/AM/v1/UserKeyLoginWithPinWithPreferredLanguage");

		System.out.println("\n\"/AM/v1/UserKeyLoginWithPinWithPreferredLanguage\"\n");
		//Print response
		response.prettyPeek();

		Assert.assertEquals(response.getStatusCode(),200);



		String jsonString = response.asString();
		String  message = JsonPath.from(jsonString).get("message");
		//Validate success message
		//Assert.assertEquals(message,"Logged in successfully.");

		String token = JsonPath.from(jsonString).get("token");
		return (token);
	}

}
