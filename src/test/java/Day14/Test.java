package Day14;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Test {

    public static AndroidDriver<AndroidElement> driver;

    public static void  setup() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");

        caps.setCapability("deviceName", "x7");

        caps.setCapability("unicodeKeyboard", "True");
        caps.setCapability("resetKeyboard", "True");

        File apkPath = new File(System.getProperty("user.dir") + "\\apk\\zhihu.apk");
        caps.setCapability("app", apkPath.getAbsolutePath());

        caps.setCapability("appPackage", "com.zhihu.android");   //


        caps.setCapability("appActivity", ".app.ui.activity.MainActivity");//被测app的入口activity

        URL u = new URL("http://127.0.0.1:4723/wd/hub/");
        driver = new AndroidDriver<AndroidElement>(u, caps);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }


    public static void main(String[] args) throws MalformedURLException {

        setup();
        driver.quit();


    }





}