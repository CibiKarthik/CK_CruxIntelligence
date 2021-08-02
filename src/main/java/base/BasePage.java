package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import utilities.ElementUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	protected static final Logger log = Logger.getLogger(ElementUtils.class);

	public WebDriver init_driver(String browser, String browserVersion) {
		highlight = prop.getProperty("highlight");
		// optionsManager = new OptionsManager(prop);

		log.info(browser);
		log.info(browserVersion);

		if (browser.equalsIgnoreCase("chrome")) {
			try {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} catch (Exception e) {
				log.debug(e.getMessage());
			}
		}
		if (browser.equalsIgnoreCase("iexplore")) {
			try {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			} catch (Exception e) {
				log.debug(e.getMessage());
			}
		}
		if (browser.equalsIgnoreCase("edge")) {
			try {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} catch (Exception e) {
				log.debug(e.getMessage());
			}
		}
		if (browser.equalsIgnoreCase("firefox")) {
			try {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} catch (Exception e) {
				log.debug(e.getMessage());
			}
		}
		if (browser.equalsIgnoreCase("headless")) {
			try {
				System.setProperty("capture.video", "false");
				WebDriverManager.phantomjs().setup();
			} catch (Exception e) {
				log.debug(e.getMessage());
			}
		}

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public Properties init_prop() {

		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public WebDriver acceptCookies() {
		driver.findElement(By.xpath("//a[@id ='hs-eu-confirmation-button']")).click();
		return driver;
	}

	public String getScreenshot() {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
