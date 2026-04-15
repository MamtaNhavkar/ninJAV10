package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.sun.tools.javac.util.List;

import testCases.BaseClass;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String reportName;
	
	public void onStart(ITestContext testContext)
	{
		/*SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");  //predefined java class to format date and time
		Date dt=new Date();  //predefined java class Date
		String currentdatetimestamp= df.format(dt);
		*/
		//OR
		
		String timestamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName= "Test-Report" +timestamp+".html";
		sparkReporter =new ExtentSparkReporter(".\\reports\\"+reportName);
			
		sparkReporter.config().setDocumentTitle("Ninja Test Report");
		sparkReporter.config().setReportName("Functional Testing Report of Ninja");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent= new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application","Ninja");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub module", "Customers");
		extent.setSystemInfo("User Name",System.getProperty("user.dir"));
		extent.setSystemInfo("Environment", "QA");
		
		//this value we will take from xml
			String os=testContext.getCurrentXmlTest().getParameter("os");
			extent.setSystemInfo("Operating System", os);
			
			String browser=testContext.getCurrentXmlTest().getParameter("browser");
			extent.setSystemInfo("Browser Name", browser);
			
			List<String> includedGroups= testContext.getCurrentXmlTest().getIncludedGroups();
			if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
			}

	}
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());  //to display groups in the reports
		test.log(Status.PASS, result.getName()+"Got successfully exeuted");
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());  //to display groups in the reports
		test.log(Status.FAIL, result.getName()+"Got Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
	
		//capture screenshot for fail Test case
		
		try
		{
			String imgPath= new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());  //to display groups in the reports
		test.log(Status.SKIP, result.getName()+"Got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
		
		String pathOfExtentReport= System.getProperty("user.dir")+"\\reports\\"+reportName;
		File extentReport= new File(pathOfExtentReport);
	
	try
	{
		Desktop.getDesktop().browse(extentReport.toURI());
	}
	catch(Exception e1)
	{
		e1.printStackTrace();
	}
	
	}
	
	
	

}