package testCases;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass
{
	public static WebDriver driver;     //for extentReportManager utility we keep it static
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups={"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	void setup(String os, String br) throws IOException
	{
		System.out.println("Step 1: Opening site");
		//logger for logging logs- using log4j2.xml
		logger=LogManager.getLogger(this.getClass());
		
		//Loading config.properties file
		FileReader file= new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		// execution env=remote in properties file= using for selenium grid
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities= new DesiredCapabilities();
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if (os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else if (os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
			//browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome");
							break;
			case "edge": capabilities.setBrowserName("edge");
							break;		
			case "firefox": capabilities.setBrowserName("firefox");
							break;		
			default: System.out.println("No matching browser");
					return;
			}
		//	driver= new RemoteWebDriver(new URL("http://172.20.10.7:4444/wd/hub"), capabilities);
			driver= new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}
		
		
		//execution env=local in properties file= using for single machine
		//cross browser testing- using masterTestng.xml
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		
		switch(br.toLowerCase())
		{
		case "chrome":	driver= new ChromeDriver();
						break;
		case "edge":	driver= new EdgeDriver();
						break;
		case "firefox":	driver= new FirefoxDriver();
						break;
		default: 		System.out.println("Invalid browser");
						return;
		}
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get("https://tutorialsninja.com/demo/");
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
		System.out.println("Done with Before setup");
	}
	
	@AfterClass(groups={"Sanity","Regression","Master"})
	void tearDown()
	{
		driver.quit();
	}
	
	public String randomAlphabets()
	{
		String genertaedString=RandomStringUtils.randomAlphabetic(5);
		return genertaedString;
	}

public String randomNumbers()
	{
		String genertaedNumber=RandomStringUtils.randomNumeric(10);
		return genertaedNumber;
	}

public String randomlphanumeric()
	{
		String genertaedAlphabetics =RandomStringUtils.randomAlphabetic(3);
		String genertaedNumbers=RandomStringUtils.randomNumeric(10);	
		return (genertaedAlphabetics+"#"+genertaedNumbers);
	}

public String captureScreen(String tname) throws IOException
{
	String timestamp= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	
	TakesScreenshot takesScreenShot= (TakesScreenshot)driver;
	File sourceFile= takesScreenShot.getScreenshotAs(OutputType.FILE);
	
	String targetFilePath= System.getProperty("user.dir")+"\\screenshots\\" +tname+"_"+timestamp;
	File targetFile= new File(targetFilePath);
	
	sourceFile.renameTo(targetFile);
	
	return targetFilePath;
	}

}
