package WorkdayOR;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageOR {

private WebDriver driver;
	
	public LoginPageOR(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//title")
	protected WebElement title;
	
	@FindBy(id = "Email")
	protected WebElement email;
	
	@FindBy(id = "Passwd")
	protected WebElement passwordinput;
	
	@FindBy(id = "next")
	protected WebElement nextbutton;
	
	@FindBy(id = "signIn")
	protected WebElement signinbutton;
	
	@FindBy(id = "tdCheckbox")
	protected WebElement rememberdevicecheckbox;
	
	@FindBy(linkText = "Submit")
	protected WebElement submitbutton;
	

	
	
	
}
