package uiScenarios;

import java.io.FileNotFoundException;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import uiPageObjects.UIDataInteraction;
import uiPageObjects.ApiDataInteraction;
import uiPageObjects.AutomationObjects;

public class TestScenarios {
	
	private UIDataInteraction firstData;
	private ApiDataInteraction secondData;
	
	public TestScenarios() {
		firstData = new UIDataInteraction("firstCity");
		secondData = new ApiDataInteraction("secondCity");
	}
	
	@Test(groups="UI")
	public void Test001_searchForLocation() {
		new AutomationObjects()
				.navigateToHomepage("https://www.cheapair.com/")
				.enterInformation(firstData.getFromCity(),firstData.getToCity(),firstData.getDepartDate(),firstData.getReturnDate(),firstData.getWay())
				.sortPriceAscendingOrderAndSelect()
				.verifyDateCheckout(firstData.getDepartDate(),firstData.getReturnDate());
	}

	@Test(groups="API")
	public void Test002_apiCreateEmployee() {
		Response response = RestAssured.given().baseUri("http://dummy.restapiexample.com").post("api/vi/create");
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

	@Test(groups="API")
	public void Test003_apiGetValidateEmployeeName() {
		Response response = RestAssured.given().baseUri("http://dummy.restapiexample.com").get("api/v1/employee/719");
		if((response.getStatusCode()>=200)&&(response.getStatusCode()<300)) {
			String name = response.asString();
			JsonPath jp = new JsonPath( name );
			String value = jp.getString("name");
			Assert.assertEquals(value, secondData.getName());
		}
	}

	@Test(groups="API")
	public void Test004_apiUpdateNameAgeOfEmployee() {
		Response response = RestAssured.given().baseUri("http://dummy.restapiexample.com").put("api/v1/update/719");
		if((response.getStatusCode()>=200)&&(response.getStatusCode()<300)) {
			String name = response.asString();
			JsonPath jp = new JsonPath( name );
			String value = jp.getString("name");
			Assert.assertEquals(value, "");
		}
	}
}
