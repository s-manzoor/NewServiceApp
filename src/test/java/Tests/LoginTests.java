package Tests;

import Common.BaseClass;
import Common.TestListeners;
import Common.PropertyManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.annotations.*;
import pageObjects.LoginPage;

@Listeners(TestListeners.class)  // Listeners executed before everything

public class LoginTests extends BaseClass {
    private LoginPage loginPage;
    private PropertyManager PM;
    String email = PM.getInstance().getEmail();
    String password = PM.getInstance().getPassword();
    String invalieEmail = PM.getInstance().getInvalidEmail();
 //   public ExtentTest test;

    /*@Test(description = "Login with valid user credentials")
    public void validLogin() throws Exception {
    test = extent.createTest("Valid Login", "This is extent Report results for valid Login Test case");
    test.log(Status.INFO, "ValidLogin Test has started....");
    loginPage = new LoginPage(driver);
    loginPage.emailSet(email);
    test.pass("Input G2 email address" + email);
    loginPage.debugBtnTap();
    test.pass("Tap on Settings icon");
    loginPage.enableDebugModeClick();
    test.pass("Click to enable debug option");
    loginPage.environmentsDropDownClick();
    test.pass("Click to debug mode options dropdown");
    loginPage.MBQA3Selection();
    test.pass("Select MBQA3 debug mode option");
    loginPage.checkRememberMe();
    test.pass("Check Remember Me checkbox");
    loginPage.continueBtnClick();
    test.pass("Click on Continue button");
    loginPage.passwordScreenWait();
    loginPage.passwordSet(password);
    test.pass("Input user password" + password);
    loginPage.loginBtnClick();
    test.pass("Click on Login button");
//    loginPage.popupWait();
  //  Thread.sleep(5000);
    loginPage.forcedLoginYes();
    loginPage.serviceScreenWait();
    loginPage.loginAssertion();
    test.pass("Service screen is displayed");
    test.log(Status.INFO, "ValidLogin test cases is completed....");
}*/

 @Test(description = "User is able to login with valid login credentials")
 public void validLogin() {
  ExtentTest test = extent.createTest("Valid Login", "This is extent Report results for valid Login Test case");
  test.log(Status.INFO, "valid login test cases is starting");
  loginPage = new LoginPage(driver);

  loginPage.emailSet(email);
  test.pass("Entet user G2 email: "+ email);
  loginPage.debugBtnTap();
  test.pass("Tap on Settings icon");
  loginPage.enableDebugModeClick();
  test.pass("Enable debug mode");
  loginPage.environmentsDropDownClick();
  test.pass("Tap on environment options drop down");
  loginPage.MBQA3Selection();
  test.pass("Select MBQA3 dropdown option");
  loginPage.checkRememberMe();
  test.pass("Check RememberMe checkbox");
  loginPage.continueBtnClick();
  test.pass("Tap on Continue button");
  loginPage.passwordScreenWait();

  loginPage.passwordSet(password);
  test.pass("Enter password: "+password);
  loginPage.loginBtnClick();
  test.pass("Click on Login button");
  loginPage.popupWait();
  loginPage.forcedLoginNo();
  loginPage.popupWait();
  loginPage.forcedLoginYes();
  loginPage.serviceScreenWait();
  loginPage.loginAssertion();
  test.pass("Login assertion is passed");
  test.log(Status.INFO, "valid login test cases is completed");
 }

 @Test(description = "Invalid login without selecting Debug mode")
 public void invalidLogin01() {
 // ExtentTest test1 = extent.createTest("inValid Login01", "This is extent Report results for invalid Login without selecting Debbug mode Test case");
 // test1.log(Status.INFO, "invalid login without selecting debug mode test case is starting");

  loginPage = new LoginPage(driver);
  loginPage.emailSet(email);
 // test1.pass("Input your G2 email address: " +email);
  loginPage.continueBtnClick();
 // test1.pass("Click on Continue button");
  loginPage.loginPopup();
//  test1.pass("Tap on Ok button to close the popup message");
//  test1.log(Status.INFO,"Invalid Login without selecting Debug mode is completed");
 }

 @Test(description = "invalid login with wrong email")
 public void invalidLogin02() {
  ExtentTest test2 = extent.createTest("invalidLogin02", "This is extent report results for invalid login with wrong email");
  test2.log(Status.INFO, "invalid login with wrong email is started");
  loginPage = new LoginPage(driver);
  loginPage.emailSet(invalieEmail);
  test2.pass("Login with invalid email: " + invalieEmail );
  loginPage.continueBtnClick();
  test2.pass("Tap on continue button");
  loginPage.loginPopup();
  test2.pass("Tap on OK button to close the popup");
  test2.log(Status.INFO, "invalid login with wrong email test is completed");
 }
 @Test(description = "invalid login with wrong password")
 public void invalidLogin03() throws InterruptedException {
  ExtentTest test3 = extent.createTest("invalidLogin03", "This is extent report results for invalid login with wrong password");
  loginPage = new LoginPage(driver);
  loginPage.emailSet(email);
  test3.pass("Entet user G2 email: "+ email);
  loginPage.debugBtnTap();
  test3.pass("Tap on Settings icon");
  loginPage.enableDebugModeClick();
  test3.pass("Enable debug mode");
  loginPage.environmentsDropDownClick();
  test3.pass("Tap on environment options drop down");
  loginPage.MBQA3Selection();
  test3.pass("Select MBQA3 dropdown option");
  loginPage.checkRememberMe();
  test3.pass("Check RememberMe checkbox");
  loginPage.continueBtnClick();
  test3.pass("Tap on Continue button");
  loginPage.passwordScreenWait();
  loginPage.passwordSet("test1234");
  test3.pass("Enter invalid password: ");
  loginPage.loginBtnClick();
  test3.pass("Click on Login button");
  Thread.sleep(5000);
  loginPage.loginPopup();
  test3.pass("Tap on Ok button to close the popup message");
  // TODO: not working due to OS 7 splash activity issue
  // loginpage.backButton()
 }

 @Test(description = "Logout from IDS Service app")
 public void logOut() throws Exception {
  ExtentTest test4 = extent.createTest("Logout", "This is extent report results for logout test case");
  loginPage = new LoginPage(driver);

 // loginPage.logIn(email,password);
  if (loginPage.IsSession_Logged_In() == false)
  {
   System.out.println("User needs to login");
   loginPage.logIn(email,password);
  }
  else {
   System.out.println("User is already loggedIn");
  }

  loginPage.logOut();
  test4.pass("Tap on Logout link and Confirm the option with Yes");
  loginPage.logOutAssertion();
  test4.pass("Logout Assertion: Continue button is displayed on Login screen");
 }

 @Test(description = "User is able to re-login", dependsOnMethods = {"logOut"})
 public void reLogin() {
  loginPage = new LoginPage(driver);

  loginPage.continueBtnClick();
  loginPage.loginBtnClick();
  loginPage.serviceScreenWait();
  loginPage.loginAssertion();
 }

}
