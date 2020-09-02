package uiPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import generalUtils.UIActions;
import generalUtils.CommonPageBase;
import generalUtils.DriverScript;

public class AutomationObjects extends CommonPageBase{

	@FindBy(xpath="//div[@id='summaryContainer']/div[1]/input[1]")
	WebElement elementFromCity;
	@FindBy(xpath="//div[@id='summaryContainer']/div[2]/input[1]")
	WebElement elementToCity;
	@FindBy(xpath="//div[@id='summaryContainer']/div[3]/div[1]/span[1]")
	WebElement elementDepartDate;
	@FindBy(xpath="//div[@id='summaryContainer']/div[3]/div[1]/span[2]")
	WebElement elementReturnDate;
	@FindBy(xpath="//form[div[@id='summaryContainer']]/button[1]")
	WebElement search;
	@FindBy(xpath="//div[@class='fltrt-leg-region']/div[3]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/span[1]")
	WebElement sortPrice;
	@FindBy(xpath="//div[@class='fltrt-leg-region']/div[4]/div[1]/div[1]/div[2]/ol[1]/li[1]/div[1]")
	WebElement clickFirstTicket;
	@FindBy(xpath="//div[@className='src__Box-sc-1sbtrzs-0 jRCDLh']/div[2]/div[1]/div[1]/div[1]/div[2]")
	WebElement elementFromDateText;
	@FindBy(xpath="//div[@className='src__Box-sc-1sbtrzs-0 jRCDLh']/div[3]/div[1]/div[1]/div[1]/div[2]")
	WebElement elementToDateText;
	@FindBy(xpath="//div[@class='ui-datepicker-inline ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-datepicker-multi-2 ui-datepicker-multi']/div[2]/table/tbody/tr/td/a")
	WebElement calendar;
	@FindBy(xpath="//div[@class='loader-body fltactmdl-body']/button[1]")
	WebElement closeBox;
	
	public AutomationObjects() {
		super();
	}
	
	public AutomationObjects navigateToHomepage(String url) {
		DriverScript.openWithURL(url);
		return this;
	}
	
	public AutomationObjects enterInformation(String fromCity,String toCity,String departDate,String returnDate,String way) {
		UIActions.waitForElement(elementFromCity);
		UIActions.enterText(elementFromCity, fromCity);
		UIActions.enterText(elementToCity, toCity);
		UIActions.click(elementDepartDate);
		UIActions.click(elementReturnDate);
		UIActions.waitForElement(calendar);
		UIActions.enterDate(elementDepartDate, departDate);
		UIActions.enterDate(elementReturnDate, returnDate);
		UIActions.click(search);
		UIActions.waitForInvisibiityOfElement(By.xpath("//button[@text='Search Flights']"));
		return this;
	}

	public AutomationObjects sortPriceAscendingOrderAndSelect() {
		UIActions.switchToWindow();
		UIActions.waitForElement(closeBox);
		UIActions.click(closeBox);
		UIActions.waitForInvisibiityOfElement(By.xpath("//div[@class='loader-body fltactmdl-body']/button[1]"));
		UIActions.waitForElement(sortPrice);
		UIActions.click(sortPrice);
		UIActions.waitForElement(clickFirstTicket);
		UIActions.doubleClick(clickFirstTicket);
		return this;
	}

	public AutomationObjects verifyDateCheckout(String departDate,String returnDate) {
		UIActions.waitForElement(elementFromDateText);
		UIActions.checkText(elementFromDateText, departDate);
		UIActions.checkText(elementToDateText, returnDate);
		return this;
	}
}
