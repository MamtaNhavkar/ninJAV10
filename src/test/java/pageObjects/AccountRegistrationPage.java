package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage 
{

//constructor
	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
	}
	
//locators
	@FindBy (xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy (xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy (xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy (xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy (xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy (xpath="//input[@id='input-confirm']")
	WebElement txtPasswordConfirm;
	
	@FindBy (xpath="//div[@class='form-group']//div[@class='col-sm-10']")
	WebElement rdbtnSubscribe;
	
	@FindBy (xpath="//input[@name='agree']")
	WebElement chkAgree;
	
	@FindBy (xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy (xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
//Action Methods
	public void setFirstName(String fname)
	{
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String phone)
	{
		txtTelephone.sendKeys(phone);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void setPasswordConfirm(String pwd)
	{
		txtPasswordConfirm.sendKeys(pwd);
	}
	
	public void clickSubscribe()
	{
		rdbtnSubscribe.click();
	}
	
	public void clickAgree()
	{
		chkAgree.click();
	}
	
	public void clickContinue()
	{
		btnContinue.click();
	}
	
	public String msgConfirmation()
	{
		try
		{
			return(msgConfirmation.getText());
				}
		catch(Exception e)
		{
			return (e.getMessage());
		}
	}
}
