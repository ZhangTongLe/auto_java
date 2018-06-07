package Day1;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HelloWorld {

    public static AndroidDriver<AndroidElement> driver;

    public static void  setup() throws MalformedURLException {

        /*
         * DesiredCapabilities功能详解
         * http://appium.io/docs/en/writing-running-appium/caps/index.html
         */

//     负责启动服务端时的参数设置,本质上是key value的对象,用来告诉Appium服务器，被测试的安卓系统的环境，app叫什么名等等。
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android"); //被测移动端系统什么名
        caps.setCapability("platformVersion", "4.4.4");//系统版本

        caps.setCapability("deviceName", "127.0.0.1:21503");    //用adb devices 获取到的。

// 设置输入法是为了可以输入中文，启用Unicode输入，默认 false。
        caps.setCapability("unicodeKeyboard", "True");
        caps.setCapability("resetKeyboard", "True");

// 被测app的路径，用于安装app
        File apkPath = new File(System.getProperty("user.dir") + "\\apk\\zhihu.apk");
        caps.setCapability("app", apkPath.getAbsolutePath());



//正常app的包名、和启动的activity用aapt命令进行获取：aapt dump badging  zhihu.apk
// 被测app的包名
        caps.setCapability("appPackage", "com.zhihu.android");   //


// 被测app的启动activity
        caps.setCapability("appActivity", ".app.ui.activity.MainActivity");//被测app的入口activity




// 如果把这项注释掉就是默认状态，默认状态是测试后停止并清除应用程序数据，不要卸载apk

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


    public static void main(String[] args) throws MalformedURLException, InterruptedException, IOException {

        setup();
        driver.quit();


    }





}
