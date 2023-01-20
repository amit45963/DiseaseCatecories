package com.nitara.RestAssured;



import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nitara.UserLogin.Login;
import com.nitara.base.GenericBase;
import com.nitara.service.NitaraService;
import com.nitara.utilities.ExcelUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/*User Story :Disease Predictor 678
@Author : Ravi Teja
 */
public class GetAllDiseases extends GenericBase{

	NitaraService results = new NitaraService();

	@Test(groups= {"Smoke"})
	@Parameters({ "url" })
	public void getAllDiseases(String url) throws Exception {

		// Login with farmer credentials
		Login user = new Login();
		String usertoken = user.userToken(url);

		Response rs = results.GetAll(url, prop.getProperty("GetCategories"),usertoken);

		System.out.println("Response code:" + rs.getStatusCode());

		System.out.println("Response:" + rs.getBody().prettyPrint().toString());

		String jsonString = rs.asString();
		String  message = JsonPath.from(jsonString).get("message");

		// Printing test case result
		if (rs.getStatusCode() == 200) {			
			System.out.println("Status: Pass");
		} else {
			System.out.println("Status: Fail");
		}

		// Comparing expected result with actual result
		Assert.assertEquals(message , "Category Data.");

	}

}
