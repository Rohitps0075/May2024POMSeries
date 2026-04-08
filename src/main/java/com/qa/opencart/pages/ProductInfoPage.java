package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.Elementutil;

public class ProductInfoPage {

	private WebDriver driver;
	private Elementutil eleUtil;

	private By productHeader = By.cssSelector("div#content h1");
	private By productMetaDate = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private By productImage= By.xpath("//ul[@class='thumbnails']/li");

	private Map<String, String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new Elementutil(driver);
	}

	public String getProductHeader() {
		String productHeaderValue = eleUtil
				.waitforVisiablityofElementLocated(productHeader, AppConstants.DEFAULT_SHORT_TIME_OUT).getText();
		System.out.println("Product Header===>" + productHeaderValue);
		return productHeaderValue;
	}

//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: Out Of Stock
	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.getElements(productMetaDate);
		System.out.println(metaList.size());

		for (WebElement meta : metaList) {
			String metaText = meta.getText();
			String metaData[] = metaText.split(":");
			String metaKey = metaData[0].trim();
			String metaValue = metaData[1].trim();
			productMap.put(metaKey, metaValue);
		}

	}

	private void getProductPriceData() {
		List<WebElement> productList = eleUtil.getElements(productPriceData);
		String productPrice = productList.get(0).getText();
		String exTaxPrice = productList.get(1).getText().split(":")[1].trim();
		productMap.put("productPrice", productPrice);
		productMap.put("exTaxPrice", exTaxPrice);
	}

	public Map<String, String> getProductData() {
		productMap = new HashMap<String, String>();// hashmap will fill not follow the order, it will execute on the bases of hascode of the key.
		//productMap = new LinkedHashMap<String, String>();// linked hasmap will follow the order of insertion, means in which order we have filled the data in that order only it will give the o/p(first upper case then lower case)
//		productMap = new TreeMap<String, String>();// this will follow the order in alphabetical order that is key value.(first upper case then lower case)
		productMap.put("Product Header====>", getProductHeader());
		getProductMetaData();
		getProductPriceData();
		System.out.println("Product Data is :-" + productMap);
		return productMap;
	}
	
	
	public int getProductImageCount()
	{
		int imageCount=eleUtil.waitforElementVisiblity(productImage,AppConstants.DEFAULT_SHORT_TIME_OUT).size();
		System.out.println("Image Count is :- "+imageCount);
		return imageCount;
		
	}
	
	
	
	
	
	
	
	
	
}
