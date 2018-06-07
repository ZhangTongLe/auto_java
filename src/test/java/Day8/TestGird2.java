package Day8;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class TestGird2 {


        private String reportDirectory = "reports";
        private String reportFormat = "xml";
        private String testName = "Untitled";

        protected AndroidDriver<AndroidElement> driver = null;

        DesiredCapabilities cas = new DesiredCapabilities();

        @BeforeMethod
        public void setUp() throws MalformedURLException {
            cas.setCapability("reportDirectory", reportDirectory);
            cas.setCapability("reportFormat", reportFormat);
            cas.setCapability("testName", testName);
            cas.setCapability("platformName", "Android");
            cas.setCapability("platformVersion", "4.4.4");
            cas.setCapability("appPackage", "com.zhihu.android");   //

            cas.setCapability("appActivity", ".app.ui.activity.MainActivity");//被测app的入口activity

            cas.setCapability("unicodeKeyboard", true);
            cas.setCapability("resetKeyboard", true);



            driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), cas);
        }

    @Test
    public void testUntitled(){

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

        @AfterMethod
        public void tearDown() {
            driver.quit();
        }





}
