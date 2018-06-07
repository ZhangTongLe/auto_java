package Day9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium2Example  {
    public static void main(String[] args) {
        //创建一个Firefox驱动程序的新实例
        //注意代码的其余部分依赖于接口，
        //不是实现。
        WebDriver driver = new ChromeDriver();

        //现在用这个来访问百度
        driver.get("https://www.baidu.com");

        //或者可以像这样完成同样的事情
        // driver.navigate().to("https://www.baidu.com");

        // 按名称查找文本输入元素
        WebElement element = driver.findElement(By.name("wd"));

        // 输入要搜索的内容
        element.sendKeys("selenium!");



        // 现在提交表单。 WebDriver将从元素中找到我们的表单
        element.submit();

        // 检查页面的标题
        System.out.println("Page title is: " + driver.getTitle());

        //等待页面加载，10秒后超时
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("selenium!");
            }
        });


        //应该看到：“selenium！ - 百度搜索”
        System.out.println("Page title is: " + driver.getTitle());

        //关闭浏览器
        driver.quit();
    }
}