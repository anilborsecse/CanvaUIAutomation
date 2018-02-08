package com.canva.qe.ui.pagecomponents;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import com.canva.qe.ui.locators.TemplateCategoriesLocator;

public class TemplatesCategoriesComponent extends TemplateCategoriesLocator {

	WebDriver driver;
	
	public TemplatesCategoriesComponent(WebDriver driver){
		this.driver = driver;
	}
	
	public HashMap<String, String> returnSelectedSubCategoryAndParentCategory() {

		HashMap<String, String> hm = new HashMap<String,String>();
		
		 hm.put("subcategory", driver.findElement(subCategoryLocator)
					.getText());
		 hm.put("parent", driver.findElement(parentCategoryLocator)
				.getText());
		 
		return hm;
	}
}
