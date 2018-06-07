package Day8;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * 作者：张同乐
 * 创建时间：2018/03/09
 * 联系QQ:1071235258
 */
public class AdbTest {


    @Test(dataProvider = "data")
    public void install(String device) throws IOException {
        String result = null;
        String line = null;
        StringBuilder log = new StringBuilder();
        Runtime runtime = Runtime.getRuntime();
        String command = "adb  -s " +device+ " install -r  C://zhihu.apk";
        Process process = runtime.exec(command);
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(
                process.getInputStream()));
        while ((line = stdInput.readLine()) != null) {
            log.append(line);
            log.append(System.getProperty
                    ("line.separator"));
        }
        Scanner scan = new Scanner(String.valueOf(log));
        while (scan.hasNextLine()) {
            String oneLine = scan.nextLine();
            if(oneLine.contains("Success")) {
                result ="安装成功";
                System.out.println(result);
            }
        }

    }



    @Test(dataProvider = "data")
    public void xnTestStart(String device) throws IOException {
        String result = null;
        String line = null;
        StringBuilder log = new StringBuilder();
        Runtime runtime = Runtime.getRuntime();
        String command = "adb  -s " +device+ " install -r  D://TestJar//AutoTest//auto//apk//GT_2.2.6.5.apk";
        Process process = runtime.exec(command);
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(
                process.getInputStream()));
        while ((line = stdInput.readLine()) != null) {
            log.append(line);
            log.append(System.getProperty
                    ("line.separator"));
        }
        Scanner scan = new Scanner(String.valueOf(log));
        while (scan.hasNextLine()) {
            String oneLine = scan.nextLine();
            if(oneLine.contains("Success")) {

                result ="安装成功";
                System.out.println(result);

                String command1 = "adb shell am start -W -n com.tencent.wstt.gt/com.tencent.wstt.gt.activity.GTMainActivity";

                runtime.exec(command1);


                String command2  =" adb shell am broadcast -a com.tencent.wstt.gt.baseCommand.startTest --es pkgName 'com.zhihu.android'";

                runtime.exec(command2);


                String command3 =  " adb shell am broadcast -a com.tencent.wstt.gt.baseCommand.sampleData --ei cpu 1";

                runtime.exec(command3);


             String command4 ="    adb pull    /sdcard/GT/GW c://apk/";







            }
        }

    }













    @DataProvider(name = "data" ,parallel = true)
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
