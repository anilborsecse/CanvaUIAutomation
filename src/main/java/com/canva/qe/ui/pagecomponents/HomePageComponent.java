package com.canva.qe.ui.pagecomponents;

import org.openqa.selenium.WebDriver;

import com.canva.qe.ui.locators.HomePageLocator;

public class HomePageComponent extends HomePageLocator {

	WebDriver driver;

	public HomePageComponent(WebDriver driver) {
		this.driver = driver;
	}

	public void loginToCanva() {
		// This method is for performing actions for login
	}

	public void signupToCanva() {
		// This method is for performing actions for signup
	}

	public String getHomePageCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	public boolean checkLoginComponentDisplayed() {
		return (driver.findElement(emailLocator).isDisplayed() && driver
				.findElement(passwordLocator).isDisplayed());
	}
}
