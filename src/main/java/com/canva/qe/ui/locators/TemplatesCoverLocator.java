package com.canva.qe.ui.locators;

import org.openqa.selenium.By;

public class TemplatesCoverLocator {

	protected By templatesCoversListLocator = By
			.xpath("//div[contains(@class,'marketplaceResultGrid')]/descendant-or-self::div[contains(@class,'marketplace__gridItem--mediaCard')]");
}
