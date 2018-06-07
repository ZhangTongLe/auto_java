package Day7.base;


import io.appium.java_client.*;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Set;




@Component
public class Action {

    @Autowired
    AppiumDriver<WebElement> driver;

    protected Duration duration = Duration.ofSeconds(1);




    /**跳转到webview页面 123**/
    public void switchWebview(String contextName) {

        try {
            Set<String> contexts = driver.getContextHandles();
            for (String context : contexts) {
//                System.out.println(context);
                //打印出来看看有哪些context123
            }
            driver.context(contextName);
        } catch (NoSuchContextException nce) {
            //  LogLog.error("没有这个context:" + contextName, nce);

            Assert.fail("没有这个context:" + contextName, nce);
        }



    }


    /**验证当前页面是否包含此文本，包含的话就返回那个文本 **/

    public String yznr(String txt) {
        String result = null;
        if (driver.getPageSource().contains(txt)) {
            result = txt;
        }
        return result;
    }




    /**点击**/
    public void click(By byElement) {
        WebElement element = findElement(byElement);

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
    public void clear(WebElement we) {
        int length = we.getText().length();

        ((PressesKeyCode) driver).pressKeyCode(AndroidKeyCode.DEL);

    }


    /**通过By对象 去查找某个元素 * */

    public WebElement findElement(By by) {
        return driver.findElement(by);
    }


    /**通过By对象 去查找一组元素 **/

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }


    /* 强力定位某个元素*/

    public WebElement findElement(String classname, String text, String id) {


        String uiSelector = " new UiSelector()."
                + "className(\"" + classname + "\")"
                + ".textContains(\"" + text + "\")"
                + ".resourceId(\"" + id + "\")";

        return driver.findElement(MobileBy.AndroidUIAutomator(uiSelector));

    }



    /**清空元素内容**/

    public void clear(By byElement) {
        WebElement element = findElement(byElement);
        element.clear();

    }


    /**输入内容**/

    public void typeContent(By byElement, String str) {
        WebElement element = findElement(byElement);
        element.sendKeys(str);

    }


    String UiScrollable(String uiSelector) {
        return "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView("
                + uiSelector + ".instance(0));";
    }

    /**滚动模糊查找**/

    public WebElement scrollTo(String text) {
        String uiScrollables =
                UiScrollable("new UiSelector().descriptionContains(\"" + text + "\")") + UiScrollable(
                        "new UiSelector().textContains(\"" + text + "\")");
        return driver.findElement(MobileBy.AndroidUIAutomator(uiScrollables));
    }

    /**滚动精确查找**/
    public WebElement scrollToExact(String text) {
        String uiScrollables =
                UiScrollable("new UiSelector().description(\"" + text + "\")") + UiScrollable(
                        "new UiSelector().text(\"" + text + "\")");
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
     * @param
     */

    public void zoom(WebElement el) {
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
     * @param
     */


    public void zoom(int x, int y) {
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
     * @param
     */

    public void swipe(int startx, int starty, int endx, int endy) {
        TouchAction touchAction = new TouchAction(driver);


        // appium converts press-wait-moveto-release to a swipe action
        touchAction.press(startx, starty).waitAction(duration).moveTo(endx, endy).release();

        touchAction.perform();
    }



    /*
     * 缩放元素
     * @param el The element to pinch
     */

    public void pinch(WebElement el) {
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

    public void pinch(int x, int y) {
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
    public void DragAndDrop(By dragElement, By dropElement) {
        TouchAction act = new TouchAction(driver);
        act.press(findElement(dragElement)).perform();
        act.moveTo(findElement(dropElement)).release().perform();
    }


    /**
     * 向上滑（基于坐标x:width / 、y:height * 4  滑到x:width / 2, y:height / 4）
     * @param
     */

    public void swipeUp() {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction action1 = new TouchAction(driver).press(width / 2, height * 4 / 5).waitAction(duration).moveTo(width / 2, height / 4).release();
        action1.perform();
    }

    //向下滑
    public void swipeDown()// scroll down to refresh
    {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction action1 = new TouchAction(driver).press(width / 2, height / 4).waitAction(duration).moveTo(width / 2, height * 3 / 4).release();
        action1.perform();
    }

    //向左滑
    public void swipeLeft() {


        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction action1 = new TouchAction(driver).press(width - 10, height / 2).waitAction(duration).moveTo(width / 4, height / 2).release();
        action1.perform();
    }

    //向右滑
    public void swipeRight() {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction action1 = new TouchAction(driver).press(10, height / 2).waitAction(duration).moveTo(width * 3 / 4 + 10, height / 2).release();
        action1.perform();
    }




    /**
     * @see
     */

    public void tap(int fingers, WebElement element) {
        MultiTouchAction multiTouch = new MultiTouchAction(driver);

        for (int i = 0; i < fingers; i++) {
            multiTouch.add(createTap(element));
        }

        multiTouch.perform();
    }



    public void tap(int fingers, int x, int y) {
        MultiTouchAction multiTouch = new MultiTouchAction(driver);

        for (int i = 0; i < fingers; i++) {
            multiTouch.add(createTap(x, y));
        }

        multiTouch.perform();
    }

    private TouchAction createTap(int x, int y) {
        TouchAction tap = new TouchAction(driver);
        return tap.press(x, y).waitAction(duration).release();
    }

    private TouchAction createTap(WebElement element) {
        TouchAction tap = new TouchAction(driver);
        return tap.press(element).waitAction(duration).release();
    }






}