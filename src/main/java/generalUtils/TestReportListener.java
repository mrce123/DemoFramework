package generalUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import generalUtils.DriverScript;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestReportListener implements ITestListener{
	
	protected static ExtentReports reports;
	 protected static ExtentTest test;
	
	public void onStart(ITestContext context) {
		System.out.println("onStart method started");
	}
	
	public void onFinish(ITestContext context) {
		System.out.println("onFinish method started");
		long msDiff = context.getEndDate().getTime()-context.getStartDate().getTime();
		Reporter.log("Test run finished in seconds : "+msDiff);
		reports.flush();
	}
	
	public void onTestStart(ITestResult result) {
		System.out.println("New Test Started" +result.getName());
		Reporter.log(result.getMethod().getMethodName()+ " - started...",true);
	}
	
	public void onTestFnish(ITestResult result) {
		
	}
	
	public void onTestSuccess(ITestResult result) {
		System.out.println("onTestSuccess Method" +result.getName());
		Reporter.log(result.getMethod().getMethodName()+" - passed");
	}
	
	public void onTestFailure(ITestResult result) {
		System.out.println("onTestFailure Method" +result.getName());
		String testName = result.getMethod().getMethodName();
		String testOutputPath = Paths.get(result.getTestContext().getOutputDirectory()).toString();
		try {
			String suffix = new SimpleDateFormat("yyyyMMdd-hhmmss").format(new Date());
			String pageHTMLPath = Paths.get(String.format("", testOutputPath,testName,suffix)).toString();
			String pageScreenshot = Paths.get(String.format("", testOutputPath,testName,suffix)).toString();
			File src = ((TakesScreenshot)DriverScript.get()).getScreenshotAs(OutputType.FILE);
			FileUtils.writeStringToFile(new File(pageHTMLPath), DriverScript.get().getPageSource(),CharEncoding.UTF_8,true);
			FileUtils.copyFile(src, new File(pageScreenshot));
			Reporter.log("------------------------------------------------------------------------------------");
			Reporter.log(String.format("%s - failed", testName),true);
			Reporter.log(String.format("Page url where error occurred : %s", DriverScript.get().getCurrentUrl()),true);
			Reporter.log(String.format("html file can be found here : %s", pageHTMLPath),true);
			Reporter.log(String.format("screenshot file can be found here : %s",pageScreenshot),true);
			Reporter.log("------------------------------------------------------------------------------------");
			Reporter.setEscapeHtml(false);
			Reporter.log(String.format("<img src=\"data:image/png:base64,%s\" alt=\"%s\">",((TakesScreenshot)DriverScript.get()).getScreenshotAs(OutputType.BASE64),testName));

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		Reporter.log(result.getMethod().getMethodName() + " - test is skipped");
	}
	
	
}
