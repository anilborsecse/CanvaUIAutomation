package com.canva.qe.ui.pageobjects;

import org.openqa.selenium.WebDriver;

import com.canva.qe.ui.pagecomponents.HomePageComponent;

public class HomePage {

	WebDriver driver;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
	}
	
	public HomePageComponent getHomePageComponent(WebDriver driver){
		return new HomePageComponent(driver);
	}
}
