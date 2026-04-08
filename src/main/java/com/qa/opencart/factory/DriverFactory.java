package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameWorkException;

import io.qameta.allure.Step;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	public static String isHighlight; 
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();

	@Step("Initializing the driver with Properites :{0}")
	public WebDriver initDriver(Properties prop) // since we are passing the refernce of properties class to the
													// initdriver method in base class i am change the parameter from
													// String browsername to Properties prop
	// the advantage of pass the prop is we are giving the whole object reference
	// and then let the initdriver method decide which property it want to be
	// fetched.
	{
		String browserName = prop.getProperty("browser");// now we are getting the browser value from properties file.
															// pass the key value.
		System.out.println("browser name is "+browserName);
		
		 isHighlight=prop.getProperty("highlight");
		 
		 
		 OptionsManager optionsManager=new OptionsManager(prop);
		 
		 
		
		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			//driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
			break;
		case "edge":
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		default:
			System.out.println(AppError.INVALID_BROWSER + "===" + browserName);// "please pass the right browser"
																				// replaced with																	// AppError.INVALID_BROWSER
			throw new BrowserException("=====" + AppError.INVALID_BROWSER + "=====");// INVALID BROWSER is replaced with
																						// AppError.INVALID_BROWSER
		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));// updated the hardcoded value to take value from properties file.

		return getDriver();

	}

	public static WebDriver getDriver()
	{
		return tlDriver.get();
		
	}
	
	
//	public Properties initProp() {
//		prop = new Properties();
//		try {
//			// file input stream is used to create the connection b/w properties file.
//			FileInputStream ip = new FileInputStream("./scr/test/resources/config/config.properties");// dot means
//																										// current
//																										// project we
//																										// have to give
//																										// the path of
//																										// the config
//																										// file.
//			// we have built the connection , now we need to load the file;
//			prop.load(ip);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) { // don't throw the exception insted surround with try catch block because if we
//									// use throws the user has to handle the exception.
//
//			e.printStackTrace();
//		}
//
//		return prop;
//	}

	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip=null;
		String envName = System.getProperty("env");
		System.out.println("running test on env===>"+ envName);

		try {
			if (envName == null) {
				System.out.println("env is null ---hence running tests on QA env");

				ip = new FileInputStream("./scr/test/resources/config/qa.config.properties");

			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./scr/test/resources/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./scr/test/resources/config/stage.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./scr/test/resources/config/uat.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./scr/test/resources/config/dev.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./scr/test/resources/config/config.properties");
					break;
				default:
					System.out.println("please pass the right Env==>" + envName);
					throw new FrameWorkException("INVALID ENV NAME");
				}
				
			}
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * Screenshot Logic
	 * 
	 */
	public static String getScreenshot(String methodName)
	{
		File srcFile=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);// this will create the screenshot in the temp folder
		
		// we have to provide the new location to get the screenshot from temp folder to our folder
		//here user.dir means current project directory in that go to screenshot folder and save the screenshot with is name and .png extention.
		String path=System.getProperty("user.dir")+"/screenshot/"+methodName+"_"+System.currentTimeMillis()+".png";
		
		//moving file from temp to our folder.
		File destination=new File(path);
		
		try {
			FileHandler.copy(srcFile, destination);// filehandler is coming from selenium
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	
}
