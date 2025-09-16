package com.qafox.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class ReadSeleniumMenus {
	WebDriver driver;
	WebElement element;
	List<WebElement> elements;
	String doc = "";
	boolean flag;
	int totalMenus = 0, totalSubMenus = 0;

	@Test
	public void readMenuItems() {

		// nav[@id='secondary-nav']/ul/li

		try {

			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.get("https://www.qafox.com/selenium/selenium-practice/");
			elements = driver.findElements(By.xpath("//nav[@id='secondary-nav']/ul/li"));
			Actions actions = new Actions(driver);

			if (elements.size() != 0) {

				for (WebElement item : elements) {
					element = item.findElement(By.tagName("a"));
					actions.moveToElement(element).perform();
					totalMenus++;
					doc = doc + "  " + element.getText() + "\n";
					List<WebElement> element1 = item.findElements(By.tagName("ul"));
					if (element1.size() != 0) {
						for (WebElement count : element1) {
							List<WebElement> sItems = count.findElements(By.tagName("li"));
							for (WebElement sItem : sItems) {
								String temp = sItem.findElement(By.tagName("a")).getText();
								totalSubMenus++;
								doc = doc + "\t \t-" + temp + "\n";
							}
						}

					} else {
						doc = doc + "  " + "\t\t - This Menu Doesn't have Sub Menu Items" + "\n";
					}

				}

			} else {
				doc = "no element found";
			}

			doc = "\n\n\n\n" + "The total Number of Menu's are :: " + totalMenus + "\n\n"
					+ "The Total Number of Sub Menu Items are ::" + totalSubMenus;

			System.out.println(doc);
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
