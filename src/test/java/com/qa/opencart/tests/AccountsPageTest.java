package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.listeners.ExtentReportListener;
import com.qa.opencart.pages.AccountsPage;

//@Listeners(ExtentReportListener.class)--> enable if we want the report when we run test from the test class instead of xml
public class AccountsPageTest extends BaseTest {

	// to go to accounts page first we need to do the login
	// so the pre condition for this class is we have to do login.
	// so lets create a @BeforeClass method

	@BeforeClass
	public void accSetup() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		// we know that when we click on a button or a link or any webelement , becuase
		// of the click if we landing on a new page then that method should return the
		// object of new page.
		// now the return type of above method will be AccountsPage accpage

	}

	@Test
	public void accPageTitleTest() {
		String accTitle = accPage.getAccountsPageTitle();
		Assert.assertEquals(accTitle, AppConstants.ACCOUTN_PAGE_TITLE);

	}

	@Test
	public void isLogoutLinkExistsTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());

	}

	@Test
	public void accPageHeaderTest() {
		List<String> actualheaderList = accPage.getAccPageHeader();

		Assert.assertEquals(actualheaderList, AppConstants.EXPECTED_HEADER_LIST);
	}

	@Test
	public void searchHeaderTest() {
		resultsPage = accPage.doSearch("macbook");
		Assert.assertEquals(resultsPage.getSearchHeader(), AppConstants.RESULTS_PAGE_HEADER);
	}

	@DataProvider
	public Object[][] getSearchKey() {
		return new Object[][] { { "macbook", 3 }, { "imac", 1 }, { "samsung", 2 } };
	}

	@Test(dataProvider = "getSearchKey")
	public void searchCountTest(String searchKey, int searchCount) {
		resultsPage = accPage.doSearch(searchKey);
		Assert.assertEquals(resultsPage.getSearchResultsCount(), searchCount);
	}

	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] { { "macbook", "MacBook Air" }, { "macbook", "MacBook Pro" }, { "imac", "iMac" },
				{ "samsung", "Samsung SyncMaster 941BW" }, { "samsung", "Samsung Galaxy Tab 10.1" }

		};
	}

	@Test(dataProvider = "getSearchData")
	public void searchTest(String searchKey,String selectProduct) {
		resultsPage = accPage.doSearch(searchKey);
		productInfoPage = resultsPage.selectProduct(selectProduct);
		Assert.assertEquals(productInfoPage.getProductHeader(), selectProduct);

	}
}
