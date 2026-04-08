package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerPageSetUP() {
		registerPage = loginpage.navigateToRegisterPage();

	}

	public String getRandomEmail() {

		return "UiAutomation" + System.currentTimeMillis() + "@opencart.com";

	}
	
	@DataProvider
	public Object[][] getRegData()
	{
		return ExcelUtil.getTestData(AppConstants.REG_SHEET_NAME);
	}

	@Test(dataProvider = "getRegData")
	public void UserRegisterTest(String firstname, String lastname, String telephone,String password,String subscribe
			) {
		boolean value = registerPage.userRegistration(firstname, lastname, getRandomEmail(), telephone, password, subscribe);
		Assert.assertTrue(value);
	}

}
