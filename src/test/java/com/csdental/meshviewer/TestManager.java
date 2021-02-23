package com.csdental.meshviewer;

import com.csdental.web.IWebDriverWrapper;
import com.csdental.web.WebDriverFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

@Test(groups={"smoke","function_test"})
public class TestManager {
    IWebDriverWrapper webDriverWrapper;
    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeclass(ITestContext context, @Optional("chrome") String browser,@Optional("http://localhost:3000/") String url){
        webDriverWrapper=new WebDriverFactory().wrapWebDriver(browser);
        webDriverWrapper.get(url);
        getWebDriverWrapper().takeScreenshot("getUrl");
    }
    @AfterClass
    public void afterclass(){
        webDriverWrapper.quit();
    }

    public IWebDriverWrapper getWebDriverWrapper() {
        return webDriverWrapper;
    }

}
