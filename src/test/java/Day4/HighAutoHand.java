package Day4;

import Day3.AutoHand;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;


import java.net.MalformedURLException;
import java.time.Duration;

public class HighAutoHand  extends AutoHand {




    /**
     * 基于坐标的放大
     * @param x y
     * @param
     */


    public static void zoom(int x, int y) {


//        这个是多点触摸的管理类
        MultiTouchAction multiTouch = new MultiTouchAction(driver);

        int scrHeight = driver.manage().window().getSize().getHeight();
        int yOffset = 100;

        if (y - 100 < 0) {
            yOffset = y;
        } else if (y + 100 > scrHeight) {
            yOffset = scrHeight - y;
        }

        TouchAction action0 = new TouchAction(driver).press(x, y).waitAction(Duration.ofSeconds(3)).moveTo(0, -yOffset).release();
        TouchAction action1 = new TouchAction(driver).press(x, y).waitAction(Duration.ofSeconds(3)).moveTo(0, yOffset).release();

        multiTouch.add(action0).add(action1);

        multiTouch.perform();
    }

        //基于高级移动多点触摸，自动化手势，放大自己的知乎的个人头像。
    public static void ZoomIcon(){



         //step1:点击菜单

        driver.findElementByXPath("//android.view.View[@resource-id='com.zhihu.android:id/awesome_toolbar']/android.widget.ImageButton[1]").click();


        //step2：点击首页的头像，进入个人资料详情页


       driver.findElementByXPath("//android.widget.RelativeLayout/android.widget.ImageView[1]").click();



        //setp3:再次点击自己详情页头像
        driver.findElementById("com.zhihu.android:id/extended_avatar").click();



      //step4:利用Appium 历史版本的zoom 放大的方法，对自己的个人头像进行放大操作


        int x = driver.manage().window().getSize().width/2;

        int y = driver.manage().window().getSize().height/2;


        zoom(x,y);




    }







    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        setup();

        loginZhiHu();


        Thread.sleep(Long.parseLong("7000"));

        ZoomIcon();

        driver.quit();

    }
}
