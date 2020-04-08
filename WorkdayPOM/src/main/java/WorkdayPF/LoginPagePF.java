package WorkdayPF;

import org.openqa.selenium.WebDriver;
import org.testng.SkipException;

import CommonFunctions.DriverFunc;
import WorkdayOR.LoginPageOR;
import WorkdayTC.BaseTestCase;

public class LoginPagePF extends LoginPageOR{
	WebDriver driver = null;
	DriverFunc driverfunc;
	public LoginPagePF(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		driverfunc = BaseTestCase.getDriverFunc();
		System.out.println(driver.getTitle());
	}
	
	public boolean isTitleMatched()
	{
		System.out.println("Page opened");
		if(title.getAttribute("innerHTML").equalsIgnoreCase("Sign in - Google Accounts"))
		return true;
		else 
			return false;
	}
	
	public void methodLoginPage(String username, String password) throws InterruptedException
	{
		driverfunc.enterValues(email, username);
		if(email.getAttribute("value").equalsIgnoreCase(username))
		{
			System.out.println("username verified successfully = " + username);
		}
		else
			System.out.println("username verified unsuccessful" + username + "," + email.getAttribute("value"));
		driverfunc.click(nextbutton);
		driverfunc.enterValues(passwordinput, password);
		if(passwordinput.getAttribute("value").equalsIgnoreCase(password))
		{
			System.out.println("password verified successfully = " + username);
		}
		else
			System.out.println("password verified unsuccessful" + username + "," + passwordinput.getAttribute("value"));
		driverfunc.click(signinbutton);
		driverfunc.click(rememberdevicecheckbox);
		driverfunc.click(submitbutton);
		
		
	}

}
