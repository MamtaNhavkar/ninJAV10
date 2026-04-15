package testCases;

//import java.time.Duration;

//import org.apache.commons.lang3.RandomStringUtils;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;


public class TC001_AccountRegistrationTest extends BaseClass
{
	
	
	@Test(groups={"Sanity","Master"})
	public void verify_Account_Registration() throws InterruptedException
	{
	  try
	  {
		logger.info("*** Started TC001 ***");
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account link");
		
		hp.clickRegister();
		logger.info("Clicked on My Register link");
		
		logger.info("Enter all details");
		AccountRegistrationPage rp= new AccountRegistrationPage(driver);
		rp.setFirstName(randomAlphabets().toUpperCase());
		rp.setLastName(randomAlphabets().toUpperCase());
		rp.setEmail(randomAlphabets()+"@gmail.com");
		rp.setTelephone(randomNumbers());
		
		String Password= randomlphanumeric();
		
		logger.info("Set password");
		rp.setPassword(Password);
		rp.setPasswordConfirm(Password);
		rp.clickSubscribe();
		rp.clickAgree();
		rp.clickContinue();
		
		Thread.sleep(3000);
		logger.info("Validation message");
		String confirmationMessage=rp.msgConfirmation();
		if(confirmationMessage.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
			System.out.println("done with test case: TC001_AccountRegistrationTest");
		}
		else
		{
			logger.error("Test Failed");
			logger.debug("Test Debug");
			Assert.assertTrue(false);
		}
	}
	catch(Exception e)
	  {
		Assert.fail();
	  }
	  logger.info("*** Finished TC001 ***");
	  } 
	
}
