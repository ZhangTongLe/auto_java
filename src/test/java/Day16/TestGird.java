package Day16;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;

public class TestGird {


    public  AppiumDriver <WebElement> driver;

    public  AppiumDriverLocalService service;



    @BeforeSuite
    public void beforeSuite() {
        System.out.println("我们开始并发执行Appium");
    }

    @Test(priority = 1)
    @Parameters({"udid"})
    public void setServ(String udid){

            long id = Thread.currentThread().getId();
            System.out.println("Sample test-method One. Thread id is: " + id);

             int   port = 4723 + (int) (Math.random() * 100);

//        int port  = ThreadLocalRandom.current().nextInt(4723,5000);

            File location = new File("log");
            String logName = location.getAbsolutePath() + File.separator + udid + ".log";
            service = AppiumDriverLocalService.buildService(new
                    AppiumServiceBuilder()
                    .withIPAddress("127.0.0.1")
                    .usingPort(port)
                    .withArgument(GeneralServerFlag.ROBOT_ADDRESS, udid)
                    .withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER,
                            String.valueOf(port + 2))
                    .withArgument(SESSION_OVERRIDE)
                    .withLogFile(new File(logName)));

            service.start();

    }


    @Parameters({"udid"})
    @Test(priority = 2)
    public void setUp(String udid) throws MalformedURLException {

        long id = Thread.currentThread().getId();
        System.out.println("Sample test-method One. Thread id is: " + id);
        DesiredCapabilities cas = new DesiredCapabilities();
        cas.setCapability("platformName", "Android");
        cas.setCapability("deviceName", "Android");
        cas.setCapability("udid", udid);
        File apkPath = new File(System.getProperty("user.dir") + "\\apk\\zhihu.apk");
        cas.setCapability("app", apkPath.getAbsolutePath());
        // 被测app的包名
        cas.setCapability("appPackage", "com.zhihu.android");
        // 被测app的启动activity
        cas.setCapability("appActivity", ".app.ui.activity.MainActivity");
        cas.setCapability("unicodeKeyboard", true);
        cas.setCapability("resetKeyboard", true);


        driver = new AppiumDriver<WebElement>(service.getUrl(), cas);

    }





    //  登陆知乎。

    @Test(priority = 3)

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





    @Test(priority = 4)
    public  void quit(){

        driver.quit();
        service.stop();



    }




    @AfterSuite
    public void afterSuite() {

        System.out.println("并发执行结束");
    }

















}
