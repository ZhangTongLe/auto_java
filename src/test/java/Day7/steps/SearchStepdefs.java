package Day7.steps;

import Day7.action.SearchTo;
import Day7.config.SpringConfig;
import cucumber.api.java.zh_cn.假如;
import cucumber.api.java.zh_cn.当;
import cucumber.api.java.zh_cn.那么;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;

@ContextConfiguration(classes = SpringConfig.class)
public class SearchStepdefs {
    @Autowired
    private SearchTo search;

    private String result = null;

    @假如("^用户打开(.*)$")
    public void open(String url) throws Exception {
        search.open(url);
    }

    @当("^输入关键字：(.*)$")
    public void input(String keyword) throws Exception {
        result = search.find(keyword);
    }

    @那么("^搜索结果：(.*)就会显示出来$")
    public void result(String expectedResult) {

        Assert.assertEquals((Object) expectedResult, (Object) result);
    }
}
