package Common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.LoginPage;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseClass {

    //    protected static AppiumDriver<AndroidElement> driver;
    protected static AndroidDriver<MobileElement> driver;
    DesiredCapabilities dc = new DesiredCapabilities();
    private PropertyManager PM;
    ExtentHtmlReporter htmlReporter;
    ExtentReports report;
    public static ExtentReports extent;


    @BeforeClass
    public void setUp() throws MalformedURLException {
  //      report = new ExtentReports(System.getProperty("user.dir"));
        htmlReporter = new ExtentHtmlReporter("ExtentReportResults.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        dc.setCapability("automationName",PM.getInstance().getAutomationName()); // it is an optional but it should be Appium or any other server name. Otherwise it will give error "Session not created"
        dc.setCapability("platformName", PM.getInstance().getPlatformName());
        dc.setCapability("deviceName", PM.getInstance().getDeviceName());
        dc.setCapability("version", PM.getInstance().getDeviceOSVersion());
        dc.setCapability("appActivity",PM.getInstance().getAppActivity()); // *get it from developer
        dc.setCapability("app", PM.getInstance().getTestApp());

        String url = PM.getInstance().getURL();
        driver = new AndroidDriver <MobileElement> (new URL(url), dc);
    }

    @AfterClass
    public void teardown(){
       extent.flush();
       driver.quit();
    }

}
