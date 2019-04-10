package com.test;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class NewTest9 { //for dynamic report and to get old reports as well
	WebDriver driver;
	ExtentHtmlReporter htmlreporter;
	ExtentReports reports;
	ExtentTest logger;
@BeforeTest
public void beforeTest()
{
	System.setProperty("webdriver.chrome.driver","C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://demowebshop.tricentis.com//login");
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-ms");
	htmlreporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/extent-reports/"+sdf.format(new Date())+".html");
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
	logger.log(Status.PASS,MarkupHelper.createLabel("This test is passed",ExtentColor.GREEN));
	
}
@Test
public void testDemoWebShop()
{
	logger=reports.createTest("Test fail");
	driver.findElement(By.id("Email")).sendKeys("askmail@email.com");
	driver.findElement(By.id("pass")).sendKeys("askmail@email.com");
	//Assert.assertTrue(false);
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
		logger.log(Status.FAIL,MarkupHelper.createLabel("This test "+result.getMethod().getMethodName()+"is failed",ExtentColor.RED));
		TakesScreenshot capture=(TakesScreenshot) driver;
		File src=capture.getScreenshotAs(OutputType.FILE);
		String capturePath=System.getProperty("user.dir")+"/extent-reports/capture/"+result.getMethod().getMethodName()+".png";
		try{
			FileUtils.copyFile(src,new File(capturePath));
			logger.addScreenCaptureFromPath(capturePath,"Demo Web Shop Screen");
			logger.log(Status.FAIL,result.getThrowable().getMessage());
			
		} catch (IOException e) {    
			e.printStackTrace();
		}
		
		
	}
	else if(result.getStatus()==ITestResult.SKIP)
	{
		logger.log(Status.SKIP,MarkupHelper.createLabel("This test "+result.getMethod().getMethodName()+"is skipped",ExtentColor.ORANGE));
		
	}
		
}
@AfterTest
public void afterTest()
{
	reports.flush();//write the updated information to the html file.
	driver.close();
}
}
