package generalUtils;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestBase {
	
	
	 @BeforeMethod 
	 public void methodSetup(Method m) { 
		 Test t = m.getAnnotation(Test.class);
		 DriverScript.create(); 
	 }

	  @AfterMethod 
	  public void tearDown() {
		  DriverScript.quit(); 
	  }
	 
	
	@Parameters({"browserName", "url"})
	@BeforeSuite(alwaysRun=true)
	public void beforeSuite(@Optional("firefox") String browserName,@Optional String url) {
		LocalDriverFactory.browserName = browserName;
		LocalDriverFactory.url = url;
		DriverScript.create();
	}
	
	@AfterSuite(alwaysRun=true)
	public void AfterSuite() {
		DriverScript.quit();
	}

}
