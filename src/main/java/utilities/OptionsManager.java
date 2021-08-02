package utilities;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if(prop.getProperty("headless").trim().equals("true")) co.addArguments("--headless");		
		if(prop.getProperty("incongnito").trim().equals("true")) co.addArguments("--incongnito");
		if(prop.getProperty("cookies").trim().equals("true"))co.addArguments("enable-cookies");
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if(prop.getProperty("headless").trim().equals("true")) fo.addArguments("--headless");		
		if(prop.getProperty("incongnito").trim().equals("true")) fo.addArguments("--incongnito");
		fo.addArguments("enable-cookies");
		return fo;
	}
	
}
