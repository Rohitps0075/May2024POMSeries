package com.qa.opencart.Base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.ResultsPage;

import io.qameta.allure.Step;

public class BaseTest {

	DriverFactory df;
	protected WebDriver driver;
	protected LoginPage loginpage;// i have decalared this as protected because we can access methods or variable in child class
	protected Properties prop;
	protected AccountsPage accPage;
	protected ResultsPage resultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;

	protected SoftAssert softAssert;
	
	
	
	@Step("Launching the Browser:{0}")
	@Parameters({"browser"})
	@BeforeTest
	public void setup(@Optional("edge")String browserName) {
		df = new DriverFactory();// created the object of driver factory so that i can access the methods of that class
		prop=df.initProp();// we are calling the initproperties methods and this method will return the propertires, so now insted of passing the chrome to initdriver method
		// pass the prop refrence to inititDriver method
		
		if(browserName!=null)
		{
			prop.setProperty("browser", browserName);
			/*
			 * initially we use to pass the browser value from config file, some times i may pass the browser value from testng.xml file
			 * now let's know how this if condition works
			 * 
			 *i am passing the browser value from the xml file so that value will be assigned to browserName so now browserName is not null, enter the if condition and set the browser value coming from xml 
			 *file.this will we done at the run time, meaning at the run time the value in the config file will be updated by the xml value(not no changes in config file all this happens in runtime.).
			 *
			 *if we don't pass any browser value from the xml the condition will fail i.e. browserName is equal to null 
			 *so it will not enter the if condition, it will take value from the config file.
			 *
			 */
		}
		
		driver = df.initDriver(prop);// calling a method passing the refernce of an object is called call by refernce 
		
		loginpage = new LoginPage(driver);// df.initDriver("chrome") will return the driver which is initilzed in the driver factory and
											// we are passing this to Login page constructor.

		softAssert=new SoftAssert();
	}
	@Step("Closing the Browser")
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
