package Day7.steps.hooks;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;
import java.net.URL;


public class MbaiduHook{

    @Autowired
    AppiumDriver<WebElement> driver;


    @Autowired
    DesiredCapabilities cas;





    @After()
    public void quit() {



        driver.quit();
        System.out.println("退出啦");


    }



}
