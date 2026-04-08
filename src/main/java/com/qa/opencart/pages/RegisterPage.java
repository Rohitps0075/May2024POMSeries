package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.Elementutil;

public class RegisterPage {

	private WebDriver driver;
	private Elementutil eleUtil;

	// private locators
	private By firstname = By.id("input-firstname");
	private By lastname=By.id("input-lastname");
	private By email=By.id("input-email");
	private By telephone=By.id("input-telephone");
	private By password=By.id("input-password");
	private By confirmpassword=By.id("input-confirm");
	private By yesradiobtn=By.xpath("//label[normalize-space()='Yes']//input[@name='newsletter']");
	private By noradiobtn=By.xpath("//input[@value='0']");
	private By privacypolicy=By.cssSelector("input[value='1'][name='agree']");
	private By continuebtn=By.xpath("//input[@value='Continue']");
	private By sucessMsg=By.cssSelector("div#content h1");
	private By logout=By.linkText("Logout");
	private By registerLink=By.linkText("Register");

	// public constructor

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new Elementutil(driver);
	}

	public boolean userRegistration(String firstName, String lastName,String Email,String Telephone,String Password,String Subscribe) {
		eleUtil.waitforVisiablityofElementLocated(firstname, AppConstants.DEFAULT_SHORT_TIME_OUT).sendKeys(firstName);
		
		eleUtil.dosendKeys(lastname, lastName);
		eleUtil.dosendKeys(email, Email);
		eleUtil.dosendKeys(telephone, Telephone);
		eleUtil.dosendkeys(password, Password);
		eleUtil.dosendKeys(confirmpassword, Password);
		
		if(Subscribe.equalsIgnoreCase("yes"))
		{
			eleUtil.doClick(yesradiobtn);
		}
		else
		{
			eleUtil.doClick(noradiobtn);
		}
		
		eleUtil.doClick(privacypolicy);
		eleUtil.doClick(continuebtn);

		
		String SuccessMsg=eleUtil.waitforVisiablityofElementLocated(sucessMsg, AppConstants.DEFAULT_SHORT_TIME_OUT).getText();
		System.out.println(SuccessMsg);
		
		if(SuccessMsg.contains(AppConstants.SUCESS_MESSAGE))
		{
			eleUtil.doClick(logout);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
		
	}

}
