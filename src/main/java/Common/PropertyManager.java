package Common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    private static PropertyManager instance;
    private static final Object lock = new Object();
    private static String baseURL;
    private static String userEmail;
    private static String userPassword;
    private static String automationName;
    private static String invalidEmail;
    private static String platform;
    private static String device;
    private static String deviceVersion;
    private static String appActivity;
    private static String app;

    //Create a Singleton instance. We need only one instance of Property Manager.
    public static PropertyManager getInstance () {

        if (instance == null) {
            synchronized (lock) {
                instance = new PropertyManager();
                instance.loadData();
            }
        }
        return instance;
    }

    //Get all configuration data and assign to related fields
    public void loadData(){
        Properties property = new Properties();
        try {
            property.load(new FileInputStream("D:\\Automation\\IDSServiceAutomationPractice\\src\\main\\resources\\configuration.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Configuration properties file cannot be found");
        }
        //Get properties from configuration.properties
        baseURL = property.getProperty("URL");
        userEmail = property.getProperty("Email");
        userPassword = property.getProperty("Password");
        automationName = property.getProperty("AutomationName");
        invalidEmail = property.getProperty("invalidEmail");
        platform = property.getProperty("PlatformName");
        device = property.getProperty("DeviceName");
        deviceVersion = property.getProperty("DeviceVersion");
        appActivity = property.getProperty("AppActivity");
        app = property.getProperty("TestApp");


    }

    public String getURL(){
        return baseURL;
    }

    public String getEmail(){
        return userEmail;
    }

    public String getPassword(){
        return userPassword;
    }

    public String getAutomationName(){
        return automationName;
    }

    public String getInvalidEmail(){
        return invalidEmail;
    }


    public String getPlatformName(){
        return platform;
    }

    public String getDeviceName(){
        return device;
    }

    public String getDeviceOSVersion(){
        return deviceVersion;
    }

    public String getAppActivity(){
        return appActivity;
    }

    public String getTestApp(){
        return app;
    }

}
