package utilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptExe {

	WebDriver driver;
	protected static final Logger log = Logger.getLogger(ElementUtils.class);

	public JavaScriptExe(WebDriver driver) {
		this.driver = driver;
	}

	public void flash(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String bgcolor = element.getCssValue("backgroundColor");
		log.info("Flashing over the Element: " + element.getText());
		for (int i = 0; i < 2; i++) {
			changeColor("rgb(0,200,0)", element);// 1
			changeColor(bgcolor, element);// 2
		}
	}

	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}

	public void drawBorder(By memberName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(memberName));
	}

	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		log.info("Clicking on the Element using JavaScript Executer");
	}

	public void scrollIntoView(By memberName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(memberName));
		log.info("Scrolling into View of the Element using JavaScript Executer");
	}


}
