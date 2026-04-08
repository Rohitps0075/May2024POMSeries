package com.qa.opencart.constants;

import java.util.List;

public class AppConstants {

	public static final String LOGIN_PAGE_TITLE="Account Login";
	
	public static final String ACCOUTN_PAGE_TITLE="My Account";
	
	public static final String LOGIN_PAGE_FRACTIONAL_URL="route=account/login";
	
	public static final int DEFAULT_SHORT_TIME_OUT= 5;
	
	public static final int DEFAULT_MEDIUM_TIME_OUT= 10;
	
	public static final int DEFAULT_LONG_TIME_OUT= 20;
	
	public static final String RESULTS_PAGE_HEADER="Products meeting the search criteria";
	
	public static final String SUCESS_MESSAGE="Your Account Has Been Created!";

	
	
	
	public static final	List<String> EXPECTED_HEADER_LIST=List.of("My Account","My Orders","My Affiliate Account","Newsletter");
	
	
	
	//*********** excel sheet constants **************//
	
	public static final String REG_SHEET_NAME="register";

	
	/*
	 * all variables are final and static 
	 * so we can accessing then using class name
	 * and static because these will be stored in comman mempry location so these will not affect the parallel execution.
	 */
}
