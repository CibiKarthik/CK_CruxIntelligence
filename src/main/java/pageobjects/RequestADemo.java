package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;
import utilities.ElementUtils;

public class RequestADemo extends BasePage {

	private WebDriver driver;
	private ElementUtils elementUtil;
	private By requestADemo = By.xpath("//a[@class = 'd-header-appointment']");
	private By demoListShort = By.xpath("//div[@data-id = 'event-type-header-title']");
	private By demoCheck = By.xpath("//div[@data-container = 'side-panel']//h1");
	private By Month = By.xpath("//div[@class='calendar-header']/div[@role = 'status']");
	private By nextBtn = By.xpath("//button[@aria-label = 'Go to next month']/span");
	private By dateSelect = By
			.xpath("//div[@class='calendar-table-wrapper']//button[not(@disabled) and (@aria-label)]");
	private By checkDate = By.xpath("//div[@data-component = 'spotpicker-times-subtitle']");
	private By selectTime = By.xpath("//button[@data-container = 'time-button']/div/div");
	private By confirmBtn = By
			.xpath("//div[@data-container = 'selected-spot']//button[@data-container = 'confirm-button']");
	private By nameField = By.xpath("//input[@name = 'full_name']");
	private By emailField = By.xpath("//input[@name = 'email']");
	private By messageField = By.xpath("//textarea[@type = 'textarea']");
	private By scheduleBtn = By.xpath("//button[@type = 'submit']");

	public RequestADemo(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtils(this.driver);
	}

	public String navigateToRequestDemo() {
		elementUtil.doClick(requestADemo);
		return elementUtil.waitForTitleToBePresent(10);
	}

	public String getRequestDemo(String option) {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[1]")));
		elementUtil.visibilityOfAllElements(demoListShort, 5);
		elementUtil.doClickFromList(demoListShort, option);
		String selectedOption = elementUtil.doGetText(demoCheck);
		return selectedOption;

	}

	public String selectMonth(String month) {
		String displayedmonth = elementUtil.doGetText(Month);
		for (int i = 0; i <= 2; i++) {
			if (displayedmonth.contains(month)) {
				break;
			} else {
				elementUtil.doActionsClick(nextBtn);
			}
		}
		return displayedmonth;
	}

	public String selectDate(String date) {
		elementUtil.visibilityOfAllElements(dateSelect, 5);
		String selectedDate = elementUtil.doClickFromList(dateSelect, date);
		if (selectedDate == null) {
			log.info("Wrong Date provided");
		}
		return selectedDate;
	}

	public String selectTime(String time) throws InterruptedException {
		elementUtil.visibilityOfAllElements(selectTime, 5);
		String selectedTime = elementUtil.doClickFromList(selectTime, time);
		if (selectedTime == null) {
			log.info("Wrong Time provided");
		}
		elementUtil.clickWhenReady(confirmBtn, 5);
		return selectedTime;
	}

	public Boolean enterDetails(String name, String email, String message) {
		elementUtil.doSendKeys(nameField, name);
		elementUtil.doSendKeys(emailField, email);
		elementUtil.doSendKeys(messageField, message);
		Boolean flag = elementUtil.isElementDisplayed(scheduleBtn);
		return flag;
	}
}
