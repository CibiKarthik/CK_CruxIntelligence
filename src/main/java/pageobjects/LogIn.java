package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;
import utilities.ElementUtils;

public class LogIn extends BasePage{

	private ElementUtils elementUtil;

	private By logIn = By.xpath("//a[@title='LOG IN']");
	private By workURL = By.cssSelector("#userInput");

	private By extension = By.xpath("//input[@id = 'userInput']/parent::span/following-sibling::span");
	private By errorURL = By.cssSelector("#loginError");
	private By logInBtn = By.xpath("//div[@class='login-action']");

	public LogIn(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtils(this.driver);
	}

	public String getPageTitle() {
		return elementUtil.waitForTitleToBePresent(10);
	}

	public String navigateToLogInPage() {
		elementUtil.doClick(logIn);
		return elementUtil.waitForTitleToBePresent(10);
	}



	public String provideLoginURL(String url) {
		elementUtil.waitForElementToBeLocated(workURL, 10);
		elementUtil.doSendKeys(workURL, url);
		String ext = elementUtil.doGetText(extension);
		String fullUrl = url + ext;
		log.info("Entered URL is: " + url + ext);
		return fullUrl;
	}

	public Boolean checkLoginURL() {
		String message = null;
		elementUtil.isElementDisplayed(logInBtn);
		elementUtil.doClick(logInBtn);
		if (elementUtil.waitForElementToBeLocated(errorURL, 10) != null) {
			message = elementUtil.doGetText(errorURL);
			log.info("Message is Displayed: " + message);
			return false;
		} else {
			message = "Entered Valid URL & page navigated";
			String title = this.getPageTitle();
			log.info("Navigated to: " + title);
			return true;
		}
	}

}
