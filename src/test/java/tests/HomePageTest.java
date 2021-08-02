package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import utilities.Constants;

public class HomePageTest extends BaseTest {
	
	@Test(priority = 0)
	public void verifyLoginPageTitleTest() {
		String title = homePage.getPageTitle();
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE, "Home page title is not matched...");
	}

	@Test(priority = 1)
	public void checkHeaderLinks() {
		Assert.assertTrue(homePage.aboutUsLink());
		Assert.assertTrue(homePage.loginLink());
		Assert.assertTrue(homePage.requestADemo());
		

	}

}
