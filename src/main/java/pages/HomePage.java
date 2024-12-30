package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	By welcomeMsg=By.className("welcomeMessage");
	By manage = By.xpath("//span[text()='Manage']");
	By manageCourses=By.xpath("//img[@alt='manage course']");
	By manageCategory=By.xpath("//img[@alt='manage category']");
	
	public String getWelcomeMsg() {
		String welcomeMessage =driver.findElement(welcomeMsg).getText();
		return welcomeMessage;
	}
	
	public Courses clickOnManageCourse() {
		Actions act= new Actions(driver);
		act.moveToElement(driver.findElement(manage)).perform();
		driver.findElement(manageCourses).click();
		
		Courses courses = PageFactory.initElements(driver, Courses.class);
		return courses;
	}
	
	public Category clickOnManageCategory() {
		Actions act= new Actions(driver);
		act.moveToElement(driver.findElement(manage)).perform();
		driver.findElement(manageCategory).click();
		
		return PageFactory.initElements(driver, Category.class);
	}
}
