package Day7.action;

import Day7.base.Action;
import Day7.page.Mbaidu;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class SearchTo {

    //Step1：深度封装定制Appiumd的动作方法。

    @Autowired
    Action action;
    //Step2:交给spring来管理AppiumDriver。

    @Autowired
    AppiumDriver<WebElement> driver;



    //Step3：创建页面对象，让页面元素操作起来异常轻松。
    @Autowired
    Mbaidu mbaidu;




    //Step4:下方是通过上方的提供的页面对象，和自己定制的方法，进行业务逻辑操作
    //1、打开url
    public void open(String url) {


        driver.get(url);

    }

    //2、查找cucumber是不是显示了
    public String find(String keyword) {
        action.switchWebview("WEBVIEW_com.android.browser");
        mbaidu.keys.click();
        mbaidu.keys.sendKeys(keyword);

        mbaidu.btn.click();

        return action.yznr(keyword);
    }
}