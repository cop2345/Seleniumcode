package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewTest3Test {

  @Test
  public void testDragAndDrop() {
	  System.setProperty("webdriver.chrome.driver","C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
	  WebDriver driver=new ChromeDriver();
	  driver.get("https://demos.telerik.com/aspnet-ajax/treeview/examples/overview/defaultcs.aspx");
	  driver.manage().window().maximize();
	  WebElement from=driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_RadTreeView1']/ul/li/ul/li[3]/ul/li[2]/div/div/span"));
  
  WebElement to=driver.findElement(By.id("ctl00_ContentPlaceholder1_priceChecker"));
  Actions act1=new Actions(driver);
  //act1.clickAndHold(from).release(to).perform();
  act1.dragAndDrop(from, to).perform();
  
  //Thread.sleep(10000);
  
  WebDriverWait wait=new WebDriverWait(driver,10);
  wait.until(ExpectedConditions.invisibilityOfElementWithText(By.id("ctl00_ContentPlaceholder1_Label1"),"Drop a package here to check price"));
  
  String pricemessage=driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText();
  System.out.println("Message from Price checker :" +pricemessage);
  Assert.assertTrue(pricemessage.contains("$4999"));
  		}
 }
