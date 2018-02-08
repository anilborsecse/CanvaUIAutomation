package com.canva.qe.ui.pagecomponents;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.canva.qe.ui.locators.CoverContainerLocator;
import com.canva.qe.ui.pageobjects.CoverPage;
import com.canva.qe.ui.utilities.Utility;
import com.canva.qe.ui.validator.UIValidator;

public class CoverContainerComponent extends CoverContainerLocator {

	WebDriver driver;

	public CoverContainerComponent(WebDriver driver) {
		this.driver = driver;
	}

	public CoverPage validateCoverContainerDisplayed(
			String currentSuiteScreenshotsDirectoryPath, SoftAssert sAssert) {
		UIValidator
				.validateCoverPageDisplayed(
						driver.findElement(templateImageContainerLocator)
								.isDisplayed(), sAssert);
		Utility.captureScreenshot(currentSuiteScreenshotsDirectoryPath, driver);
		return new CoverPage(driver);
	}
}
