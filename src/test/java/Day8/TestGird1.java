package Day8;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class TestGird1 {


    public  AppiumDriver <WebElement> driver;



    @Parameters({"udid"})
    @Test(priority = 1)
    public void setUp(String udid) throws MalformedURLException {


        long id = Thread.currentThread().getId();
        System.out.println("Sample test-method One. Thread id is: " + id);

        DesiredCapabilities cas = new DesiredCapabilities();

        cas.setCapability("platformName", "Android");
        cas.setCapability("platformVersion", "4.4.4");
        cas.setCapability("deviceName", "xiaoyaoyou");

        cas.setCapability("udid", udid);
        cas.setCapability("appPackage", "com.android.browser");
        cas.setCapability("appActivity", ".BrowserActivity");

        cas.setCapability("unicodeKeyboard", true);
        cas.setCapability("resetKeyboard", true);


        URL u = new URL("http://192.168.1.101:4444/wd/hub/");

        System.out.println(cas.toString());

        driver = new AppiumDriver<WebElement>(u, cas);

    }



    @Test(priority = 2)
    public void action(){

        driver.get("https://www.baidu.com");


    }

    @Test(priority = 3)
    public  void quit(){

        driver.quit();

    }




    @BeforeSuite
    public void beforeSuite() {
        System.out.println("我们开始并发执行Appium");
    }




    @AfterSuite
    public void afterSuite() {




        System.out.println("并发执行结束");
    }

















}
