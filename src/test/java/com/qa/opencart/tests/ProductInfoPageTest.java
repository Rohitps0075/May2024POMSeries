package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;

public class ProductInfoPageTest extends BaseTest{

	@BeforeClass
	public void productInfoPageSetUp() {

		accPage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));	
	}

	
	@DataProvider
	public Object[][] headerSearchData()
	{
		return new Object[][] {
			{"macbook","MacBook Pro"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"},
			{"canon","Canon EOS 5D"}
		};
	}
	
	@Test(dataProvider = "headerSearchData")
	public void ProductHeaderTest(String searchKey,String productName)
	{
		resultsPage=accPage.doSearch(searchKey);
		productInfoPage=resultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductHeader(),productName);
		
	}
	
	//below we have used hard assert, the problem with hard assert is, if the validation fails hard assert will terminate the execution immeditaly at
	// that particual line so execution will not be continued.
	//so when test contains multiple checks then go with soft assertion
	//for soft assert we need to create the object becuase soft assert methods are not static
	// hard arrests methods are static in nature so we use classname dot method name
	//for soft assert we use refrence dot method name.
	// so create the soft asset in base class so that we can use the assert in all the class
	// and replace the below validations with hard assert.
	// one more point if we are using soft assert means then at last we have to call assert.assertAll() method, this will help to show the failed validation
	// if we dont use it will not show
	
	

	@Test
	public void ProductInfoTest()
	{
		resultsPage=accPage.doSearch("macbook");
		productInfoPage=resultsPage.selectProduct("MacBook Pro");
		Map<String, String> actProductData = productInfoPage.getProductData();
		
//		Assert.assertEquals(actProductData.get("Brand"), "Apple");
//		Assert.assertEquals(actProductData.get("Product Code"), "Product 18");
//		Assert.assertEquals(actProductData.get("Reward Points"), "800");
//		Assert.assertEquals(actProductData.get("Availability"), "Out Of Stock");
//		Assert.assertEquals(actProductData.get("productPrice"), "$2,000.00");
//		Assert.assertEquals(actProductData.get("exTaxPrice"), "$2,000.00");
		
		
		softAssert.assertEquals(actProductData.get("Brand"), "Apple");
		softAssert.assertEquals(actProductData.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductData.get("Reward Points"), "800");
		softAssert.assertEquals(actProductData.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(actProductData.get("productPrice"), "$2,000.00");
		softAssert.assertEquals(actProductData.get("exTaxPrice"), "$2,000.00");
		softAssert.assertAll();
	}
	
	@DataProvider
	public Object[][] productImageData()
	{
		return new Object[][] {
			{"macbook","MacBook Pro", 4},
			{"imac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7},
			{"canon","Canon EOS 5D",3},
			{"macbook","MacBook", 5}
		};
	}
	
	@Test(dataProvider = "productImageData")
	public void ProductImageCountTest(String searchKey,String productName, int imageCount)
	{
		resultsPage=accPage.doSearch(searchKey);
		productInfoPage=resultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImageCount(),imageCount);
	}
	
	
	
	
	
}
