package Day6;
import org.testng.annotations.*;
public class TestAnnotation {


    @BeforeSuite   //注解的方法将只运行一次，运行所有测试前此套件中。
    public void beforeSuite() {
        System.out.println("我是BeforeSuite");
    }


    @BeforeTest  //注解的方法将被运行之前的任何测试方法属于内部类的
    public void beforeTest() {
        System.out.println("我是BeforeTest");
    }

    @BeforeClass //注解的方法将只运行一次先行先试在当前类中的方法调用。
    public void beforeClass() {
        System.out.println("我是BeforeClass");
    }



    @BeforeMethod  //注解的方法将每个测试方法之前运行。
    public void beforeMethod() {
        System.out.println("我是beforeMethod");
    }



// 使用priority指定执行顺序(默认值为0)，数值越小，越先执行！

    @Test(priority = 2,dataProvider = "data")
    public void testcase1(String dayNum) {
        System.out.println(dayNum);

        long id = Thread.currentThread().getId();
        System.out.println("dayNum：Thread id is: " + id);

        System.out.println("执行case1");
    }



    @Test(priority = 1)  //用priority属性来调整 test方法的运行顺序。
    public void testcase2() {

        System.out.println("执行case2");

    }


    @DataProvider(name = "data" ,parallel = true  )  //并发

// 注意：在定义测试类的时候，如果@Test使用了singleThreaded=true属性，则该测试类的所有测试方法都只能在单线程中执行！
//    @DataProvider(name = "data" )  //正常单线程执行
    public Object[][] dataProvider() {
        return new Object[][]{
                {"Day1"},
                {"Day2"},
                {"Day3"},


        };
    }



    @AfterMethod  	//每个测试方法被运行后执行。
    public void afterMethod() {
        System.out.println("我是afterMethod");
    }

    @AfterClass  //注解的方法将只运行一次后已经运行在当前类中的所有测试方法。
    public void afterClass() {
        System.out.println("我是AfterClass");
    }



    @AfterTest //注解的方法将被运行后，所有的测试方法，属于内部类的的运行。
    public void afterTest() {
        System.out.println("我是AfterTest");
    }

    @AfterSuite //注解的方法将只运行一次此套件中的所有测试都运行之后。
    public void afterSuite() {
        System.out.println("我是AfterSuite");
    }
}
