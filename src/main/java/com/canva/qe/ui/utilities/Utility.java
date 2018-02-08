package com.canva.qe.ui.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class Utility {
	
	public static int generateIntegerRandom(int lowerLimit, int upperLimit) {
		return ThreadLocalRandom.current().nextInt(lowerLimit, upperLimit);
	}
	
	public static void waitForAnElementToBeVisible(WebDriverWait wait, By locator){
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public static void captureScreenshot(String currentSuiteScreenshotsDirectoryPath, WebDriver driver) {
		
		try {
			File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
			String fileName = "screenshot-" + new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date()) + ".png";
			Files.copy(screenshotFile, new File(currentSuiteScreenshotsDirectoryPath + "\\" + fileName));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
