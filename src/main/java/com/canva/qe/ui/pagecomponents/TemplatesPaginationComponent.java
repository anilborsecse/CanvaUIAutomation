package com.canva.qe.ui.pagecomponents;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.canva.qe.ui.locators.TemplatesPaginationLocator;
import com.canva.qe.ui.utilities.Utility;

public class TemplatesPaginationComponent extends TemplatesPaginationLocator {

	WebDriver driver;
	int randomPageNumber;

	public TemplatesPaginationComponent(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * This method finds the random page on pagination using divide and conquer
	 * method and then clicks it.
	 */
	public TemplatesPaginationComponent clickRandomPageFromPagination()
			throws Exception {
		int numberOfPagesInPagination = getNumberOfPagesInPagination();

		randomPageNumber = Utility.generateIntegerRandom(1,
				numberOfPagesInPagination);
		Reporter.log("Number of pages on Pagination: "
				+ numberOfPagesInPagination + "  Random Page Number: "
				+ randomPageNumber, true);

		boolean isPageFoundAndClickable = makeRandomPageNumberVisibleInPagination(
				numberOfPagesInPagination, randomPageNumber);

		if (isPageFoundAndClickable) {
			driver.findElement(
					By.xpath("//a[@data-model-id='" + randomPageNumber + "']"))
					.click();
		} else {
			throw new Exception(
					"Random Page number not found or something went wrong. Please check captured screenshots to debug the problem.");
		}
		return this;
	}

	/*
	 * This method finds random page number link based on divide and conquer method
	 * (not exactly but bit similar) and checks whether it is closer to starting pages
	 * or last pages of pagination.
	 */
	public boolean makeRandomPageNumberVisibleInPagination(
			int numberOfPagesInPagination, int randomPageNumber) {

		boolean isPageNumberVisibleToClick = false;

		// Making implicit wait to wait for only 5 seconds during pagination
		// check
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		int firstPageNumber = 0;
		int middlePageNumber = 0;
		int LastPageNumber = numberOfPagesInPagination;

		if (randomPageNumber < 4
				|| randomPageNumber == numberOfPagesInPagination) {

			isPageNumberVisibleToClick = true;

		} else if (randomPageNumber >= 4) {

			/*
			 * Calculating middle page number to find random page either in
			 * forward direction from starting pages or backward direction from
			 * last pages.
			 */
			middlePageNumber = (firstPageNumber + LastPageNumber) / 2;

			if (randomPageNumber < middlePageNumber) {
				// Navigate through through starting pages.
				isPageNumberVisibleToClick = findPagesInPaginationFromBegining(
						middlePageNumber, randomPageNumber);
			} else if (randomPageNumber >= middlePageNumber) {
				// Navigate through last pages.
				isPageNumberVisibleToClick = findPagesInPaginationFromLastPages(
						LastPageNumber, middlePageNumber, randomPageNumber);
			}
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return isPageNumberVisibleToClick;
	}

	// This method finds the random page from starting pages of pagination.
	public boolean findPagesInPaginationFromBegining(int middle,
			int randomPageNumber) {
		int startPageNumber = 1;

		boolean isPageFoundOnPagination = false;

		while (true) {
			try {
				if ((driver.findElement(By.xpath("//a[@data-model-id='"
						+ randomPageNumber + "']")).isDisplayed())
						&& startPageNumber < middle) {

					isPageFoundOnPagination = true;
					break;
				}
			} catch (NoSuchElementException e) {

				// Click from starting pages to find correct random page
				driver.findElement(
						By.xpath("//a[@data-model-id='" + (startPageNumber)
								+ "']")).click();

				startPageNumber = startPageNumber + 2;
			}
		}

		return isPageFoundOnPagination;
	}

	// This method finds the random page from last pages of pagination.
	public boolean findPagesInPaginationFromLastPages(int lastPageNumber,
			int middle, int randomPageNumber) {

		boolean isPageFoundOnPagination = false;

		while (true) {
			try {
				if ((driver.findElement(By.xpath("//a[@data-model-id='"
						+ randomPageNumber + "']")).isDisplayed())
						&& lastPageNumber >= middle) {

					isPageFoundOnPagination = true;
					break;
				}
			} catch (NoSuchElementException e) {

				// Click from last pages to find correct random page.
				driver.findElement(
						By.xpath("//a[@data-model-id='" + (lastPageNumber)
								+ "']")).click();

				lastPageNumber = lastPageNumber - 2;
			}
		}

		return isPageFoundOnPagination;
	}

	// This method returns the number of pages in pagination.
	public int getNumberOfPagesInPagination() {

		List<WebElement> listOfPages = driver
				.findElements(templatesPaginationLocator);

		// To get last page number from pagination.
		if (listOfPages.get(listOfPages.size() - 1).getText().contains("→")) {
			return Integer.parseInt(listOfPages.get(listOfPages.size() - 2)
					.getText());
		} else {
			return Integer.parseInt(listOfPages.get(listOfPages.size() - 1)
					.getText());
		}
	}
	
	public int getRandomPageNumberForValidation(){
		return randomPageNumber;
	}
}
