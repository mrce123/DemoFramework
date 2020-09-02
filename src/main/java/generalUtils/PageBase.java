package generalUtils;

import generalUtils.DriverScript;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageBase {
	protected WebDriver driver = DriverScript.get();
	protected String pageURL = "/";
	
	public PageBase() {
		PageFactory.initElements(driver, this);
	}
	
	protected PageBase open() {
		DriverScript.openWithURL(pageURL);
		return this;
	}

}
