package pageObjects;


import Common.BaseClass;
import Common.PropertyManager;
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

    PropertyManager PM;
    String email = PM.getInstance().getEmail();
    String password = PM.getInstance().getPassword();

    private ServicePage servicePage = new ServicePage(driver);
    ExtentTest test3;

    public LoginPage(AppiumDriver driver) {
//        public LoginPage(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(accessibility = "login-email-address")
    MobileElement emailBox;

    @AndroidFindBy(accessibility = "login-continue-btn")
    public MobileElement continueBtn;

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
    public MobileElement userPassword;

    @AndroidFindBy(accessibility = "login-back")
    MobileElement backBtn;

    @AndroidFindBy(accessibility = "popup-btn-0")
    public MobileElement loginErrorPopupOK; // forcedLoginYes

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
      //  test3.log(Status.INFO, "Enter your G2 email: "+ email);
        emailBox.sendKeys(email);
    }

    public void passwordSet(String pwd) {
        userPassword.clear();
        userPassword.sendKeys(pwd);
    }

    public void continueBtnClick() {
      //  test1.log(Status.INFO, "Click on Continue button");
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
       // test1.pass("Click on OK");
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
            }
        }
        catch (Exception e) {
            System.out.println("Forced login option YES is not displayed...");
        }
    }

    public void passwordError(){
        loginErrorPopupOK.click();
    }

    public void logOutMenuClick(){
        logOutMenu.click();
    }
    public void logOutNo(){
        logOutNo.click();
    }
    public void logOutYes(){
        logOutYes.click();
    }

}
