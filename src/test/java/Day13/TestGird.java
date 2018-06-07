package Day13;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestGird {


    public  AppiumDriver <WebElement> driver;



    @Test(dataProvider = "data")
    public void setUp(String udid) throws MalformedURLException {


        long id = Thread.currentThread().getId();
        System.out.println("Sample test-method One. Thread id is: " + id);

        DesiredCapabilities cas = new DesiredCapabilities();

        cas.setCapability("platformName", "Android");
        cas.setCapability("platformVersion", "4.4.4");
        cas.setCapability("deviceName", "xiaoyaoyou");

        cas.setCapability("udid", udid);
        cas.setCapability("appPackage", "com.android.browser");
        cas.setCapability("appActivity", ".BrowserActivity");

        cas.setCapability("unicodeKeyboard", true);
        cas.setCapability("resetKeyboard", true);


        URL u = new URL("http://192.168.1.101:4444/wd/hub/");

        System.out.println(cas.toString());

        driver = new AppiumDriver<WebElement>(u, cas);

        driver.get("https://www.baidu.com");

        driver.quit();


    }





    @BeforeSuite
    public void beforeSuite() {
        System.out.println("我们开始并发执行Appium");
    }




    @AfterSuite
    public void afterSuite() {

        System.out.println("并发执行结束");
    }











    @DataProvider(name = "data" ,parallel = true  )  //并发

// 注意：在定义测试类的时候，如果@Test使用了singleThreaded=true属性，则该测试类的所有测试方法都只能在单线程中执行！
    public Object[][] dataProvider() {

         List<Object> list = getDevice();

        Object[][] userData = new  Object[list.size()][];

        for (int i=0;i<userData.length;i++)
            userData[i] =  new  Object[]{list.get(i)};


        return userData ;

    }





    public static  List<Object>  getDevice() {
        List<Object> devices = new ArrayList<>();
        String line;
        StringBuilder log = new StringBuilder();
        Process process;
        Runtime rt = Runtime.getRuntime();
        try {
            process = rt.exec(new String[]
                    {"adb", "devices", "-l"});
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(
                    process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(
                    process.getErrorStream()));


            while ((line = stdInput.readLine()) != null) {
                log.append(line);
                log.append(System.getProperty
                        ("line.separator"));
            }
            while ((line = stdError.readLine()) != null) {
                log.append(line);
                log.append(System.getProperty
                        ("line.separator"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scanner scan = new Scanner(String.valueOf(log));
        while (scan.hasNextLine()) {
            String oneLine = scan.nextLine();
            if (oneLine.contains("model")) {
                devices.add(oneLine.split("device")[0].trim());
            }
        }
        return devices;
    }


















}
