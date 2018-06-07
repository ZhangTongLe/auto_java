package Day6;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;

public class ScreenshotListener extends TestListenerAdapter{


	@Override
	public void onTestFailure(ITestResult tr){
		
		
 // 从TestApplication类获取驱动程序实例,这个好处就是TestN赋予的。
	AndroidDriver driver=Screenshot.getDriver();
	 
	File location=new File("screenshots");
		// 它会在项目中创建截图文件夹
		String screenShotName = location.getAbsolutePath()+File.separator+ tr.getMethod().getMethodName()+".png";
	// 使用ITestResult获取失败方法名称，getScreenshotAs是屏幕截图方法。
	File screenShot=driver.getScreenshotAs(OutputType.FILE);  
	try {  
		FileUtils.copyFile(screenShot,new File(screenShotName));
		
	}catch (IOException e) {  
		e.printStackTrace();
		}
	 }
	
}