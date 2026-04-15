package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass
{

	@Test(groups={"Regression","Master"})
	public void verify_login()
	{
		try
	
	  {
		logger.info("*** Started TC002 ***");
		//Home page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//login page
		LoginPage lp= new LoginPage(driver);
		lp.setEmailAddress(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLoginButton();
		
		//My Account page
		MyAccountPage mp= new MyAccountPage(driver);
		boolean msghead= mp.isMyAccountPageExist();
		
		Assert.assertTrue(msghead);
		System.out.println("done with test case: TC002_LoginTest");
	  }
		catch(Exception e)
		{
			Assert.fail();
		}
	}
	
	
}
