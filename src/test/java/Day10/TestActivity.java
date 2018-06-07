package Day10;

import Day1.HelloWorld;
import io.appium.java_client.android.Activity;

import java.net.MalformedURLException;

public class TestActivity  extends HelloWorld{



    public static  void goView(){

        Activity activity = new Activity("com.sogou.mobiletoolassist","com.sogou.doraemonbox.AssistActivity");

//        Activity activity = new Activity("com.zhihu.android", ".app.ui.activity.MainActivity");
//        activity.setAppWaitPackage("com.sogou.mobiletoolassist");
//        activity.setAppWaitActivity("com.sogou.doraemonbox.tool.tcpdump.TcpDumpActivity");
        driver.startActivity(activity);

         String  s =  activity.getAppActivity();


        System.out.println(s);

        Activity activity1 = new Activity("com.sogou.mobiletoolassist","com.sogou.doraemonbox.tool.tcpdump.TcpDumpActivity");

        driver.startActivity(activity1);





    }

    public static void main(String[] args) throws MalformedURLException {
        setup();

        goView();

        driver.quit();

    }






}
