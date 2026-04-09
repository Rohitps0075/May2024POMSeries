package com.qa.opencart.pages;

public class P14 {

	public static void main(String[] args) {
		//find the sum of odd numbers in a given number.
		
		int no=136;
		int sum=0;
				
				while(no!=0)
				{
					int rem=no%10;
					
					if(rem%2==1)
					{
						sum=sum+rem;
					}
					no=no/10;
					
					
				}
				System.out.println(sum);
	}
}
