package com.canva.qe.ui.test;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.canva.qe.ui.pageobjects.CoverPage;
import com.canva.qe.ui.pageobjects.TemplatesPage;
import com.canva.qe.ui.validator.UIValidator;

public class CanvaUITest extends BaseTest {

	@Test(description = "Click any random page number from pagination, click random cover template, click Use Template button and verify it redirects user to Canva home page.")
	public void testCanvaUITestCase() {

		TemplatesPage templatePage = new TemplatesPage(driver);

		try {

			browseUrl(property.getProperty("CANVA_URL"));

			// Click random page number from pagination
			int randomPageNumber = templatePage
					.getTemplatesPaginationComponent()
					.clickRandomPageFromPagination()
					.getRandomPageNumberForValidation();

			takeScreenshot();

			UIValidator.validateRandomPageBrowseUrl(driver.getCurrentUrl(),
					randomPageNumber, softAssert);

			// Get parent and sub category of selected cover template.
			HashMap<String, String> categoriesHashMap = templatePage
					.getTemplatesCoverComponent().clickAnyRandomTemplateCover()
					.getTemplatesCategoriesComponent()
					.returnSelectedSubCategoryAndParentCategory();

			takeScreenshot();

			// Validate selected parent and sub category.
			UIValidator.validateSelectedSubCategoryIsNotNull(
					categoriesHashMap.get("subcategory"), softAssert);
			UIValidator.validateParentCategoryofSelectedSubCategory(
					property.getProperty("PARENT_CATEGORY"),
					categoriesHashMap.get("parent"), softAssert);

			Reporter.log(
					"selectedSubCategoryText: "
							+ categoriesHashMap.get("subcategory")
							+ "    Parent Category: "
							+ categoriesHashMap.get("parent"), true);

			// Click 'Use Template' button and then it takes user to home page
			// and
			// verifies if login component is displayed.
			boolean isLoginComponentDisplayedOnHomePage = new CoverPage(driver)
					.getCoverRightPaenlComponent().clickUseTemplateButton()
					.getHomePageComponent(driver)
					.checkLoginComponentDisplayed();

			takeScreenshot();

			// Validate Canva home page url.
			UIValidator.validateRedirectionToHomePage(driver.getCurrentUrl(),
					property.getProperty("CANVA_HOME_PAGE_URL"), softAssert);
			// Validate login component displayed on home page.
			UIValidator.validateLoginComponentDisplayed(
					isLoginComponentDisplayedOnHomePage, softAssert);

		} catch (Exception e) {
			takeScreenshot();
			Assert.fail("Exception occured in CanvaUITestVerification method. Exception message: "
					+ e.getMessage());
		} finally {
			softAssert.assertAll();
		}
	}
}
