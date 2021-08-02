package pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;
import utilities.ElementUtils;

public class HomePage extends BasePage {

	private WebDriver driver;
	private ElementUtils elementUtil;
	protected static final Logger log = Logger.getLogger(ElementUtils.class);
	
	private By aboutUs = By.xpath("//a[@title='About Us']");
	private By logIn = By.xpath("//a[@title='LOG IN']");
	private By requestADemo = By.xpath("//a[@class = 'd-header-appointment']");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtils(this.driver);
	}

	public String getPageTitle() {
		return elementUtil.waitForTitleToBePresent(10);
	}

	public boolean aboutUsLink() {
		return elementUtil.isElementDisplayed(aboutUs);
	}

	public boolean loginLink() {
		return elementUtil.isElementDisplayed(logIn);
	}

	public boolean requestADemo() {
		return elementUtil.isElementDisplayed(requestADemo);
	}

	public AboutUs navigateToAboutUsPage() {
		elementUtil.doClick(aboutUs);
		return new AboutUs(driver);
	}

}
