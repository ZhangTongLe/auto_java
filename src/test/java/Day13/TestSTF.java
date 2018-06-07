package Day13;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class TestSTF {

    private static final String STF_SERVICE_URL = "http://192.168.1.111:7100";  // Change this URL
    private static final String ACCESS_TOKEN = "36576328289c4d06a0fff3ea037e7158a012da363139401aa987ba92d409a162";  // Change this access token

    private static final String deviceSerial = "192.168.1.101:5555";

    private static AndroidDriver androidDriver;

    private DeviceApi deviceApi;



    private void connectToStfDevice() throws MalformedURLException, URISyntaxException {
        STFService stfService = new STFService(STF_SERVICE_URL,
                ACCESS_TOKEN);
        this.deviceApi = new DeviceApi(stfService);
        this.deviceApi.connectDevice(deviceSerial);
    }

    public void setup() throws MalformedURLException, URISyntaxException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "ANDROID");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");

        caps.setCapability("udid", deviceSerial);

        caps.setCapability("unicodeKeyboard", "True");
        caps.setCapability("resetKeyboard", "True");


//        File apkPath = new File(System.getProperty("user.dir") + "\\apk\\zhihu.apk");
//        caps.setCapability("app", apkPath.getAbsolutePath());

        caps.setCapability("appPackage", "com.zhihu.android");   //


// 被测app的启动activity
        caps.setCapability("appActivity", ".app.ui.activity.MainActivity");//被测app的入口activity



        connectToStfDevice();

        //与Appium Server 建立通信的链接！
        URL u = new URL("http://192.168.1.111:4723/wd/hub/");

// 实例化一个Android driver
        androidDriver = new AndroidDriver<AndroidElement>(u, caps);

    }


    public static void main(String[] args) throws MalformedURLException, URISyntaxException {

        TestSTF testSTF = new TestSTF();

        testSTF.setup();

        androidDriver.quit();


    }





}
