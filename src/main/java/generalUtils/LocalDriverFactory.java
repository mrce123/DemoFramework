package generalUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;

public class LocalDriverFactory {
	public static String browserName;
	public static String url;
	
	static WebDriver createInstance() {
		FirefoxProfile fp = new FirefoxProfile();
		
		FirefoxOptions fo = new FirefoxOptions();
		fo.setProfile(fp);
		fo.setCapability(CapabilityType.OVERLAPPING_CHECK_DISABLED, true);
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Manish\\Downloads\\geckodriver-v0.27.0-win64\\geckodriver.exe");
		
		WebDriver driver;
		if(browserName.toLowerCase().contains("firefox")) {
			driver = new FirefoxDriver(fo);
			driver.manage().window().maximize();
			return driver;
		}
		return null;
	}

}
