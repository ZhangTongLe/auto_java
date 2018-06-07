package Day13;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * Created by yunusm on 08.11.2016.
 */
public class AppiumTest {

    private AppiumDriver<WebElement> driver;

    public void setUp(String udid) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");

        capabilities.setCapability("udid", udid);
//        File apkPath = new File("D:\\TestJar\\AutoTest\\auto\\apk\\zhihu.apk");
//        capabilities.setCapability("app", apkPath.getAbsolutePath());


        capabilities.setCapability("appPackage", "com.zhihu.android");
        // 被测app的启动activity
        capabilities.setCapability("appActivity", ".app.ui.activity.MainActivity");


         capabilities.setCapability("noReset", true);

        driver = new AndroidDriver<>(new URL("http://192.168.1.111:4723/wd/hub"), capabilities);
    }

    public void tearDown() {
        driver.quit();
    }



    public void startTest(String udid) {
        try {
            setUp(udid);
            tearDown();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}