package tests;

import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import utilities.Constants;

public class LogInPageTest extends BaseTest {

	SoftAssert softAssert = new SoftAssert();

	@Test(priority = 0)
	public void navToLogin() {
		String title = logIn.navigateToLogInPage();
		System.out.println("Log In page title is: " + title);
		Assert.assertEquals(title, Constants.LOG_IN, "Log In page title is not matched...");
	}

	@Test(priority = 1)
	@Parameters("url")
	public void enterWorkURL(@Optional String url) {
		String fullurl = logIn.provideLoginURL(url);
		Assert.assertNotNull(fullurl);
		Assert.assertEquals(fullurl, url + Constants.EXTENSION, "URL matches with the Page");
		softAssert.assertFalse(logIn.checkLoginURL());
	}

}
