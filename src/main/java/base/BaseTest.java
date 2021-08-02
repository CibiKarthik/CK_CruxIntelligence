package base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageobjects.AboutUs;
import pageobjects.HomePage;
import pageobjects.LogIn;
import pageobjects.RequestADemo;

public class BaseTest {

	public BasePage basePage;
	public HomePage homePage;
	public AboutUs aboutUs;
	public LogIn logIn;
	public RequestADemo requestDemo;

	public Properties prop;
	public WebDriver driver;
	public WebElement element;

	@Parameters({ "browser", "version" })
	@BeforeClass(alwaysRun = true)
	public void setUp(@Optional String browserName, @Optional String browserVersion) {
		System.out.println("Before Test Started");
		basePage = new BasePage();
		prop = basePage.init_prop();
		String browser = prop.getProperty("browser");

		if (browserName != null) {
			browser = browserName;
		}
		driver = basePage.init_driver(browser, browserVersion);
		homePage = new HomePage(driver);
		aboutUs = new AboutUs(driver);
		logIn = new LogIn(driver);
		requestDemo = new RequestADemo(driver);

		driver.get(prop.getProperty("url"));
		driver = basePage.acceptCookies();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
