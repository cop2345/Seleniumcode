package com.test;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class NewTest5 {
	
	@Test
	public void testdropdown()
	{
		  System.setProperty("webdriver.chrome.driver","C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
		  WebDriver driver=new ChromeDriver();
		  driver.manage().window().maximize();
		  driver.get("http://hdfcbank.com/");
		  //driver.findElement(By.d("netsafe")).click();
		  JavascriptExecutor js=(JavascriptExecutor) driver;
		  js.executeScript("document.getElementById('netsafe').click()");
		  js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		 String title=(String)js.executeScript("return document.title");
		 //js.executeScript("alert('Welcome to js')");
		 //driver.get("http://demowebshop.tricents.com/login");
		 //js.executeScript("document.getElementById,('Email').value='askmail@email.com'");//using javascript entering value to the input field
	}
	

}
