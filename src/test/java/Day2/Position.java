package Day2;

import Day1.HelloWorld;

import java.net.MalformedURLException;

public class Position extends HelloWorld {



    public static void resourceid(){

        driver.findElementById("com.zhihu.android:id/login_btn").click();

    }


    public  static void text(){

//    driver.findElementByName("登录").click();


        driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"登录\")").click();

    }


    public  static  void  byClass(){
//    driver.findElementByClassName("android.widget.Button");  //一旦有相同类名，这就是错误的做法


        driver.findElementsByClassName("android.widget.Button").get(0).click();


    }



    public static  void xpath(){

        driver.findElementByXPath("//android.widget.Button[@resource-id='com.zhihu.android:id/login_btn']").click();


    }






    public static void main(String[] args) throws MalformedURLException {

        setup();
//     resourceid();


//        text();

//        byClass();

        xpath();

        driver.quit();

    }

}
