package Day10;

import Day1.HelloWorld;
import org.testng.annotations.Test;

import java.io.IOException;

public class XnTest extends HelloWorld {





    @Test

    public  static void install() throws IOException {


        String command1 ="adb install D:\\TestJar\\AutoTest\\auto\\apk\\GT_2.2.6.5.apk";

        String command2 ="adb install D:\\TestJar\\AutoTest\\auto\\apk\\zhihu.apk";

        Process process1 = Runtime.getRuntime().exec(command1);

        Process process2 = Runtime.getRuntime().exec(command2);
    }







    public static void main(String[] args) throws IOException {

        install();

        setup();

        driver.quit();



    }



}
