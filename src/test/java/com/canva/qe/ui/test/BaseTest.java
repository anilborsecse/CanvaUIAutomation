package com.canva.qe.ui.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.canva.qe.ui.customexception.BrowserException;
import com.canva.qe.ui.utilities.Utility;

/**
 * BaseTest class is a parent class for all test classes and initializes driver
 * object.
 */

public class BaseTest {

	protected WebDriver driver;
	protected Properties property;
	protected FileInputStream fin;
	protected SoftAssert softAssert;

	public static String currentSuiteScreenshotsDirectoryPath;

	@BeforeSuite
	public void setUpBeforeSuite(ITestContext testContext) {
		String userDir = System.getProperty("user.dir");
		String screenshotsDirPath = userDir + "\\screenshots";
		File screenshotsDirectory = new File(screenshotsDirPath);

		if (!screenshotsDirectory.exists())
			screenshotsDirectory.mkdir();

		String testSuiteName = testContext.getSuite().getName();
		String timeStamp = new SimpleDateFormat("ddMMyyyyhhmmss")
				.format(new Date());
		currentSuiteScreenshotsDirectoryPath = screenshotsDirPath + "\\"
				+ testSuiteName + "-" + timeStamp;

		File currentSuiteScreenshotsDirectory = new File(
				currentSuiteScreenshotsDirectoryPath);
		currentSuiteScreenshotsDirectory.mkdir();
	}

	@BeforeTest
	@Parameters("browser")
	public void initialize(String browser) throws IOException,
			BrowserException, Exception {

		if (browser.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					"drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();

		} else if (browser.equals("firefox")) {

			System.setProperty("webdriver.gecko.driver",
					"drivers/geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browser.equals("android")) {

			//Its not working currently but can be extended to mWeb/tablet/iPad browsers using Appium.
			DesiredCapabilities cap = new DesiredCapabilities();

			cap.setCapability("browserName", "chrome");
			cap.setCapability("appium-version", "1.4.0");
			cap.setCapability("VERSION", "5.1.1");
			cap.setCapability("deviceName", "device");
			cap.setCapability("platformName", "ANDROID");

			driver = new RemoteWebDriver(
					new URL("http://127.0.0.1:4723/wd/hub"), cap);

		} else {
			throw new BrowserException(
					"Browser string does not matches. It should be either 'chrome' or 'firefox'");
		}

		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		property = new Properties();
		fin = new FileInputStream("TestData/TestData.properties");
		property.load(fin);
		softAssert = new SoftAssert();
	}

	public void browseUrl(String url) {
		driver.navigate().to(url);
	}

	public void takeScreenshot() {
		Utility.captureScreenshot(currentSuiteScreenshotsDirectoryPath, driver);
	}

	public WebDriver getWebDriver() {
		return this.driver;
	}

	@AfterTest
	public void tearDown() {

		if (driver != null) {
			driver.quit();
		}
		fin = null;
		property = null;
	}

}
