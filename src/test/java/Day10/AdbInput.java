package Day10;

import Day1.HelloWorld;
import io.appium.java_client.android.AndroidElement;

import java.io.IOException;

public class AdbInput  extends HelloWorld{




    public static void input() throws IOException {


        driver.findElementById("com.zhihu.android:id/login_btn").click();

        AndroidElement user   = driver.findElementByXPath(" //android.widget.EditText[@resource-id='com.zhihu.android:id/username']");

        user.click();

        long user_startTime = System.currentTimeMillis();//获取当前时间

        user.sendKeys("15811006613");




        long user_endTime = System.currentTimeMillis();
        System.out.println("用户输入框_Appium_输入程序运行时间："+(user_endTime-user_startTime)+"ms");


        AndroidElement password = driver.findElementByXPath(" //android.widget.EditText[@resource-id='com.zhihu.android:id/password']");


        password.click();

        long password_startTime = System.currentTimeMillis();//获取当前时间


        String s ="adb shell input text  15811006666";
        Runtime.getRuntime().exec(s);

        long password_endTime = System.currentTimeMillis();



        System.out.println("用户密码框_adb input_输入程序运行时间："+(password_endTime-password_startTime)+"ms");




    }







    public static void main(String[] args) throws IOException {
        setup();

        input();

        driver.quit();
    }









}
