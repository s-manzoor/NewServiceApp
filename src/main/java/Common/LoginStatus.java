package Common;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import pageObjects.LoginPage;
import pageObjects.ServicePage;

public class LoginStatus extends BaseClass {
    PropertyManager PM;
    String email = PM.getInstance().getEmail();
    String password = PM.getInstance().getPassword();

    private ServicePage servicePage = new ServicePage(driver);
    private LoginPage loginPage;
    private Waits wait;
    private Assertions assertions;

    public LoginStatus(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean logIn(String email, String password) throws InterruptedException {
        loginPage = new LoginPage(driver);
        wait = new Waits(driver);
        assertions = new Assertions(driver);

        loginPage.emailSet(email);
        loginPage.checkRememberMe();
        loginPage.debugBtnTap();
        loginPage.enableDebugModeClick();
        loginPage.environmentsDropDownClick();
        loginPage.MBQA3Selection();
        loginPage.continueBtnClick();
        wait.passwordScreenWait();
        loginPage.passwordSet(password);
        loginPage.loginBtnClick();
        Thread.sleep(3000); // Explicit wait because not sure about next step
        loginPage.forcedLoginYes();
        try {
            wait.serviceScreenWait();
            assertions.loginAssertion();
            System.out.println("LogIn method is called and passed...");
        }
        catch (Exception e)
        {
            System.out.println("Unable to login and quiting the tests");
            driver.close();
            driver.quit(); // driver is not quiting
        }
        return IsSession_Logged_In();
    }

    public boolean IsSession_Logged_In()
    {
        try {
            servicePage.WOMenu.isDisplayed();
        }
        catch (NoSuchElementException e){
            return false;
        }
        return true;

    }

    public void loginCheck() throws InterruptedException {
        if (IsSession_Logged_In() == false)
        {
            System.out.println("User needs to login");
            logIn(email,password);
        }
        else {
            System.out.println("User is already loggedIn");
        }
    }

}
