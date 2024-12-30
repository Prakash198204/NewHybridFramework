package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import dataprovider.CustomDataProvider;
import pages.LoginPage;

public class LoginTest extends BaseClass {
	
	@Test(description = "This test will verify valid login scenario", dataProvider="login", dataProviderClass=CustomDataProvider.class)
	public void validLoginTest(String username, String password) {
		
		LoginPage login = new LoginPage(driver);
		login.loginToApplication(username, password);
		Assert.assertTrue(driver.findElement(By.className("welcomeMessage")).isDisplayed());
				
	}

}
