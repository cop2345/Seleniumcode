package com.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewTest4 {
	
	@Test
	public void testKeys() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver","C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.cleartrip.com/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		WebElement from=driver.findElement(By.id("FromTag"));
		//from.sendkeys("h");
		//from.sendkeys("y");
		//from.sendkeys("d");
		Actions act1=new Actions(driver);
		act1.keyDown(from,Keys.SHIFT).perform();//to enter HYD in Capital letter
		act1.sendKeys("h").perform();
		Thread.sleep(2000);
		//act1.keyUp(from,Keys.SHIFT).perform();//to enter from Y in small letter
		act1.sendKeys("y").perform();
		Thread.sleep(2000);
		act1.sendKeys("d").perform();
		Thread.sleep(5000);
		act1.sendKeys(Keys.ENTER).perform();
		
		WebElement to=driver.findElement(By.id("ToTag"));
		/*Actions act2=new Actions(driver);
		act2.sendKeys(to,"B").perform();
		Thread.sleep(2000);
		act2.sendKeys("a").perform();
		Thread.sleep(2000);
		act2.sendKeys("n").perform();
		Thread.sleep(5000);
		act2.sendKeys(Keys.ARROW_DOWN).perform();
		act2.sendKeys(Keys.ENTER).perform();*/
		to.sendKeys("b");
		to.sendKeys("a");
		to.sendKeys("n");
		//Thread.sleep(5000);
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.id("ui-id-2"), By.className("list")));
		WebElement ul=driver.findElement(By.id("ui-id-2"));
		List<WebElement> list=ul.findElements(By.tagName("li"));//finding web element from element
		Assert.assertEquals(list.size(),10);
		
		for(WebElement e:list)
		
		{
			if(e.getText().contains("BKK"))
			{
				e.click();
				break;
			}
		}
			Assert.assertTrue(to.getAttribute("value").contains("BKK"));
	}
}
