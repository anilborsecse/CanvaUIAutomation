package com.canva.qe.ui.pageobjects;

import org.openqa.selenium.WebDriver;

import com.canva.qe.ui.pagecomponents.CoverRightPanelComponent;
import com.canva.qe.ui.pagecomponents.CoverContainerComponent;
import com.canva.qe.ui.pagecomponents.TemplatesCategoriesComponent;

/*
 * CoverPage re-uses TemplatesCategoriesComponent as its same on TemplatesPage and CoverPage.
 */
public class CoverPage {

	WebDriver driver;

	public CoverPage(WebDriver driver) {
		this.driver = driver;
	}

	public CoverRightPanelComponent getCoverRightPaenlComponent() {
		return new CoverRightPanelComponent(driver);
	}
	
	public CoverContainerComponent getCoverContainerComponent(){
		return new CoverContainerComponent(driver);
	}
	
	public TemplatesCategoriesComponent getTemplatesCategoriesComponent(){
		return new TemplatesCategoriesComponent(driver);
	}
}
