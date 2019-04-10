package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebDriverDemo {
  @Test
  public void testLinks() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver","C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
	  WebDriver driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get("https://www.hdfcbank.com/");
	  Thread.sleep(5000);
	  Actions act1=new Actions(driver);
	  act1.moveToElement(driver.findElement(By.xpath("//a[@href='/personal/products']"))).perform();
	  Thread.sleep(3000);
	  act1.moveToElement(driver.findElement(By.xpath("//a[@href='/personal/products/cards']"))).perform();
	  Thread.sleep(3000);
	  act1.moveToElement(driver.findElement(By.xpath("//a[@href='/personal/products/cards/credit_cards']"))).click().perform();
	  Assert.assertTrue(driver.getCurrentUrl().contains("credit_cards"));
	  
  }
}
