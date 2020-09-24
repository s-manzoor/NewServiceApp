package pageObjects;


import Common.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class LoginPage extends BaseClass {

    private ServicePage servicePage = new ServicePage(driver);
    ExtentTest test1 = extent.createTest("Login Tests", "This is extent Report results");

    public LoginPage(AppiumDriver driver) {
//        public LoginPage(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(accessibility = "login-email-address")
    MobileElement emailBox;

    @AndroidFindBy(accessibility = "login-continue-btn")
    MobileElement continueBtn;

    @AndroidFindBy(accessibility = "login-checkbox-remember-me")
    MobileElement rememberMe;

    @AndroidFindBy(accessibility = "login-btn")
    MobileElement loginButton;

    @AndroidFindBy(accessibility = "login-options")
    MobileElement debugOptions;

    @AndroidFindBy(accessibility = "login-debug-btn-enable")
    MobileElement enableDebugMode;

    @AndroidFindBy(accessibility = "login-environment-selector")
    MobileElement environmentsList;

    @AndroidFindBy(accessibility = "login-environment-selector-items")
    MobileElement QA3;

    @AndroidFindBy(accessibility = "login-password")
    MobileElement userPassword;

    @AndroidFindBy(accessibility = "login-back")
    MobileElement backBtn;

    @AndroidFindBy(accessibility = "popup-btn-0")
    MobileElement loginErrorPopupOK; // forcedLoginYes

    @AndroidFindBy(accessibility = "popup-btn-1")
    MobileElement forcedLoginNo;

    @AndroidFindBy(accessibility = "menu-item-logout")
    MobileElement logOutMenu;

    @AndroidFindBy(accessibility = "popup-btn-0")
    MobileElement logOutYes;

    @AndroidFindBy(accessibility = "popup-btn-1")
    MobileElement logOutNo; // not used


    public void emailSet(String email) {
        emailBox.clear();
        test1.pass("Enter your G2 email: "+ email);
        emailBox.sendKeys(email);
    }

    public void passwordSet(String pwd) {
        userPassword.clear();
        userPassword.sendKeys(pwd);
    }

    public void continueBtnClick() {
        test1.pass("Click on Continue button");
        continueBtn.click();
    }

    public void checkRememberMe() {
        rememberMe.click();
    }

    public void debugBtnTap() {
        debugOptions.click();
    }

    public void enableDebugModeClick() {
        enableDebugMode.click();
    }

    public void environmentsDropDownClick() {
        environmentsList.click();
    }

    public void MBQA3Selection() {
        List<MobileElement> environments = QA3.findElementsByClassName("android.widget.TextView");
        environments.get(1).click();
    }

    public void loginBtnClick() {
        loginButton.click();
    }

    public void backButton() {
        backBtn.click();
    }

    public void loginPopup() {
        test1.pass("Click on OK");
        loginErrorPopupOK.click();
    }

    public void forcedLoginNo() {
        try {
            if (forcedLoginNo.isDisplayed()) {
                forcedLoginNo.click();
                loginButton.click();
            }
        }
        catch (Exception e) {
            System.out.println("Forced login option NO is not displayed...");
        }
    }

    public void forcedLoginYes() {
        try {
            if (loginErrorPopupOK.isDisplayed()) {
                loginErrorPopupOK.click();
                loginButton.click();
            }
        }
        catch (Exception e) {
            System.out.println("Forced login option YES is not displayed...");
        }
    }

    public void passwordError(){
        loginErrorPopupOK.click();
    }

    public void logOut(){
        logOutMenu.click();
        // TODO: not working due to OS 7 splash activity issue
       /* logOutNo.click()
        Thread.sleep(3000)
        logOutMenu.click()*/
        logOutYes.click();
    }

    public boolean logIn(String email, String password) throws InterruptedException {
        emailSet(email);
        checkRememberMe();
        debugBtnTap();
        enableDebugModeClick();
        environmentsDropDownClick();
        MBQA3Selection();
        continueBtnClick();
//        Thread.sleep(3000);
        passwordScreenWait();
        passwordSet(password);
        loginBtnClick();
        Thread.sleep(5000);
        loginPopup();
        Thread.sleep(5000);
        try {
           // loginAssertion();
            System.out.println("LogIn method is called and passed.");
        }
        catch (Exception e)
        {
            System.out.println("Unable to login and quiting the tests");
            driver.close();
        }
        return IsSession_Logged_In();
    }

    public boolean IsSession_Logged_In()
    {
       // return (servicePage.IsObjExist(fetch_elements.GetObj("xpath", "//*[starts-with(@class, 'Header_title')]")));
    try {
        servicePage.WOMenu.isDisplayed();
    }
    catch (NoSuchElementException e){
        return false;
    }
    return true;

       /*     ((servicePage.WOMenu).isDisplayed())
        return (servicePage.WOMenu).isDisplayed();*/
    }


    public void passwordScreenWait(){
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOf(userPassword));
    }

    public void popupWait(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(loginErrorPopupOK));
    }

    public void serviceScreenWait(){
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOf(servicePage.WOMenu));
    }

    public void loginAssertion(){
        Assert.assertTrue(servicePage.WOMenu.isDisplayed());
        // System.out.println("Login Assertion is passed...");
    }

    public void logOutAssertion(){
        Assert.assertTrue(continueBtn.isDisplayed());
    }
}
