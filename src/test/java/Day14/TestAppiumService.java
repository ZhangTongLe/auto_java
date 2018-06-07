package Day14;

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

public class TestAppiumService{

    public static AppiumDriverLocalService service;

    public  static STF stf;



    @Test(priority =1)

    public  void conn() throws IOException {

        STF.adbcon();



    }




    @Test(dataProvider = "data" ,priority = 2)

    public void createAppiumService(String udid) throws IOException {

//        adbcon();

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







    @DataProvider(name = "data" ,parallel =true )  //开启并发模式

// 注意：在定义测试类的时候，如果@Test使用了singleThreaded=true属性，则该测试类的所有测试方法都只能在单线程中执行！
    public Object[][] dataProvider() {

        List<Object> list = getDevice();

        Object[][] userData = new  Object[list.size()][];

        for (int i=0;i<userData.length;i++)
            userData[i] =  new  Object[]{list.get(i)};


        return userData ;

    }




    public static  List<Object>  getDevice() {
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






}
