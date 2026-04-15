package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage
{

//constructor
	public MyAccountPage(WebDriver driver) 
	{
		super(driver);	
	}
	
//locators
	@FindBy (xpath="//h2[text()='My Account']")
	WebElement msgHeading;
	
	@FindBy (xpath="//a[@title='My Account']")
	WebElement clickMyaccountDropdown;
	
//added in step 6
	@FindBy (xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")
	WebElement lnkLogout;
	
//action methods
	public boolean isMyAccountPageExist()
	{
		try
		{
		return (msgHeading.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	public void MyAccountPageDropdown()
	{
	clickMyaccountDropdown.click();
	}
	
	public void clickLogout() throws InterruptedException 
	{ MyAccountPageDropdown();
		//clickMyaccountDropdown.click();
		Thread.sleep(3000);
		lnkLogout.click();
	}

}
