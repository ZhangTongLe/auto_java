package Day12;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class TestWX {


    public static AndroidDriver<WebElement> driver;




    public static void  setup() throws MalformedURLException {


        DesiredCapabilities caps = new DesiredCapabilities();


        caps.setCapability("platformName", "Android"); //被测移动端系统什么名
        caps.setCapability("platformVersion", "5.1.1");//系统版本

// 表示我们的设备名字，在安卓下这个参数必须有，但是值可以随便写
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "vivo");// “devicesName”


// 下面两项是用来使用appium自带的unicode输入法，来隐藏键盘并且支持中文输入
        caps.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, true);
        caps.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, true);

//        File chromedriver=new File("D:\\TestJar\\AutoTest\\auto\\apk\\chromedriver.exe");


        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.tencent.mm");
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.tencent.mm.ui.LauncherUI");
        caps.setCapability(MobileCapabilityType.NO_RESET, true);

//        caps.setCapability(AndroidMobileCapabilityType.RECREATE_CHROME_DRIVER_SESSIONS, true);
//        caps.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, chromedriver.getAbsolutePath());

//
//        ChromeOptions options=new ChromeOptions();
//        options.setExperimentalOption("androidProcess", "com.tencent.mm:appbrand0");
//        caps.setCapability(ChromeOptions.CAPABILITY, options);


        driver = new AndroidDriver<WebElement>( new URL("http://127.0.0.1:4723/wd/hub"), caps);


        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);





    }


    public  static void talk(){

        driver.findElementByXPath("//android.widget.ListView[@resource-id='com.tencent.mm:id/c5x']/android.widget.LinearLayout[1]").click();


//      AndroidElement  atk =   driver.findElementByXPath("//android.widget.EditText[@resource-id='com.tencent.mm:id/aac']");

//      atk.sendKeys("123");



      driver.findElementByXPath("//android.widget.Button[@resource-id='com.tencent.mm:id/aai']").click();



    }






    //普通微信小程序
    public  static  void  xch(){

        driver.findElementByAccessibilityId("搜索").click();



//        AndroidElement  search = driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"搜索\")");


//        search.sendKeys("拜年");
//
//
////        AndroidElement  bn = driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"拜年祝福小助手\")");
//
//        bn.click();



        driver.findElementByAccessibilityId("点击编辑您的祝福！").click();



//        AndroidElement  xm  =driver.findElementByXPath("//android.view.View[@content-desc='请输入对方称呼']");

//        xm.sendKeys("hello");



    }


    /**
     * 向上滑（基于坐标x:width / 、y:height * 4  滑到x:width / 2, y:height / 4）
     * @param
     */

    public static void swipeUp() {

        // 获取屏幕分辨率,已屏幕分辨率为比例进行按位置进行滑动
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction action1 = new TouchAction(driver).press(width / 2, height * 4 / 5).waitAction(Duration.ofSeconds(2)).moveTo(width / 2, height / 4).release();
        action1.perform();
    }



    public static void xchWeb() throws InterruptedException {


        //京东购物小程序


//        driver.get("https://www.baidu.com");
//
//
//        Activity activity = new Activity("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
//
//
//        driver.startActivity(activity);
//



//        Thread.sleep(Long.parseLong("8000"));





        driver.findElementByXPath("//*[@text='发现']").click();
        swipeUp();

        driver.findElementByXPath("//*[@text='小程序']").click();


        driver.findElementByXPath("//*[@text='京东购物']").click();




        driver.context("WEBVIEW_com.tencent.mm:appbrand0");

        System.out.println("切换成功");

//        System.out.println(driver.getPageSource());

//        Set<String> contexts = driver.getContextHandles();
//        for (String context : contexts) {
//            System.out.println(context);
//        }



//        driver.findElementByXPath("/html/body/wx-view/wx-view/wx-grid-selected/wx-view/wx-view/wx-view[1]/wx-view[3]");







    }


public static void  wx() throws MalformedURLException, InterruptedException {

    DesiredCapabilities capability = new DesiredCapabilities();

    capability.setCapability("app", "");
//    capability.setCapability("browserName","Chrome");
    capability.setCapability("appPackage", "com.tencent.mm");
    capability.setCapability("appActivity", ".ui.LauncherUI");
    capability.setCapability("deviceName", "5d414bee");
    capability.setCapability("fastReset", "false");
    capability.setCapability("fullReset", "false");
    capability.setCapability("noReset", "true");
    capability.setCapability("unicodeKeyboard", "True");
    capability.setCapability("resetKeyboard", "True");
    //关键是加上这段
    ChromeOptions options2 = new ChromeOptions();
    options2.setExperimentalOption("androidProcess", "com.tencent.mm:tools");
    capability.setCapability(ChromeOptions.CAPABILITY, options2);

    //启动微信浏览器

    System.out.println("启动微信");

    driver = new AndroidDriver( new URL("http://127.0.0.1:4723/wd/hub"), capability);

    driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    sleep(50);


    WebElement webElement=driver.findElement(By.xpath("//*[@content-desc='搜索']"));
    webElement.click();






}






    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        wx();
//        talk();

//        xch();

//        xchWeb();

        driver.quit();


    }




}
