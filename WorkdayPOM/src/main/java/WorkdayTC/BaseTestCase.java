package WorkdayTC;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import CommonFunctions.DriverFunc;
import CommonFunctions.StartDriver;
import WorkdayPF.LoginPagePF;
import CommonFunctions.ConfigManager;

public class BaseTestCase {

	StartDriver s;
	static DriverFunc driverfunc;
	
	protected LoginPagePF loginpage;
	
	public ExtentHtmlReporter html;
	public static ExtentReports report;
	public static ExtentTest classtest;
	public static ExtentTest test;
	
	public static String reportPath;
	

	private void setDriverFunc(DriverFunc driverfunc) {
		// TODO Auto-generated method stub
		BaseTestCase.driverfunc = driverfunc;
		
	}



	public static DriverFunc getDriverFunc() {
		// TODO Auto-generated method stub
		return driverfunc;
	}
	@BeforeSuite
	public void beforesuit() {
		String currentDirectory = System.getProperty("user.dir");
		SimpleDateFormat formatter = new SimpleDateFormat("MM_dd_yyyy(HH_mm_ss)");
	    reportPath=currentDirectory+"/"+formatter.format(new Date());
		Path p = Paths.get(reportPath);
	     try {
			Files.createDirectories(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		html = new ExtentHtmlReporter(reportPath+"/"+"report.html");
		report = new ExtentReports();
		report.attachReporter(html);
		
	}
	
	@BeforeTest
	public void beforeTest() {
		try {
			ConfigManager.loadProperties();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String browser = ConfigManager.getProp().getProperty("browser");
		s = new StartDriver();
		s.startDriver(browser);
		s.getDriver();
		driverfunc = new DriverFunc(s.getDriver());
		setDriverFunc(driverfunc);

		loginpage = new LoginPagePF(s.getDriver());
		classtest=report.createTest(getClass().getName());
		classtest.log(Status.INFO, "inside the before test method");
	}



	@BeforeMethod
	public void beforMethod(Method method) {
		test=classtest.createNode(method.getName());
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if(result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getMethod().getMethodName()+"has failed");
			String pa = captureScreenshot(s.getDriver());
			classtest.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(pa).build());
		}
		if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getMethod().getMethodName()+"has passed");
		}
		if(result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getMethod().getMethodName()+"has skipped");
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
