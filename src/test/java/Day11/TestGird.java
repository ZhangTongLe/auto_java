package Day11;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class TestGird {


    public  AppiumDriver <WebElement> driver;



    @Parameters({"udid"})
    @Test(priority = 1)
    public void setUp(String udid) throws MalformedURLException {

        long id = Thread.currentThread().getId();
        System.out.println("Sample test-method One. Thread id is: " + id);
        DesiredCapabilities cas = new DesiredCapabilities();

        cas.setCapability("platformName", "Android");
        cas.setCapability("platformVersion", "4.4.4");

        cas.setCapability("deviceName", "安卓逍遥游");

        cas.setCapability("udid", udid);

        File apkPath = new File(System.getProperty("user.dir") + "\\apk\\zhihu.apk");
        cas.setCapability("app", apkPath.getAbsolutePath());


        // 被测app的包名
        cas.setCapability("appPackage", "com.zhihu.android");


        // 被测app的启动activity
        cas.setCapability("appActivity", ".app.ui.activity.MainActivity");


        cas.setCapability("unicodeKeyboard", true);
        cas.setCapability("resetKeyboard", true);


        //老的链接
        //URL u = new URL("http://192.168.1.102:4723/wd/hub/");

        URL u = new URL("http://192.168.1.102:4444/wd/hub/");

        System.out.println(cas.toString());

        driver = new AppiumDriver<WebElement>(u, cas);

    }





    //  登陆知乎。

    @Test(priority = 2)

    public  void loginZhiHu() {

        //step1:点击登录按钮

        driver.findElementById("com.zhihu.android:id/login_btn").click();

        //step2:输入用户名
        driver.findElementByXPath(" //android.widget.EditText[@resource-id='com.zhihu.android:id/username']")
                .sendKeys("15811006613");

        //step3:输入密码
        driver.findElementByXPath(" //android.widget.EditText[@resource-id='com.zhihu.android:id/password']")
                .sendKeys("zhangtongle");


        // step4:点击确认登录
        driver.findElementByXPath("//android.widget.Button[@resource-id='com.zhihu.android:id/btn_progress']").click();


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
