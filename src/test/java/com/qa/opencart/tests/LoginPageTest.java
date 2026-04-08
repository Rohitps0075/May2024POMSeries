package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.listeners.ExtentLogger;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

// all the below will be displayed in the report and under Behavior section  we can see these details.
@Epic("EPIC:- BTSPORT-1020 : Design Open Cart LOGIN PAGE")
@Feature("Feature:- BTSPORT-2145 : Login feature")
@Story("USER STORY:- 5081, All The Feature Related To Open Cart Login Page")
@Owner("ROHIT SHEPUR")
@Link(name = "LoginPage", url = "https://naveenautomationlabs.com/opencart/index.php?route=account/login")

//@Listeners(ExtentReportListener.class)--> enable if we want the report when we run test from the test class instead of xml

public class LoginPageTest extends BaseTest {

	@Severity(SeverityLevel.MINOR) // this will be displayed in the severity section
	@Description("Login Page Title Test")
	@Feature("Feature 4001 : Title test Feature")
	@Test // (retryAnalyzer = Retry.class)
	public void getLoginPageTitleTest() {
//		ExtentLogger.info("Checking login page title");
		String accTitle = loginpage.getLoginPageTitle();// in base class we declared loginpage as protected because of
														// that we are now able to access the methods.

		Assert.assertEquals(accTitle, AppConstants.LOGIN_PAGE_TITLE);// "Account Login" replaced with
																		// AppConstants.LOGIN_PAGE_TITLE
//		ExtentLogger.pass("Login page title matched successfully");
	}

	@Severity(SeverityLevel.CRITICAL) // this will be displayed in the severity section
	@Description("Login Page URL Test")
	@Feature("Feature 4002 : URL test Feature")
	@Test
	public void getLoginPageURLTest() {
		String accURL = loginpage.getLoginPageURL();
		Assert.assertTrue(accURL.contains(AppConstants.LOGIN_PAGE_FRACTIONAL_URL));
		// "route=account/login" replaced with AppConstants.LOGIN_PAGE_FRACTIONAL_URL

	}

	@Severity(SeverityLevel.BLOCKER) // this will be displayed in the severity section
	@Description("Login Page Forgot Password Link Test")
	@Feature("Feature 4003 : Title test Feature")
	@Issue("This Issue ia part of the BUG-2526 ") // here we can specify the bug id
	@Test
	public void isforgotPwdLinkExistsTest() {
		Assert.assertTrue(loginpage.isforgotpwdLinkExists());
	}

	@Severity(SeverityLevel.CRITICAL) // this will be displayed in the severity section
	@Description("Login Page Logo Test")
	@Feature("Feature 4004 : Title test Feature")
	@Test
	public void isLogoTest() {
		Assert.assertTrue(loginpage.isLogoExists());
	}

	
	@Severity(SeverityLevel.NORMAL) // this will be displayed in the severity section
	@Description("Login Page do Login Test")
	@Feature("Feature 4005 : Title test Feature")
	@Test(priority = Integer.MAX_VALUE)
	// the priority is only given for this method because since @Test methods will
	// execute in alphabetical order, if this method executes first them i above
	// methods will get failed, so we have give the priority and this will executed
	// at last.

	public void doLoginTest() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccountsPageTitle(), AppConstants.ACCOUTN_PAGE_TITLE);// "My Account" replaced
																								// with
																								// AppConstants.ACCOUTN_PAGE_TITLE
	}
	/*
	 * till now we are passing the some hardcoded value like un,pwd,url etc lets
	 * pass thsese values form the properties file.
	 * 
	 */
	/*
	 * now we are getting the value from properties file
	 */

	/*
	 * but still some hardcoded value are ther like Account login, My Account etc
	 * which are constant value so get these values from appconstants page
	 * 
	 * and we have some error messages like please pass the right browser, invalid
	 * browser , so get these values from the app erros class
	 * 
	 * 
	 */

	@Test(enabled=false)
	public void RohitTest()
	{
		System.out.println("This is to check the Unknow in the allure report");
	}
}
