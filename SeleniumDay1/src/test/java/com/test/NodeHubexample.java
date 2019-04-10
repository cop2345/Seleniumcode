package com.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NodeHubexample{//writing code in hub and executing in registered node
	WebDriver driver;
	@BeforeTest
	@Parameters("node")
	public void beforeTest(String value) throws MalformedURLException
	{
		System.setProperty("webdriver.chrome.driver","C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
		DesiredCapabilities ds=DesiredCapabilities.chrome();
		ds.setPlatform(Platform.ANY);//Platform like window ,linux,mac
		driver=new RemoteWebDriver(new URL(value),ds);
		
		driver.manage().window().maximize();
		driver.get("http://demowebshop.tricentis.com//login");
	}
	@AfterTest
	public void afterTest()
	{     
		driver.close();
	}
	@Test(dataProvider="dp1")
	public void testValidUserDemoWebShop(String username,String password)
	{
		driver.findElement(By.id("Email")).sendKeys(username);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		driver.findElement(By.linkText("Log out")).click();
		driver.findElement(By.linkText("Log in")).click();
	}
		@DataProvider(name="dp1")
		public Object[][] providerData()
		{
			//to connect to excel and convert to object 2D array
			return ReadExcel.testExtractDataExcel();
		}
}
