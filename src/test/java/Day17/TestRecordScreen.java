package Day17;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.screenrecording.BaseStartScreenRecordingOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestRecordScreen {

    public static AndroidDriver<AndroidElement> driver;

    public static BaseStartScreenRecordingOptions so ;


    public static void  setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android"); //被测移动端系统什么名
        caps.setCapability("platformVersion", "5.1.1");//系统版本

        caps.setCapability("deviceName", "x7plus");    //用adb devices 获取到的。
        caps.setCapability("unicodeKeyboard", "True");
        caps.setCapability("resetKeyboard", "True");

        caps.setCapability("appPackage", "com.ss.android.ugc.live");   //

        caps.setCapability("appActivity", ".main.MainActivity");//被测app的入口activity

      //不要停止应用程序，不要清除应用程序数据，不要卸载apk。

        // caps.setCapability("noReset", true);

        //与Appium Server 建立通信的链接！
        URL u = new URL("http://127.0.0.1:4723/wd/hub/");

// 实例化一个Android driver
        driver = new AndroidDriver<AndroidElement>(u, caps);

// 它是 webdirver 提供的一个超时等待。
// 隐式等待一个元素被发现，或一个命令完成。如果超出了设置时间仍未定位到元素则抛出异常。
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }



    public  static void  luzhi() throws InterruptedException, IOException {

        Thread.sleep(12000);

        TouchAction touchAction = new TouchAction(driver);

        touchAction.tap(269 , 604).perform().release();

        String s = " adb shell screenrecord /sdcard/test.mp4";

        Runtime.getRuntime().exec(s);



    }


    public static void main(String[] args) throws IOException, InterruptedException {
        setup();
        luzhi();

    }




}
