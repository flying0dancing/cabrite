package com.csdental.meshviewer;

import com.csdental.util.Helper;
import com.csdental.util.IProp;
import com.csdental.web.IWebDriverWrapper;
import com.csdental.web.WebDriverFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;

@Test(groups={"smoke","function_test"})
public class TestManager {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    IWebDriverWrapper webDriverWrapper;
    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeclass(ITestContext context, @Optional("chrome") String browser,@Optional("http://localhost:3001/") String url){
        webDriverWrapper=new WebDriverFactory().wrapWebDriver(browser);
        webDriverWrapper.get(url);
        logger.info("open website url:{}",url);
        getWebDriverWrapper().takeScreenshot("getUrl");
    }

    @AfterClass
    public void afterclass(){
        webDriverWrapper.quit();
    }

    public IWebDriverWrapper getWebDriverWrapper() {
        return webDriverWrapper;
    }

    @Test(enabled=false)
    public void uploadDCM(BasePage basePage, String uploadFileName) {
        MeshViewPage meshViewPage=(MeshViewPage)basePage;
        if(meshViewPage.fileUploadDlgExist()){
            //String uploadFileName="652abe30-4de1-4c44-9ff4-a743617ac5c1_Orthodontics.dcm";
            String uploadFilePath= IProp.TEST_SOURCE_MESHVIEWER+uploadFileName;
            Reporter.log("upload file "+uploadFileName+ " from "+IProp.TEST_SOURCE_MESHVIEWER);
            ((MeshViewPage)basePage).uploadFile(uploadFilePath);
            getWebDriverWrapper().takeScreenshot(Thread.currentThread().getStackTrace()[1].getMethodName());
        }else{
            Reporter.log("already uploaded file "+uploadFileName+ " from "+IProp.TEST_SOURCE_MESHVIEWER);
        }
    }

    @Test(enabled = false)
    public void embededScreenShot(String screenName){
        Reporter.log("take screenshot");
        String screenshotPath=getWebDriverWrapper().takeScreenshot(screenName);
        Reporter.log("screenshot at "+screenshotPath);
        //Reporter.log(Helper.getTestReportStyle("<img src=\"" + screenshotPath + "\" width=\"400\" height=\"300\"/>","screenshot is "+screenName));
    }

}
