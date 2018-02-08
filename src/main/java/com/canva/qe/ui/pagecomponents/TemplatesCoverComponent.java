package com.canva.qe.ui.pagecomponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.canva.qe.ui.locators.TemplatesCoverLocator;
import com.canva.qe.ui.pageobjects.CoverPage;
import com.canva.qe.ui.utilities.Utility;

public class TemplatesCoverComponent extends TemplatesCoverLocator {

	WebDriver driver;

	public TemplatesCoverComponent(WebDriver driver) {
		this.driver = driver;
	}

	public CoverPage clickAnyRandomTemplateCover() throws InterruptedException {
		
		List<WebElement> listOfTemplateCovers = driver
				.findElements(templatesCoversListLocator);
		
		Reporter.log("List of cover templates: "+listOfTemplateCovers.size(), true);

		int radomTemplateCover = Utility.generateIntegerRandom(1, listOfTemplateCovers.size());
		
		System.out.println("Random template cover: "+radomTemplateCover);
		
		listOfTemplateCovers
				.get(radomTemplateCover)
				.findElement(By.tagName("a")).click();

		return new CoverPage(driver);
	}
}
