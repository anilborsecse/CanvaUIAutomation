package com.canva.qe.ui.pagecomponents;

import org.openqa.selenium.WebDriver;

import com.canva.qe.ui.locators.CoverRightPanelLocator;
import com.canva.qe.ui.pageobjects.HomePage;

public class CoverRightPanelComponent extends CoverRightPanelLocator {

	WebDriver driver;

	public CoverRightPanelComponent(WebDriver driver) {
		this.driver = driver;
	}
	
	public HomePage clickUseTemplateButton(){
		driver.findElement(useTemplateButtonLocator).click();
		return new HomePage(driver);
	}
}
