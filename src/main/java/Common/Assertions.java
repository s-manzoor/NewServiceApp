package Common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pageObjects.LoginPage;
import pageObjects.ServicePage;

public class Assertions extends BaseClass {

    private ServicePage servicePage = new ServicePage(driver);
    private LoginPage loginPage = new LoginPage(driver);

    public Assertions(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void loginAssertion(){
        Assert.assertTrue(servicePage.WOMenu.isDisplayed());
        
    }

    public void logOutAssertion(){

        Assert.assertTrue(loginPage.continueBtn.isDisplayed());
    }

}
