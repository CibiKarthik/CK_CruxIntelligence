package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import utilities.Constants;

public class AboutUsePageTest extends BaseTest {

	private String designation;

	@Test(priority = 0)
	public void navToAbousUs() {
		String title = aboutUs.navigateToAboutUs();
		System.out.println("About Us page title is: " + title);
		Assert.assertEquals(title, Constants.ABOUT_US, "Home page title is not matched...");
	}

	@Test(priority = 1)
	public void checkTeamMembers() {
		designation = aboutUs.outTeam(Constants.SUMITH);
		Assert.assertEquals(designation, "Head of Technology");
		System.out.println("Sumith Designation: " + designation);

		designation = aboutUs.outTeam(Constants.GANESH);
		Assert.assertEquals(designation, "Head of Product");
		System.out.println("Ganesh Designation: " + designation);
	}
}
