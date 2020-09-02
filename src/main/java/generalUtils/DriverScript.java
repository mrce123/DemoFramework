package generalUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

@Listeners(TestReportListener.class)
public final class DriverScript {
	
	static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
	private static String baseURL = null;
	private DriverScript() {
		
	}
	
	public static WebDriver get() {
		return webDriver.get();
	}
	
	public static void set(WebDriver driver) {
		webDriver.set(driver);
	}
	
	public static void quit() {
		get().quit();
	}
	
	public static void close() {
		get().close();
	}
	
	public static void openWithURL(String URL) {
		get().get(URL);
	}
	
	public static void create() {
		set(LocalDriverFactory.createInstance());
	}
	
	public static void open(String link) {
		get().get(link);
	}

	public static WebDriverWait waitForElement(int timeOut) {
		return new WebDriverWait(get(),timeOut);
	}
	
	public static WebDriverWait waitForElementWithDefaultTimeout() {
		return waitForElement(60);
	}
}
