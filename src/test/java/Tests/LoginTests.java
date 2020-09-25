package Tests;

import Common.*;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.annotations.*;
import pageObjects.LoginPage;

@Listeners(TestListeners.class)  // Listeners executed before everything

public class LoginTests extends BaseClass {
 private LoginPage loginPage;
 private PropertyManager PM;
 private LoginStatus loginChecking;
 private Assertions assertion;
 private Waits wait;

 String email = PM.getInstance().getEmail();
 String password = PM.getInstance().getPassword();
 String invalieEmail = PM.getInstance().getInvalidEmail();

 @Test(description = "User is able to login with valid login credentials")
 public void validLogin() throws InterruptedException {
  loginPage = new LoginPage(driver);
  wait = new Waits(driver);
  assertion = new Assertions(driver);

  loginPage.emailSet(email);
  loginPage.debugBtnTap();
  loginPage.enableDebugModeClick();
  loginPage.environmentsDropDownClick();
  loginPage.MBQA3Selection();
  loginPage.checkRememberMe();
  loginPage.continueBtnClick();
  wait.passwordScreenWait();
  loginPage.passwordSet(password);
  loginPage.loginBtnClick();
  Thread.sleep(3000);
  loginPage.forcedLoginNo();
  Thread.sleep(3000);
  loginPage.forcedLoginYes();
  wait.serviceScreenWait();
  assertion.loginAssertion();
 }

 @Test(description = "Invalid login without selecting Debug mode")
 public void invalidLogin01() {
  loginPage = new LoginPage(driver);
  loginPage.emailSet(email);
  loginPage.continueBtnClick();
  loginPage.loginPopup();
 }

 @Test(description = "invalid login with wrong email")
 public void invalidLogin02() {
  loginPage = new LoginPage(driver);
  loginPage.emailSet(invalieEmail);
  loginPage.continueBtnClick();
  loginPage.loginPopup();
 }
 @Test(description = "invalid login with wrong password")
 public void invalidLogin03() throws InterruptedException {
  loginPage = new LoginPage(driver);
  loginPage.emailSet(email);
  loginPage.debugBtnTap();
  loginPage.enableDebugModeClick();
  loginPage.environmentsDropDownClick();
  loginPage.MBQA3Selection();
   loginPage.checkRememberMe();
  loginPage.continueBtnClick();
 // loginPage.passwordScreenWait();
  loginPage.passwordSet("test1234");
  loginPage.loginBtnClick();
  Thread.sleep(3000);
  loginPage.loginPopup();
   // loginpage.backButton()
 }

 @Test(description = "Logout from IDS Service app")
 public void logOut() throws Exception {
  loginPage = new LoginPage(driver);
  loginChecking = new LoginStatus(driver);
  assertion = new Assertions(driver);

  loginChecking.loginCheck();
//  loginPage.logOut();
  loginPage.logOutMenuClick();
  loginPage.logOutNo();
  loginPage.logOutMenuClick();
  loginPage.logOutYes();
 // wait.
  assertion.logOutAssertion();
 }

 @Test(description = "User is able to re-login", dependsOnMethods = {"logOut"})
 public void reLogin() {
  loginPage = new LoginPage(driver);
  wait = new Waits(driver);
  assertion = new Assertions(driver);

  loginPage.continueBtnClick();
  wait.passwordScreenWait();
  loginPage.loginBtnClick();
  wait.serviceScreenWait();
  assertion.loginAssertion();
 }

}
