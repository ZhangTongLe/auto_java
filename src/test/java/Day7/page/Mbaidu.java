package Day7.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
public class Mbaidu {


    @Autowired
    AppiumDriver<WebElement> driver;


    @WithTimeout(time = 7, unit = TimeUnit.SECONDS)


    @FindBy(xpath = "//*[@id='index-kw']")
    //手机百度的搜索框
    public WebElement keys;

    @FindBy(xpath = "//*[@id='index-bn']")
    //手机百度的搜索按钮
    public WebElement btn;


    public Mbaidu( AppiumDriver<WebElement> driver){


        this.driver = driver;

        PageFactory.initElements(new AppiumFieldDecorator(driver,
                        15, //所有策略的默认隐式等待超时
                        TimeUnit.SECONDS),
                this //PageObject.class的一个实例
        );
    }




}
