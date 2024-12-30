package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import helper.Utility;

public class LoginPage {
	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
			this.driver=driver;
	}
	
	By username = By.id("email1");
	By password = By.name("password1");
	By signin =By.className("submit-btn");
	By menu = By.xpath("//img[@alt='menu']");
	By logout =By.xpath("//button[text()='Sign out']");
	
	public HomePage loginToApplication(String uname, String pword) {
		
	/*	driver.findElement(username).sendKeys(uname);
		driver.findElement(password).sendKeys(pword);
		driver.findElement(signin).click();
		Utility.waitForWebElement(driver, menu, 5);
		driver.findElement(menu).click();
		Utility.waitForWebElement(driver, menu, 5);
		driver.findElement(logout).click(); */
		
		Utility.findElement(driver, username).sendKeys(uname);
		Utility.findElement(driver, password).sendKeys(pword);
		Utility.clickElement(driver, signin);
		Utility.waitForWebElement(driver, menu, 10);
		Utility.clickElement(driver, menu);
		Utility.waitForWebElement(driver, logout, 10);
		Utility.clickElement(driver, logout);
				
		HomePage home = PageFactory.initElements(driver, HomePage.class);
		return home;
		
	}
	
	

}
