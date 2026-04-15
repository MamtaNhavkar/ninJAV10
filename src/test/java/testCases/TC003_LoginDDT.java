package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;


public class TC003_LoginDDT extends BaseClass
{
	
	//getting data provider from different package
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class)
	public void verify_LoginDDT(String email, String pwd, String exp)
	{
		//logger.info("*** Started TC003_LoginDDT ***");
		try {	
		//Home page
		HomePage hp1=new HomePage(driver);
		System.out.println("Click My Account");
		hp1.clickMyAccount();
		System.out.println("Click Login");
		hp1.clickLogin();
		
		//login page
		LoginPage lp= new LoginPage(driver);
		lp.setEmailAddress(email);
		lp.setPassword(pwd);
		lp.clickLoginButton();
		//My Account page
		MyAccountPage mp= new MyAccountPage(driver);
		boolean msghead= mp.isMyAccountPageExist();
	
		

if(exp.equalsIgnoreCase("Valid"))
		{ 				System.out.println("C1");

	//If test data is valid & exp is valid then Test data passed	
		
			if(msghead==true)
			{		
				System.out.println("message displayed properly");
				System.out.println(email+""+pwd+""+exp);
				mp.clickLogout();
				System.out.println("Test Passed for valid data:Logout if test data is valid and you login");
				Assert.assertTrue(true);
			}
	//If test data is valid & exp is Invalid then Test data Failed	
			else
			{
				System.out.println(email+""+pwd+""+exp);
				System.out.println("Test Failed for valid data: if test data is valid but your are not able to login");
				Assert.assertTrue(false);
			}
		}
	
		if(exp.equalsIgnoreCase("Invalid"))
		{
			//If test data is invalid & exp is valid then Test data Failed	
			if(msghead==true)
			{
				System.out.println(email+""+pwd+""+exp);
				mp.clickLogout();
				System.out.println("Test Failed for invalid data: Logout if test data is invalid but still you login");
				Assert.assertTrue(false);
			}
			//If test data is invalid & exp is invalid then Test data passed	
			else
			{
				System.out.println(email+""+pwd+""+exp);
				System.out.println("Test Passed for invalid data: if test data is invalid and your not able to login");
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		System.out.println("Done TC003");
	//logger.info("*** Finished TC003_LoginDDT ***");
	}
}
