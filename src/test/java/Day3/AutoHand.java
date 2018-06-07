
package Day3;

import Day1.HelloWorld;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;


import java.net.MalformedURLException;
import java.time.Duration;


public class AutoHand  extends HelloWorld{



//    向左滑（x轴变小，y轴不动）
     public  static void swipeLeft() {

        // 获取屏幕分辨率,已屏幕分辨率为比例进行按位置进行滑动


        int width = driver.manage().window().getSize().width;   //1080
        int height = driver.manage().window().getSize().height; //1920

// 让手指按压屏幕高度一半，且靠近右边屏幕的地方。等待2秒之后向，左滑动屏幕宽度的4分之一。
  TouchAction action1 = new TouchAction(driver).press(width -10, height / 2).waitAction(Duration.ofSeconds(2)).moveTo(width / 4, height / 2).release();
         action1.perform();
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





//  登陆知乎。
    public static void loginZhiHu() throws InterruptedException {

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




    /**
     * 基于坐标的滑动
     * @param startx
     * @param starty
     * @param endx
     * @param endy
     * @param
     */

    public static void swipe(int startx, int starty, int endx, int endy) {
        TouchAction touchAction = new TouchAction(driver);

        // appium converts press-wait-moveto-release to a swipe action
        touchAction.press(startx, starty).waitAction(Duration.ofSeconds(2)).moveTo(endx, endy).release();

        touchAction.perform();
    }





    //滑动知乎首页内容，第一列内容。
    public static void  swipeLeftFirst(){





       AndroidElement element =  driver.findElementByXPath(
                " //android.support.v7.widget.RecyclerView[@resource-id='com.zhihu.android:id/recycler_view']/android.support.v7.widget.LinearLayoutCompat[1]");


       //用Point类来获取和存储元素的左上角的起始（x,y）坐标
        Point upperLeft = element.getLocation();

       // 用Dimension 类来获取存储 元素的宽和高
        Dimension dimensions = element.getSize();


        //获得此元素的中心 的x坐标，用此元素左上角的x坐标加上，元素的一半宽。

        int startX =upperLeft.getX()+ dimensions.getWidth() / 2;

        //获得此元素的中心 的y坐标，用此元素左上角的y坐标加上，元素的一半高。
        int starty = upperLeft.getY() + dimensions.getHeight() / 2;


        // 相当于让手指滑动到endx 的坐标处
        int endX = startX / 2;


        // 因为是向左滑,y轴不变，x轴缩小
        int endy = starty;


        swipe(startX,starty,endX,endy);


    }





    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        setup();

        //我们在知乎启动页面执行两次左滑动,在启动界面，不要操作太快容易出错丫，给他个休息时间。

        swipeLeft();

        Thread.sleep(Long.parseLong("5000"));

        swipeLeft();


        //登录知乎
         loginZhiHu();



        //网络内容加载的慢，在给他点时间。
          Thread.sleep(Long.parseLong("7000"));

         //让知乎主页内容第一列进行左滑动.
          swipeLeftFirst();



         driver.quit();





    }


}
