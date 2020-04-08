package CommonFunctions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import WorkdayTC.BaseTestCase;

public class DriverFunc {

	private WebDriver driver = null;
	public DriverFunc(WebDriver driver)
	{
		super();
		this.driver = driver;
	}
	public void enterValues(WebElement ele, String value)
	{
		BaseTestCase.test.log(Status.INFO, "enter value as "+value);
		try {
		ele.sendKeys(value);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			captureScreenshot(driver);
			BaseTestCase.test.log(Status.ERROR, "in exception block  "+e.getMessage());
		}
		
	}
	public void click(WebElement ele)
	{
		BaseTestCase.test.log(Status.INFO, "click on element"+ele.getText());
		try {
			ele.click();
		}catch (Exception e) {
			System.out.println("click error");

			captureScreenshot(driver);
			System.out.println(e.getMessage());
			BaseTestCase.test.log(Status.ERROR, "in exception block  "+e.getMessage());
		}
	}
	public static String captureScreenshot(WebDriver driver) {
        //driver = GetDriver.getDriver();
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String fileName = "";
        try {
            // now copy the screenshot to desired location using copyFile //method
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");
            String stringDate = dateFormat.format(new Date());
            String saveLocation =BaseTestCase.reportPath + ".png";
            fileName =
                    saveLocation.replace(
                            System.getProperty("user.dir") + File.separator + File.separator,
                            "");
            FileUtils.copyFile(src, new File(saveLocation));
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return fileName;
    }
}
