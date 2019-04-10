package com.test;

import org.junit.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class NewTest8 { //for static report
	ExtentHtmlReporter htmlreporter;
	ExtentReports reports;
	ExtentTest logger;
@BeforeTest
public void beforeTest()
{
	htmlreporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/extent-reports/report1.html");
	reports=new ExtentReports();
	reports.attachReporter(htmlreporter);
	reports.setSystemInfo("host","localHost Training LKM");
	reports.setSystemInfo("username","aswani.kumar.avilala");
	
	htmlreporter.config().setTheme(Theme.STANDARD);
	htmlreporter.config().setReportName("Report for demo web shop");

}
@Test
public void testPass()
{
	logger=reports.createTest("Test pass");
	logger.log(Status.PASS,"This test is passed");
	
}
@Test
public void testFail()
{
	logger=reports.createTest("Test fail");
	Assert.assertTrue(false);
}
@Test
public void testSkip()
{
	logger=reports.createTest("Test skip");
	throw new SkipException("The test is skipped");
	
}
@AfterMethod
public void afterMethod(ITestResult result)
{
	if(result.getStatus()==ITestResult.FAILURE)
	{
		logger.log(Status.FAIL,"This test is failed");
				
	}
	else if(result.getStatus()==ITestResult.SKIP)
	{
		logger.log(Status.SKIP,"This test is Skipped");
	}
		
}
@AfterTest
public void afterTest()
{
	reports.flush();//write the updated information to the html file.
}
}
