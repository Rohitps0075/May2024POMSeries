package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.Elementutil;

public class AccountsPage {

	private WebDriver driver;
	private Elementutil eleUtil;
	// private By Locators

	private By logoutLink = By.linkText("Logout");
	private By headers = By.xpath("//div[@id='content']//h2");
	private By search = By.xpath("//input[@name='search']");
	private By searchBtn = By.xpath("//span[@class='input-group-btn']");

	// public constructor

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new Elementutil(driver);
	}

	// public actions/methods

	public String getAccountsPageTitle() {
		String accTitle = eleUtil.waitForTitleAndReturn(AppConstants.ACCOUTN_PAGE_TITLE,
				AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		System.out.println(accTitle);
		return accTitle;
	}

	public boolean isLogoutLinkExist() {
		return eleUtil.isElementDisplayed(logoutLink);
	}

	public List<String> getAccPageHeader() {
		List<WebElement> headerList = eleUtil.waitforElementVisiblity(headers, AppConstants.DEFAULT_MEDIUM_TIME_OUT);

		List<String> headerValueList = new ArrayList<String>();

		for (WebElement e : headerList) {
			String header = e.getText();
			headerValueList.add(header);
		}
		return headerValueList;

	}
	
	public ResultsPage doSearch(String keyValue)
	{
		WebElement searchEle=eleUtil.waitforVisiablityofElementLocated(search, AppConstants.DEFAULT_SHORT_TIME_OUT);
		searchEle.clear();
		searchEle.sendKeys(keyValue);
		eleUtil.doClick(searchBtn);
		
		return new ResultsPage(driver);
	}
	
	// each and every page class will return something, but as of now we are not returning anything from doSearch method
	// let's create the AccountsPageTest class.
	
}
