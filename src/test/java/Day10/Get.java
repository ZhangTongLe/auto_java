package Day10;

import Day1.HelloWorld;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;

public class Get extends HelloWorld {


    public  static  void getMessage(){

        System.out.println( "我是 driver.getDeviceTime()" +driver.getDeviceTime());
//
//       Map<String,String> map = driver.getAppStringMap();
//
//
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//
//        }


        System.out.println(  driver.getRemoteAddress());


//        System.out.println( driver.getAutomationName());




//       driver.navigate().forward(); // 前进
//        // driver.navigate().back(); // 后退
//        driver.navigate().refresh(); // 刷新




        String text=  driver.getConnection().toString();


        System.out.println(text);

    }



    /***
     * 检查网络
     * @return 是否正常
     */
    public static boolean checkNet(){



        String text=  driver.getConnection().toString();
        if(text.contains("Data: true"))
            return true;
        else
            return false;
    }



    private Point getCenter(WebElement element) {

        Point upperLeft = element.getLocation();
        Dimension dimensions = element.getSize();
        return new Point(upperLeft.getX() + dimensions.getWidth()/2, upperLeft.getY() + dimensions.getHeight()/2);
    }


//    @Test
//    public void verifyLastName() {
//        assert "Beust".equals(m_lastName) : "Expected name Beust, for" + m_lastName;
//    }










//    http://blog.csdn.net/heart_1014/article/details/51872025


    public static void main(String[] args) throws MalformedURLException {


        setup();
        getMessage();


        driver.quit();




    }




}
