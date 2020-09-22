package Common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static Common.utility.generateFileName;

public class TestListeners extends Common.BaseClass implements ITestListener {

    public void onFinish(ITestContext Result)
    {

        System.out.println("This is onFinish Test Listener");
    }


    public void onStart(ITestContext Result)
    {

        System.out.println("This is onStart Test Listener");
    }


    public void onTestFailedButWithinSuccessPercentage(ITestResult Result)
    {
     System.out.println("This is onTestFailedButWithinSuccessPercentage Test Listener");
    }

    // When Test case get failed, this method is called.
    public void onTestFailure(ITestResult Result) {
        String screenShot = utility.captureScreenshot(driver, generateFileName(Result));
        ExtentTest test;
        test = extent.createTest(Result.getName() + " Test", "This will test " + Result.getName() + " Test case");
        if (Result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, Result.getName());
            test.log(Status.FAIL, Result.getThrowable());
            try {
                test.fail("Screen Shot : " + test.addScreenCaptureFromPath(screenShot));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // When Test case get Skipped, this method is called.
    public void onTestSkipped(ITestResult Result)
    {
        System.out.println("The name of the testcase Skipped is :"+Result.getName());
        ExtentTest test2 = extent.createTest(Result.getName()+" Test", "This will test "+Result.getName() + " Test case");
        test2.log(Status.INFO, "Starting " + Result.getName()+ " test case");
        test2.skip("Testcase: " +Result.getName()+ " is skipped...");
        extent.flush();
    }

    // When Test case get Started, this method is called.

    public void onTestStart(ITestResult Result)
    {

        System.out.println(Result.getName()+" test case started");
    }

    // When Test case get passed, this method is called.

    public void onTestSuccess(ITestResult Result)
    {
        System.out.println("The name of the testcase passed is :"+Result.getName());
        ExtentTest test1 = extent.createTest(Result.getName()+" Test", "This will test "+Result.getName() + " Test case");
        test1.log(Status.INFO, "Starting " + Result.getName()+ " test case");
        test1.pass("Testcase: " +Result.getName()+ " is passed");
        extent.flush();
    }


}
