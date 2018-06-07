package Day6;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Listeners({ScreenshotListener.class})
public class Screenshot {
    public   static AndroidDriver<AndroidElement> driver;


    public static AndroidDriver getDriver(){
        return driver;
    }


    @BeforeClass
    public void setUp() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "4.4.4");

        caps.setCapability("deviceName", "127.0.0.1:21503");

        caps.setCapability("unicodeKeyboard", "True");
        caps.setCapability("resetKeyboard", "True");


//        adb shell dumpsys window | findstr mCurrentFocus
        caps.setCapability("appPackage", "com.ibox.calculators");
        caps.setCapability("appActivity", ".CalculatorActivity");



        URL u = new URL("http://127.0.0.1:4723/wd/hub/");

        driver = new AndroidDriver<AndroidElement>(u, caps);
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);




    }
    @Test
    public void testExample() throws IOException{


        //通过resource-id定位方式，对计算器的数字2 进行点击
        driver.findElementById("com.ibox.calculators:id/digit2").click();


        //对加号进行点击

        driver.findElementById("com.ibox.calculators:id/plus").click();

        //对数字4进行点击

        driver.findElementById("com.ibox.calculators:id/digit4").click();

        //点击等于号

        driver.findElementById("com.ibox.calculators:id/equal").click();


        //获得屏幕上的输出结果内容

        AndroidElement  result = driver.findElementById("com.ibox.calculators:id/output_month");


        //检查编辑框中的计算值
        assert result.getText().equals("8"):"实际值是 : "+result.getText()+" 与期望值8不符";

    }
    @AfterClass
    public void tearDown(){

        driver.quit();
    }

}