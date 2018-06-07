package Day7.base;

import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class Driver {


    public AndroidDriver<WebElement> driver = null;

    protected Duration duration = Duration.ofSeconds(1);


    public Driver() { }
    private static volatile Driver instance;
    public static Driver getIstance() {
        // 对象实例化时与否判断（不使用同步代码块，instance不等于null时，直接返回对象，提高运行效率）
        if (instance == null) {
            //同步代码块（对象未初始化时，使用同步代码块，保证多线程访问时对象在第一次创建后，不再重复被创建）
            synchronized (Driver.class) {
                //未初始化，则初始instance变量
                if (instance == null) {
                    instance = new Driver();
                }
            }
        }
        return instance;
    }


    public AndroidDriver<WebElement> createDriver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "4.4.4");
        caps.setCapability("deviceName", "123");
        // 设置输入法
        caps.setCapability("unicodeKeyboard", "True");
        caps.setCapability("resetKeyboard", "True");
        caps.setCapability("appPackage", "com.android.browser");
        caps.setCapability("appActivity", ".BrowserActivity");
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }

    public AndroidDriver<WebElement> getDriver() throws MalformedURLException {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }


    /**跳转到webview页面 **/
    public void switchWebview(String contextName, AppiumDriver<WebElement> driver) {
        try {
            Set<String> contexts = driver.getContextHandles();
            for (String context : contexts) {
                System.out.println(context);
                //打印出来看看有哪些context
            }
            driver.context(contextName);
        } catch (NoSuchContextException nce) {
            //  LogLog.error("没有这个context:" + contextName, nce);

            Assert.fail("没有这个context:" + contextName, nce);
        }

    }


   /**验证当前页面是否包含此文本，包含的话就返回那个文本 **/

    public String yznr(String txt, AppiumDriver<WebElement> driver) {
        String result = null;
        if (driver.getPageSource().contains(txt)) {
            result = txt;
        }
        return result;
    }




    /**点击**/
    public void click(By byElement, AppiumDriver<WebElement> driver) {
        WebElement element = findElement(byElement, driver);

        try {
            element.click();
        } catch (Exception e) {
            Assert.fail("点击元素:" + getLocatorByElement(element, ">") + "失败", e);
        }

    }


    /**根据元素来获取此元素的定位值* */

    public String getLocatorByElement(WebElement element, String expectText) {
        String text = element.toString();
        String expect = null;
        try {
            expect = text.substring(text.indexOf(expectText) + 1, text.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return expect;

    }



    /**一点点的自定义清空* */
    public void clear(WebElement we,AppiumDriver<WebElement> driver) {
        int length = we.getText().length();

        ((PressesKeyCode) driver).pressKeyCode(AndroidKeyCode.DEL);

        }


    /**通过By对象 去查找某个元素 * */

    public WebElement findElement(By by, AppiumDriver<WebElement> driver) {
        return driver.findElement(by);
    }


    /**通过By对象 去查找一组元素 **/

    public List<WebElement> findElements(By by,AppiumDriver<WebElement> driver) {
        return driver.findElements(by);
    }


    /* 强力定位某个元素*/

    public WebElement findElement(String classname, String text, String id, AppiumDriver<WebElement> driver) {


        String uiSelector = " new UiSelector()."
                + "className(\"" + classname + "\")"
                + ".textContains(\"" + text + "\")"
                + ".resourceId(\"" + id + "\")";

        return driver.findElement(MobileBy.AndroidUIAutomator(uiSelector));

    }



    /**清空元素内容**/

    public void clear(By byElement, AppiumDriver<WebElement> driver) {
        WebElement element = findElement(byElement, driver);
        element.clear();

    }


    /**输入内容**/

    public void typeContent(By byElement, String str, AppiumDriver<WebElement> driver) {
        WebElement element = findElement(byElement, driver);
        element.sendKeys(str);

    }


    String UiScrollable(String uiSelector) {
        return "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView("
                + uiSelector + ".instance(0));";
    }

    /**滚动模糊查找**/

    public WebElement scrollTo(String text, AppiumDriver<WebElement> driver) {
        String uiScrollables =
                UiScrollable("new UiSelector().descriptionContains(\"" + text + "\")") + UiScrollable(
                        "new UiSelector().textContains(\"" + text + "\")");
        return driver.findElement(MobileBy.AndroidUIAutomator(uiScrollables));
    }

    /**滚动精确查找**/
    public WebElement scrollToExact(String text, AppiumDriver<WebElement> driver)
    {
        String uiScrollables =
                UiScrollable("new UiSelector()."
                        +"description(\"" + text + "\")")
                        + UiScrollable("new UiSelector()"
                        + ".text(\"" + text + "\")");

        return driver.findElement(MobileBy.AndroidUIAutomator(uiScrollables));
    }


    /**点击一个元素的中心点*/
    public PointOption getCenter(MobileElement element) {

        Point upperLeft = element.getLocation();
        Dimension dimensions = element.getSize();
        return PointOption.point(upperLeft.getX() + dimensions.getWidth() / 2, upperLeft.getY() + dimensions.getHeight() / 2);
    }


    /**
     * 基于元素的放大
     * @param el
     * @param driver
     */

    public void zoom(WebElement el, AppiumDriver<WebElement> driver) {
        MultiTouchAction multiTouch = new MultiTouchAction(driver);

        Dimension dimensions = el.getSize();
        Point upperLeft = el.getLocation();
        Point center = new Point(upperLeft.getX() + dimensions.getWidth() / 2,
                upperLeft.getY() + dimensions.getHeight() / 2);
        int yOffset = center.getY() - upperLeft.getY();

        TouchAction action0 = new TouchAction(driver).press(center.getX(), center.getY())
                .moveTo(el, center.getX(), center.getY() - yOffset).release();
        TouchAction action1 = new TouchAction(driver).press(center.getX(), center.getY())
                .moveTo(el, center.getX(), center.getY() + yOffset).release();

        multiTouch.add(action0).add(action1);

        multiTouch.perform();
    }


    /**
     * 基于坐标的放大
     * @param x
     * @param y
     * @param driver
     */


    public void zoom(int x, int y, AppiumDriver<WebElement> driver) {
        MultiTouchAction multiTouch = new MultiTouchAction(driver);

        int scrHeight = driver.manage().window().getSize().getHeight();
        int yOffset = 100;

        if (y - 100 < 0) {
            yOffset = y;
        } else if (y + 100 > scrHeight) {
            yOffset = scrHeight - y;
        }

        TouchAction action0 = new TouchAction(driver).press(x, y).moveTo(0, -yOffset).release();
        TouchAction action1 = new TouchAction(driver).press(x, y).moveTo(0, yOffset).release();

        multiTouch.add(action0).add(action1);

        multiTouch.perform();
    }


    /**
     * 滑动
     * @param startx
     * @param starty
     * @param endx
     * @param endy
     * @param driver
     */

    public void swipe(int startx, int starty, int endx, int endy, AppiumDriver<WebElement> driver) {
        TouchAction touchAction = new TouchAction(driver);

        // appium converts press-wait-moveto-release to a swipe action
        touchAction.press(startx, starty).waitAction(duration).moveTo(endx, endy).release();

        touchAction.perform();
    }



    /*
     * 缩小元素
     * @param el The element to pinch
     */

    public void pinch(WebElement el, AppiumDriver<WebElement> driver) {
        MultiTouchAction multiTouch = new MultiTouchAction(driver);

        Dimension dimensions = el.getSize();
        Point upperLeft = el.getLocation();
        Point center = new Point(upperLeft.getX() + dimensions.getWidth() / 2,
                upperLeft.getY() + dimensions.getHeight() / 2);
        int yOffset = center.getY() - upperLeft.getY();

        TouchAction action0 =
                new TouchAction(driver).press(el, center.getX(), center.getY() - yOffset).moveTo(el)
                        .release();
        TouchAction action1 =
                new TouchAction(driver).press(el, center.getX(), center.getY() + yOffset).moveTo(el)
                        .release();

        multiTouch.add(action0).add(action1);

        multiTouch.perform();
    }


    /**
     * Convenience method for pinching an element on the screen.
     * "pinching" refers to the action of two appendages pressing the screen and sliding towards each other.
     * NOTE:
     * This convenience method places the initial touches around the element at a distance, if this would happen to place
     * one of them off the screen, appium will return an outOfBounds error. In this case, revert to using the
     * MultiTouchAction api instead of this method.
     *
     * @param x x coordinate to terminate the pinch on
     * @param y y coordinate to terminate the pinch on
     */

    public void pinch(int x, int y, AppiumDriver<WebElement> driver) {
        MultiTouchAction multiTouch = new MultiTouchAction(driver);

        int scrHeight = driver.manage().window().getSize().getHeight();
        int yOffset = 100;

        if (y - 100 < 0) {
            yOffset = y;
        } else if (y + 100 > scrHeight) {
            yOffset = scrHeight - y;
        }

        TouchAction action0 = new TouchAction(driver).press(x, y - yOffset).moveTo(x, y).release();
        TouchAction action1 = new TouchAction(driver).press(x, y + yOffset).moveTo(x, y).release();

        multiTouch.add(action0).add(action1);

        multiTouch.perform();
    }


    /**
     * 滑动操作，从某个元素滑动到某个元素
     */
    public void DragAndDrop(By dragElement, By dropElement, AppiumDriver<WebElement> driver) {
        TouchAction act = new TouchAction(driver);
        act.press(findElement(dragElement, driver)).perform();
        act.moveTo(findElement(dropElement, driver)).release().perform();
    }


    /**
     * 向上滑（基于坐标x:width / 、y:height * 4  滑到x:width / 2, y:height / 4）
     * @param driver
     */

    public void swipeUp(AppiumDriver<WebElement> driver) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction action1 = new TouchAction(driver).press(width / 2, height * 4 / 5).waitAction(duration).moveTo(width / 2, height / 4).release();
        action1.perform();
    }

    //向下滑
    public void swipeDown(AppiumDriver<WebElement> driver)// scroll down to refresh
    {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction action1 = new TouchAction(driver).press(width / 2, height / 4).waitAction(duration).moveTo(width / 2, height * 3 / 4).release();
        action1.perform();
    }

    //向左滑
    public void swipeLeft(AppiumDriver<WebElement> driver) {


        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction action1 = new TouchAction(driver).press(width - 10, height / 2).waitAction(duration).moveTo(width / 4, height / 2).release();
        action1.perform();
    }

    //向右滑
    public void swipeRight(AppiumDriver<WebElement> driver) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction action1 = new TouchAction(driver).press(10, height / 2).waitAction(duration).moveTo(width * 3 / 4 + 10, height / 2).release();
        action1.perform();
    }



    public WebElement getElementByIndex(int index){

        return driver.findElementByAndroidUIAutomator("new UiSelector().index("+index+")");
    }





}