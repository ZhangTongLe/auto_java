package Day15;

import com.github.yunusmete.stf.api.STFService;
import com.github.yunusmete.stf.api.ServiceGenerator;
import com.github.yunusmete.stf.model.Device;
import com.github.yunusmete.stf.rest.DeviceResponse;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;

public class TestUnit {

    public static AppiumDriverLocalService service;

    private static  String ACCESS_TOKEN = "36576328289c4d06a0fff3ea037e7158a012da363139401aa987ba92d409a162";

    @Test(priority = 1)
    public  void conn() throws IOException {
        long id = Thread.currentThread().getId();
        System.out.println("Sample test-method One. Thread id is: " + id);
        adbConn();
        System.out.println("连接成功");

    }


    @Test(priority = 2,dataProvider = "data")
    public void createAppiumService(String udid){
        long id = Thread.currentThread().getId();
        System.out.println("Sample test-method One. Thread id is: " + id);
        int port = 4723+(int)(Math.random()*50);
        File location=new File("log");
        String logName = location.getAbsolutePath()+File.separator+ udid+".log";
        service = AppiumDriverLocalService.buildService(new
                AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(port)
                .withArgument(GeneralServerFlag.ROBOT_ADDRESS,udid)
                .withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER,
                        String.valueOf(port + 2))
                .withArgument(SESSION_OVERRIDE)
                .withLogFile(new File(logName)));

        service.start();

        System.out.println(service.getUrl());
        System.out.println("启动成功");


    }




// 注意：在定义测试类的时候，如果@Test使用了singleThreaded=true属性，则该测试类的所有测试方法都只能在单线程中执行！
    @DataProvider(name = "data" ,parallel =true )  //开启并发模式
    public Object[][] dataProvider() {
        List<Object> list = getDevice();
        Object[][] userData = new  Object[list.size()][];
        for (int i=0;i<userData.length;i++)
            userData[i] =  new  Object[]{list.get(i)};
        return userData ;

    }


    //   连接STF平台可以进行远程调试的设备。
    public static void   adbConn() throws IOException {
        List<String>  list  = new ArrayList<String>();
        STFService service = ServiceGenerator.createService(STFService.class, "http://192.168.1.111:7100/api/v1", ACCESS_TOKEN);
        DeviceResponse devices = service.getDevices();
        List<Device> deviceList = devices.getDevices();
        for (Device device : deviceList) {

            if (device.isUsing()) {
                list.add( (String) device.getRemoteConnectUrl());
            }
        }

        for(String str : list) {
            String s ="adb connect"+" "+str ;
            Runtime.getRuntime().exec(s);
        }


    }


    //获取本地已经连接到STF的deviceid

    public static List<Object>  getDevice() {
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


//
//    //测试完毕断开与STF设备的连接
//    @Test(priority = 3)
//    public void disconn() throws IOException {
//
//        long id = Thread.currentThread().getId();
//        System.out.println("Sample test-method One. Thread id is: " + id);
//        String s ="adb kill-server" ;
//        String s1 ="adb devices" ;
//        Runtime rt = Runtime.getRuntime();
//        rt.exec(s);
//        rt.exec(s1);
//        System.out.println("连接断开");
//    }


}



