package utilities;

import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.BasePage;

public class ElementUtils extends BasePage {

	private WebDriver driver;
	private JavaScriptExe js;

	protected static final Logger log = Logger.getLogger(ElementUtils.class);

	public ElementUtils(WebDriver driver) {
		this.driver = driver;
		js = new JavaScriptExe(this.driver);
	}

	public String waitForTitleToBePresent(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(driver.getTitle()));
		String title = driver.getTitle();
		log.info("Title of the Page is: " + title);
		return title;
	}

	public List<WebElement> getElements(By locator) {
		List<WebElement> list = driver.findElements(locator);
		log.info("Size of Elements: " + list.size());
		return list;
	}

	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		log.info("Retrieving Element: " + element.getText());
		if (BasePage.highlight.equals("true")) {
			js.flash(element);
		}
		return element;
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).clear();
		log.info("Cleared text field for: " + locator);
		getElement(locator).sendKeys(value);
		log.info("Entering the value: " + value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
		log.info("Clicked on Element :" + driver.findElement(locator).getText());
	}

	public void doActionsClick(By locator) {
		Actions action = new Actions(driver);
		action.click(getElement(locator)).perform();
		log.info("Clicked on Element using Actions Class" + locator);
	}

	public String doGetText(By locator) {
		log.info("Retrieving Element text: " + getElement(locator).getText());
		return getElement(locator).getText();
	}

	public boolean isElementDisplayed(By locator) {
		log.info("Element Presence for: " + getElement(locator).getText() + " - " + getElement(locator).isDisplayed());
		return getElement(locator).isDisplayed();
	}

	public String doClickFromList(By locator, String linkText) {
		String selectedOption = null;
		List<WebElement> options = driver.findElements(locator);
		log.debug("Size of the List obtained: " + options.size());
		for (WebElement option : options) {
			log.debug("Elements available in List" + option.getText());
			if (option.getText().equalsIgnoreCase(linkText)) {
				selectedOption = option.getText();
				// js.scrollIntoView(option);
				js.clickElementByJS(option);
				break;
			}
		}
		log.debug("Expected Element is available: " + selectedOption);
		return selectedOption;
	}

	public List<WebElement> visibilityOfAllElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		List<WebElement> li = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		log.info("Explicit wait for the list of elements to be visible: " + li.size());
		return li;
	}

	public WebElement waitForElementToBeLocated(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		log.debug("Explicit wait for the Element to be Present" + element.getText());
		return element;
	}

	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public boolean isElementPresent(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			if (element != null) {
				log.debug("Element is present: " + locator.toString());
				return true;
			}
			log.warn("Element is NOT present: " + locator.toString());
			return false;
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			log.warn(e.getMessage(), e);
			return false;
		}
	}
}
