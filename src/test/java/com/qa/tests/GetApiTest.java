package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.Base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetApiTest extends TestBase {

	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closableHttpResponse;
	
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException{
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		//https://reqres.in/api/users
		
		url = serviceUrl + apiUrl;
		
	}
	
	@Test(priority = 1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException{
		restClient = new RestClient();
		closableHttpResponse = restClient.get(url);
		
		// Status Code
				int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("Status Code-----> "+ statusCode );
				Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
				
				//JSON St
				String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("Response JSON from API----> "+ responseJson);
				
				//Single value assertion
				//per_page:
				String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
				System.out.println("PerPage Value is----> " +perPageValue);
				Assert.assertEquals(Integer.parseInt(perPageValue), 6);
				
				//total:
				String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
				System.out.println("Total Value is----> " +totalValue);
				Assert.assertEquals(Integer.parseInt(totalValue), 12);
				
				//Get the value from JSON Array
				String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
				String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
				String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
				String lastNAme = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
				String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
				
				System.out.println(id);
				System.out.println(email);
				System.out.println(firstName);
				System.out.println(lastNAme);
				System.out.println(avatar);
				
				//All Hoders
				Header[] headersArry = closableHttpResponse.getAllHeaders();
				HashMap<String, String> allHeaders = new HashMap<String, String>();
				for(Header header : headersArry){
				allHeaders.put(header.getName(), header.getValue());
				}
				
				System.out.println("Headers Array--> "+allHeaders);
	}
	
	@Test(priority = 2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException{
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		closableHttpResponse = restClient.get(url, headerMap);
		
		// Status Code
				int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("Status Code-----> "+ statusCode );
				Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
				
				//JSON St
				String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UTF-8");
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("Response JSON from API----> "+ responseJson);
				
				//Single value assertion
				//per_page:
				String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
				System.out.println("PerPage Value is----> " +perPageValue);
				Assert.assertEquals(Integer.parseInt(perPageValue), 6);
				
				//total:
				String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
				System.out.println("Total Value is----> " +totalValue);
				Assert.assertEquals(Integer.parseInt(totalValue), 12);
				
				//Get the value from JSON Array
				String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
				String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
				String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
				String lastNAme = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
				String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
				
				System.out.println(id);
				System.out.println(email);
				System.out.println(firstName);
				System.out.println(lastNAme);
				System.out.println(avatar);
				
				//All Hoders
				Header[] headersArry = closableHttpResponse.getAllHeaders();
				HashMap<String, String> allHeaders = new HashMap<String, String>();
				for(Header header : headersArry){
				allHeaders.put(header.getName(), header.getValue());
				}
				
				System.out.println("Headers Array--> "+allHeaders);
}
	
}
