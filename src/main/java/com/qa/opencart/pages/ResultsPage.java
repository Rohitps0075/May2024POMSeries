package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.Elementutil;

public class ResultsPage {
	
	private WebDriver driver;
	private Elementutil eleUtil;
	
	
	//private locators
	private By searchHeader=By.cssSelector("div#content h2");
	private By results=By.cssSelector("div.product-thumb");
	
	//public constructor
	
	public ResultsPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new Elementutil(driver);
		
	}
	
	// public actions / methods
	
	public String getSearchHeader()
	{
		String SearchHeaderValue=eleUtil.waitforVisiablityofElementLocated(searchHeader, AppConstants.DEFAULT_SHORT_TIME_OUT).getText();
		return SearchHeaderValue;
	}
	
	public int getSearchResultsCount()
	{
		int resultsCount=eleUtil.waitforElementVisiblity(results, AppConstants.DEFAULT_SHORT_TIME_OUT).size();
		return resultsCount;
	}

	public ProductInfoPage selectProduct(String productName)
	{
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
		
	}
	
	

}
