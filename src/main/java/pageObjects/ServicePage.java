package pageObjects;

import Common.BaseClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ServicePage extends BaseClass {

    /*WebDriverWait wait = new WebDriverWait(driver,10);

    public void serviceScreenWait(){
        wait.until(ExpectedConditions.visibilityOf(WOMenu));
    }

    public void setWait(WebDriverWait wait) {
        this.wait = wait;
    }

    public WebDriverWait getWait() {
        return wait;
    }*/

    public ServicePage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(accessibility = "menu-item-workorder")
    public MobileElement WOMenu;

}
