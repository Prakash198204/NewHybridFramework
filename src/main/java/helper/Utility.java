package helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {
	
	public static WebElement findElement(WebDriver driver, By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}

	public static WebElement findElement(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}
	
	public static void clickElement(WebDriver driver, By locator, int time) {
		WebElement element = null;
		try {
			element = findElement(driver, locator, time);
			element.click();
		} catch (Exception e) {
			System.out.println("LOG : INFO - Normal click failed - Trying with Actions Class");
			Actions act = new Actions(driver);
			try {
				act.moveToElement(element).click().build().perform();
			} catch (Exception e1) {
				System.out.println("LOG : INFO - Actions click failed - Trying with JS Click");
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click()", element);
			}
		}
	}
	
	

	public static void clickElement(WebDriver driver, By locator) {
		WebElement element = null;
		try {
			element = findElement(driver, locator);
			element.click();
		} catch (Exception e) {
			System.out.println("LOG : INFO - Normal click failed - Trying with Actions Class");
			Actions act = new Actions(driver);
			try {
				act.moveToElement(element).click().build().perform();
			} catch (Exception e1) {
				System.out.println("LOG : INFO - Actions click failed - Trying with JS Click");
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click()", element);
			}
		}
	}

	public static int getNumberOfFiles(String directoryPath) {
		File src = new File(directoryPath);
		int count = src.listFiles().length;
		return count;
	}

	public static WebElement waitForWebElement(WebDriver driver, By locator, int timeToWait, int pollingIntervel) {
		// default wait is 30 seconds
		WebElement element = null;
		for (int i = 0; i < timeToWait; i++) {
			try {
				element = driver.findElement(locator);
				if (element.isDisplayed() && element.isEnabled() && element.getSize().getHeight() > 0
						&& element.getSize().getWidth() > 0) {
					highLightElement(driver, element);
					break;
				} else {
					System.out.println("Waiting for WebElement - Waiting for all states");
				}
			} catch (Exception e) {
				System.out.println("Waiting for WebElement - Retrying");
			}
			try {
				Thread.sleep(pollingIntervel);

			} catch (InterruptedException e) {

			}
		}
		return element;
	}


	public static WebElement waitForWebElement(WebDriver driver, By locator, int timeToWait) {
		// default wait is 30 seconds
		WebElement element = null;
		for (int i = 0; i < timeToWait; i++) {
			try {
				element = driver.findElement(locator);

				if (element.isDisplayed() && element.isEnabled() && element.getSize().getHeight() > 0
						&& element.getSize().getWidth() > 0) {
					highLightElement(driver, element);
					break;
				} else {
					System.out.println("Waiting for WebElement - Waiting for all states");
				}
			} catch (Exception e) {
				System.out.println("Waiting for WebElement - Retrying");
			}

			try {
				Thread.sleep(500);

			} catch (InterruptedException e) {

			}
		}
		return element;
	}

	public static WebElement waitForWebElement(WebDriver driver, By locator) {
		// default wait is 30 seconds
		WebElement element = null;
		for (int i = 0; i < 30; i++) {
			try {
				element = driver.findElement(locator);
				if (element.isDisplayed() && element.isEnabled() && element.getSize().getHeight() > 0
						&& element.getSize().getWidth() > 0) {
					highLightElement(driver, element);
					break;
				} else {
					System.out.println("Waiting for WebElement - Waiting for all states");
				}
			} catch (Exception e) {
				System.out.println("Waiting for WebElement - Retrying");
			}

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {

			}
		}
		return element;
	}

	public static void highLightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);
	}

	public static String getCurrentDateTime() {
		Date currentDate = new Date();
		SimpleDateFormat myDateFormat = new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy");
		String date = myDateFormat.format(currentDate);
		return date;
	}
	
	public static String getScreenshotAsBase64(WebDriver driver) {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		String screenshot =ts.getScreenshotAs(OutputType.BASE64);
		
		return screenshot;
	}

	public static void captureScreenshots(WebDriver driver) {
		try {
			// Captures a screenshot using the WebDriver cast as TakesScreenshot and stores
			// it as a file.
			// The screenshot is saved to the "./Screenshots/" folder with a name that
			// includes the current date and time.

			FileHandler.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),
					new File("./Screenshots/Screenshot_" + getCurrentDateTime() + ".png"));
		} catch (IOException e) {
			// Catches any IOException that may occur while saving the screenshot and prints
			// an error message.
			System.out.println("Screenshot Failed " + e.getMessage());
		}
	}

	/**
	 * Accepts a JavaScript alert and returns its text.
	 * 
	 * @param driver - WebDriver instance currently in use.
	 * @return The text of the alert.
	 */
	public static String acceptAlert(WebDriver driver) {
		// Switch to the alert and store its reference.
		Alert alt = driver.switchTo().alert();
		// Get the text of the alert.
		String alertText = alt.getText();
		// Accept the alert (click "OK").
		alt.accept();
		// Return the alert text for further use.
		return alertText;
	}

	/**
	 * Dismisses a JavaScript alert and returns its text.
	 * 
	 * @param driver - WebDriver instance currently in use.
	 * @return The text of the alert.
	 */
	public static String dismissAlert(WebDriver driver) {
		// Switch to the alert and store its reference.
		Alert alt = driver.switchTo().alert();
		// Get the text of the alert.
		String alertText = alt.getText();
		// Dismiss the alert (click "Cancel").
		alt.dismiss();
		// Return the alert text for further use.
		return alertText;
	}

	/**
	 * Sends text to a prompt-type JavaScript alert and returns its text.
	 * 
	 * @param driver - WebDriver instance currently in use.
	 * @param data   - The text to input into the alert's prompt.
	 * @return The text of the alert before sending keys.
	 */
	public static String typeInAlert(WebDriver driver, String data) {
		// Switch to the alert and store its reference.
		Alert alt = driver.switchTo().alert();
		// Get the text of the alert.
		String alertText = alt.getText();
		// Send input text to the alert (works with prompt-type alerts).
		alt.sendKeys(data);
		// Return the alert text for further use.
		return alertText;
	}

	/**
	 * Initializes and returns a WebDriver instance (Chrome) with default URL and
	 * settings.
	 * 
	 * @return A WebDriver instance for Chrome.
	 */
	public static WebDriver startBrowser() {
		// Initialize a new WebDriver instance using ChromeDriver.
		WebDriver driver = new ChromeDriver();
		// Open the default URL.
		driver.get("https://freelance-learn-automation.vercel.app/login");
		// Maximize the browser window.
		driver.manage().window().maximize();
		// Set a default implicit wait timeout for locating elements.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// Return the initialized WebDriver instance.
		return driver;
	}

	public static void browserSetting(WebDriver driver, String url) {
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	/**
	 * Initializes and returns a WebDriver instance (Chrome) for a given URL.
	 * 
	 * @param url - The URL to open in the browser.
	 * @return A WebDriver instance for Chrome.
	 */
	public static WebDriver startBrowser(String url) {
		WebDriver driver = new ChromeDriver();
		browserSetting(driver, url);
		return driver;
	}

	/**
	 * Initializes and returns a WebDriver instance for a specified browser and URL.
	 * 
	 * @param browser - Browser type (Chrome, Firefox, Edge, Safari).
	 * @param url     - The URL to open in the browser.
	 * @return A WebDriver instance for the specified browser.
	 */
	public static WebDriver startBrowser(String browser, String url) {
		WebDriver driver = null;
		// Initialize the driver based on the browser type.
		if (browser.contains("Chrome")) {
			driver = new ChromeDriver();
		} else if (browser.contains("Firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.contains("Edge")) {
			driver = new EdgeDriver();
		} else if (browser.contains("Safari")) {
			driver = new SafariDriver();
		} else {
			System.out.println("Sorry, we don't support this browser - please use Chrome, Firefox, Edge, or Safari.");
		}
		browserSetting(driver, url);
		// Return the initialized WebDriver instance.
		return driver;
	}

	/**
	 * Initializes and returns a WebDriver instance for a specified browser, URL,
	 * and implicit wait time.
	 * 
	 * @param browser      - Browser type (Chrome, Firefox, Edge, Safari).
	 * @param url          - The URL to open in the browser.
	 * @param implicitWait - The implicit wait duration (in seconds) for locating
	 *                     elements.
	 * @return A WebDriver instance for the specified browser.
	 */
	public static WebDriver startBrowser(String browser, String url, int implicitWait) {
		WebDriver driver = null;
		// Initialize the driver based on the browser type.
		if (browser.contains("Chrome")) {
			driver = new ChromeDriver();
		} else if (browser.contains("Firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.contains("Edge")) {
			driver = new EdgeDriver();
		} else if (browser.contains("Safari")) {
			driver = new SafariDriver();
		} else {
			System.out.println("Sorry, we don't support this browser - please use Chrome, Firefox, Edge, or Safari.");
		}
		browserSetting(driver, url);
		// Return the initialized WebDriver instance.
		return driver;
	}

}
