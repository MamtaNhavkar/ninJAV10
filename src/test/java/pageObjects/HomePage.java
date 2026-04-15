package pageObjects;

//import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage
{
	
//constructor	
	public HomePage(WebDriver driver)
	{
	super(driver);
	}
	//a[@title='My Account']
//locators
	@FindBy (xpath="//a[@title='My Account']")
	WebElement lnkMyAccount;

	@FindBy (xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	// added in step 5
	@FindBy (xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
//Action Methods
	public void clickMyAccount() 
	{
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.elementToBeClickable
				lnkMyAccount.click();
	}
	
	public void clickRegister()
	{
		lnkRegister.click();
	}
	
	public void clickLogin()
	{ 
	//  wait.until(ExpectedConditions.visibilityOf(lnkLogin));
  //  wait.until(ExpectedConditions.elementToBeClickable
		lnkLogin.click();
	}
}
