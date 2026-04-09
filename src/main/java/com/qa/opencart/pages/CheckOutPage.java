package com.qa.opencart.pages;

public class CheckOutPage {

	int a = 105;
	
	int b= 205;
	
	
	public void Sum()
	{
		int sum=a+b;
		System.out.println("Total Sum of the Integer Value is ==>"+sum);
		
	}
	
	public static void main(String[] args) {
		CheckOutPage ch=new CheckOutPage();
		ch.Sum();
	}
	
	
	
	
}
