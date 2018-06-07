package Day5;

import io.appium.java_client.NoSuchContextException;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Hybird {


    //因为是混合应用，我们要测试应用内的网页元素。所以声明一个包裹WebElement的driver
    public static AndroidDriver<WebElement> driver;


    public static void  setup() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android"); //被测移动端系统什么名
        caps.setCapability("platformVersion", "4.4.4");//系统版本

        caps.setCapability("deviceName", "127.0.0.1:21503");    //用adb devices 获取到的。

        caps.setCapability("unicodeKeyboard", "True");
        caps.setCapability("resetKeyboard", "True");


        //  可以使用此命令来获取，当前系统启动的应用的包名和activity
        //  adb shell dumpsys window | findstr mCurrentFocus
        caps.setCapability("appPackage", "com.android.browser");   //
        caps.setCapability("appActivity", ".BrowserActivity");//被测app的入口activity

        URL u = new URL("http://127.0.0.1:4723/wd/hub/");

        driver = new AndroidDriver<WebElement>(u, caps);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


    }


    public static void openURL(){
        driver.get("https://m.baidu.com");

    }



    /**跳转到webview页面 **/
    public static void switchWebview(String contextName) {
        try {
            Set<String> contexts = driver.getContextHandles();
            for (String context : contexts) {
                System.out.println(context);
                //打印出来看看有哪些context
            }
            driver.context(contextName);
        } catch (NoSuchContextException nce) {
            System.out.println("没有找到这个webview 请重新写 webview的名字");
        }

    }




    public static void search(){


        //如果不切换到webview的视图功能，下面的所有代码都执行不了！
        switchWebview("WEBVIEW_com.android.browser");

        //这回可以执行了，定位百度搜索框的位置
        driver.findElementByXPath("//*[@id=\"index-kw\"]").sendKeys("appium");


       //然后点击百度一下你就知道搜索按钮

        driver.findElementByXPath("//*[@id=\"index-bn\"]").click();


    }


    public static void main(String[] args) throws MalformedURLException {

        setup();

        openURL();

        search();

        driver.quit();

    }


}
