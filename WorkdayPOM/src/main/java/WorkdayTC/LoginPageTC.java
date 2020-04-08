package WorkdayTC;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import CommonFunctions.ConfigManager;


public class LoginPageTC extends BaseTestCase{

	@Test(priority=1)
	public void verifytitle()
	{
		BaseTestCase.test.log(Status.INFO, "verify title");
		Assert.assertTrue(loginpage.isTitleMatched());
		
	}
	
	public void verifyLoginPage() throws InterruptedException
	{
		BaseTestCase.test.log(Status.INFO, "verify login page");
		loginpage.methodLoginPage(ConfigManager.getProp().getProperty("username"), ConfigManager.getProp().getProperty("password"));
	}
}
