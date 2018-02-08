package com.canva.qe.ui.locators;

import org.openqa.selenium.By;

public class TemplateCategoriesLocator {

	protected By subCategoryLocator =  By.xpath("//ul[contains(@class,'marketplaceCategoryList__children--visible')]/li[contains(@class,'marketplaceCategoryList__category--current')]/a");
	protected By parentCategoryLocator =  By.xpath("//li[contains(@class,'marketplaceCategoryList__category--current')]/a");

}
