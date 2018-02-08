package com.canva.qe.ui.validator;

import org.testng.asserts.SoftAssert;

public class UIValidator {

	public UIValidator() {

	}

	public static void validateRandomPageBrowseUrl(String actualUrl,
			int pageNumber, SoftAssert softAssert) {

		softAssert
				.assertEquals(actualUrl,
						"https://www.canva.com/templates/book-covers/?page="
								+ pageNumber,
						"Page url does not matches with expected");
	}

	public static void validateCoverPageDisplayed(boolean actual,
			SoftAssert softAssert) {
		softAssert
				.assertTrue(
						actual,
						"Template cover page is not displayed after clicking one of the random cover on templates page.");
	}

	public static void validateRedirectionToHomePage(String actual,
			String expected, SoftAssert softAssert) {
		softAssert
				.assertTrue(
						actual.contains(expected),
						"Actual sign up page url does not matches with expected after user redirected to Canva signup page.");
	}

	public static void validateLoginComponentDisplayed(
			boolean isLoginComponentDisplayed, SoftAssert softAssert) {
		softAssert.assertTrue(isLoginComponentDisplayed,
				"Login component is not displayed on home page.");
	}

	public static void validateSelectedSubCategoryIsNotNull(String subCategory,
			SoftAssert softAssert) {
		softAssert.assertNotNull(subCategory);
	}

	public static void validateParentCategoryofSelectedSubCategory(
			String expectedParentCategory, String actualParentCategory,
			SoftAssert softAssert) {
		softAssert
				.assertEquals(expectedParentCategory.toLowerCase(),
						actualParentCategory.toLowerCase(),
						"Actual parent category does not matches with expected parent category.");
	}
}
