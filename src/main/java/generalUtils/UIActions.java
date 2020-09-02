package generalUtils;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIActions{
	
	
	//Click button
	public static void click(WebElement el) {
		el.click();
	}
	
	//enter text
	public static void enterText(WebElement el,String value) {
		el.click();
		el.clear();
		el.sendKeys(value);
	}
	
	//double click on element
	public static void doubleClick(WebElement el) {
		Actions a = new Actions(DriverScript.get());
		a.doubleClick(el);
	}
	
	//enter date
	public static void enterDate(WebElement el,String value) {
		String splitter[] = value.split(" ");
		String month = splitter[0];
		String day = splitter[1];
		selectDate(el,month,day);
	}
	
	public static void selectDate(WebElement el, String month, String day) {
		List<WebElement> firstMonth = DriverScript.get().findElements(By.xpath("//div[@id='datePickerContainer']/div[1]/div[1]/div[1]/div[1]/span[1]"));
		List<WebElement> secondMonth = DriverScript.get().findElements(By.xpath("//div[@id='datePickerContainer']/div[1]/div[2]/div[1]/div[1]/span[1]"));

		for (int i=0; i<firstMonth.size();i++) {
			if((firstMonth.get(i).getText().contains(month))||(secondMonth.get(i).getText().contains(month)))
			{
				if(firstMonth.get(i).getText().contains(month)){			
					//Selecting the date				
					List<WebElement> days = DriverScript.get().findElements(By.xpath("//div[@class='ui-datepicker-inline ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-datepicker-multi-2 ui-datepicker-multi']/div[1]/table/tbody/tr"));

					for (WebElement d:days)
					{					
						System.out.println(d.getText());
						if(d.getText().equals(day))
						{
							d.click();
							return;
						}
					}								
				}
				if(secondMonth.get(i).getText().contains(month)) {
					List<WebElement> days = DriverScript.get().findElements(By.xpath("//div[@class='ui-datepicker-inline ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-datepicker-multi-2 ui-datepicker-multi']/div[2]/table/tbody/tr/td/a"));
					if(days.size()>0) {
					for (WebElement d:days)
					{
						System.out.println(d.getText());
						if(d.getText().equals(day))
						{
							d.click();
							return;
						}
					}
					}
					List<WebElement> returnDays = DriverScript.get().findElements(By.xpath("//div[@class='ui-datepicker-inline ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-datepicker-multi ui-datepicker-multi-2']/div[2]/table/tbody/tr/td/a"));
					if(returnDays.size()>0) {
						for (WebElement d:returnDays)
						{
							System.out.println(d.getText());
							if(d.getText().equals(day))
							{
								d.click();
								return;
							}
						}
						}
				}
			}
		}
		DriverScript.get().findElement(By.xpath("//div[@class='ui-datepicker-inline ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-datepicker-multi ui-datepicker-multi-2']/div[2]/div/a/span")).click();
		selectDate(el,month,day);
	}
	
	//capture text
	public static String getText(WebElement el) {
		String text = el.getText();
		return text;
	}

	//Verify text
	public static boolean checkText(WebElement el,String value) {
		if(el.getText().contains(value)) {
			return true;
		}
		return false;
	}
	
	//select value from dropdown
	public static void selectValueFromDropdown(Select s, String value) {
		s.deselectByValue(value);
	}
	
	//JavaScript click
	public static void clickWithJS(WebElement el) {
		((JavascriptExecutor)DriverScript.get()).executeScript("arguments[0].click();", el);
	}
	
	//Element is not visible
	public static void waitForInvisibiityOfElement(By locator) {
		DriverScript.waitForElementWithDefaultTimeout().until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	//Waits for elements
	public static WebElement waitForElement(WebElement el) {
		DriverScript.waitForElementWithDefaultTimeout().until(ExpectedConditions.visibilityOf(el));
		return el;
	}

	//switch to new window
	public static void switchToWindow() {
		String parentWindow = DriverScript.get().getWindowHandle();
		Set<String> winSize = DriverScript.get().getWindowHandles();
		for(String window:winSize) {
			if(!window.contains(parentWindow)) {
				DriverScript.get().switchTo().window(window);
				return;
			}
		}
		if(DriverScript.get().getWindowHandles().size()==1) {
			throw new IllegalStateException("only parent window was found");
		}
	}
	
}
