package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import base.BasePage;
import utilities.ElementUtils;
import utilities.JavaScriptExe;

public class AboutUs extends BasePage{

	private WebDriver driver;
	private ElementUtils elementUtil;
	private JavaScriptExe js;
	
	private By aboutUs = By.xpath("//a[@title='About Us']");
	private String beforeXpath = "//a[text() = '";
	private String afterXpath = "']";
	private String designationExt = "//parent::h4//parent::div//parent::div//following-sibling::section//h2" ;

	public AboutUs(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtils(this.driver);
		js = new JavaScriptExe(this.driver);
	}
	
	public String getPageTitle() {
		return elementUtil.waitForTitleToBePresent(10);
	}
	
	public String navigateToAboutUs() {
		elementUtil.doClick(aboutUs);
		return elementUtil.waitForTitleToBePresent(10);
	}
	
	public String outTeam(String name) {
		String designation = null;
		String actualXpath = beforeXpath + name + afterXpath;
		By memberName = By.xpath(actualXpath);
		if (elementUtil.isElementPresent(memberName)) {
			js.scrollIntoView(memberName);
			js.drawBorder(memberName);
			By memberDesignation = By.xpath(actualXpath + designationExt);
			designation = elementUtil.doGetText(memberDesignation);
		} else {
			System.out.println("Mentioned Member is not available");
			log.info("Mentioned Member is not available");
			Reporter.log("Mentioned Member is not available");
		}
		return designation;
	}

}
