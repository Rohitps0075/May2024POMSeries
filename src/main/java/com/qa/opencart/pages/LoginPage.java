package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.Elementutil;

import io.qameta.allure.Step;

public class LoginPage {

	/*
	 * 3 things we need to keep in mind while creating a page class
	 * 
	 * 1-->private locater 2--> one public constructor 3--> public actions/ methods
	 */

	private WebDriver driver; // we declared Webdriver at class and we didn't created the object just declared
						// the Webdriver so that we can call the driver methods

	//day 2
	private Elementutil eleUtil;// lets copy the element util file from our selenium session and start using the generic methods
	
	// 1 private By locators
	/**
	 * declared all the locater in the page as private member.
	 */
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By logo = By.className("img-responsive");
	private By registerLink=By.linkText("Register");

	// 2 one public constructor

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new Elementutil(driver);
	}

	// 3 public actions/methods

	
	/**
	 * 
	 * in below methods we are using the driver and with the help of driver calling the driver methods, still driver is not initilized
	 * if we run the code will get null pointer exception because as of now driver value is null, for this reason we created a public constructor so that we can pass the 
	 * driver refrence coming from driver factory class( will create driver factory class now)
	 * 
	 */
	@Step("Getting Login Page Title")
	public String getLoginPageTitle() {
	String title=eleUtil.waitForTitleAndReturn(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIME_OUT);// this method will return the title so store it in String title and comment the below line	
		//String title = driver.getTitle();
		System.out.println("Login Page Title is: " + title);
		return title;
	}

	public String getLoginPageURL() {
		String url=eleUtil.waitforURLConatinsAndReturn(AppConstants.LOGIN_PAGE_FRACTIONAL_URL, AppConstants.DEFAULT_SHORT_TIME_OUT);// this method will return the url so store it in String url and comment the below line
		//String url = driver.getCurrentUrl();
		System.out.println("Login Page URL is: " + url);
		return url;
	}

	public boolean isforgotpwdLinkExists() {
		
		return eleUtil.isElementDisplayed(forgotPwdLink);// this method will check wheather the element is presenet or not so comment the below line
		
		//return driver.findElement(forgotPwdLink).isDisplayed();// this will be one of the example for encapsulation, here private variable used by public methods 
	}

	public boolean isLogoExists() {
		return eleUtil.isElementDisplayed(logo);
		//return driver.findElement(logo).isDisplayed();
	}

	@Step("Login with Username : {0} and Password : {1}")// this will diplay the user details in report.
	public AccountsPage doLogin(String userName, String Pwd) {
		eleUtil.waitForElementPresence(username,AppConstants.DEFAULT_SHORT_TIME_OUT).sendKeys(userName);;// this method will return the webdriver by applying the wait. so replacing below line with this.(comment out below line)
	//	driver.findElement(username).sendKeys(userName);
		eleUtil.dosendKeys(password, Pwd);// comment out below line
		//driver.findElement(password).sendKeys(Pwd);
		eleUtil.doClick(loginBtn);//comment out below line
		//driver.findElement(loginBtn).click();

		
		String accPageTitle=eleUtil.getPageTitle(AppConstants.ACCOUTN_PAGE_TITLE, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
	//	String accPageTitle = driver.getTitle();
		System.out.println(accPageTitle);

		//return accPageTitle;// we are returning the title once we login to the app for validation purpose.
		
		return new AccountsPage(driver);// this method is landing on a new page so we need to return the obecjet of new page.
		
	}

	// each and evry page class will return something they will not void in nature.
	
	public RegisterPage navigateToRegisterPage()
	{
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	
}
