package CommonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class StartDriver {

	static WebDriver driver = null;
	public void startDriver(String browser)
	{
		switch(browser)
		{
		case "chrome" :
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\raju.nadimpalli\\Downloads\\zip_jar_files\\EXE file\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "firefox" :
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\raju.nadimpalli\\Downloads\\zip_jar_files\\geckodriver-v0.26.0-win64.zip\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "explorer" :
			System.setProperty("webdriver.IE.driver", "C:\\Users\\raju.nadimpalli\\Downloads\\zip_jar_files\\IEDriverServer_x64_3.150.1.zip\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		default :
			System.out.println("browser not available");
			break;
		}
		driver.get(ConfigManager.getProp().getProperty("url"));
		setDriver(driver);
	}
	public WebDriver getDriver()
	{
		return driver;
	}
	public static void setDriver(WebDriver driver)
	{
		StartDriver.driver = driver;
	}
}
