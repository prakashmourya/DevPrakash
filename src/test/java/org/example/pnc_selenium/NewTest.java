package org.example.pnc_selenium;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utility.WebDriverFactory;

public class NewTest {

	private WebDriver driver;

	@Test
	public void google() throws InterruptedException {

		Actions act = new Actions(driver);
		WebElement google = driver.findElement(By.xpath("//*[@id=\"lst-ib\"]"));
		google.sendKeys("amazon");
		google.submit();
		Thread.sleep(2000);

		WebElement searchRes = 	driver.findElement(By.linkText("Amazon.com: Online Shopping for Electronics, Apparel, Computers ..."));
		act.contextClick(searchRes)
		.sendKeys(Keys.ARROW_DOWN)
		.sendKeys(Keys.ARROW_DOWN)
		.sendKeys(Keys.ENTER)
		.build()
		.perform();

		Set<String> allKeys = 	driver.getWindowHandles();

		for(String key:allKeys) {
			driver.switchTo().window(key);
		}


		String actTitle = driver.getTitle();

		Assert.assertEquals("Online", actTitle.startsWith("Online"));

		//	driver.close();

		driver.switchTo().window(driver.getWindowHandle());

	}

	@BeforeTest
	public void beforeTest() {

		driver = WebDriverFactory.ceateDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://www.google.com/");
	}

	@AfterTest
	public void afterTest() {
	}

}
