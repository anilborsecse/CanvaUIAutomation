package com.canva.qe.ui.pageobjects;

import org.openqa.selenium.WebDriver;

import com.canva.qe.ui.pagecomponents.TemplatesCategoriesComponent;
import com.canva.qe.ui.pagecomponents.TemplatesCoverComponent;
import com.canva.qe.ui.pagecomponents.TemplatesHeadersComponent;
import com.canva.qe.ui.pagecomponents.TemplatesPaginationComponent;

/*
 * TemplatesPage instantiates the component objects and has 4 components.
 * 
 * TemplatesPaginationComponent
 * TemplatesHeadersComponent
 * TemplatesCoverComponent
 * TemplatesCategoriesComponent
 */
public class TemplatesPage{
	
	WebDriver driver;
	
	public TemplatesPage(WebDriver driver){
		this.driver = driver;
	}
	
	public TemplatesPaginationComponent getTemplatesPaginationComponent(){
		return new TemplatesPaginationComponent(driver);
	}

	public TemplatesHeadersComponent getTemplatesHeaderComponent(){
		return new TemplatesHeadersComponent();
	}
	
	public TemplatesCoverComponent getTemplatesCoverComponent(){
		return new TemplatesCoverComponent(driver);
	}
	
	public TemplatesCategoriesComponent getTemplatesCategoriesComponent(){
		return new TemplatesCategoriesComponent(driver);
	}
}
