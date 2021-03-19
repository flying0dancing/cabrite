package com.csdental.meshviewer;

import com.csdental.test.IComFolder;
import com.csdental.util.IProp;
import com.csdental.web.IWebDriverWrapper;
import com.csdental.web.WebDriverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import com.csdental.test.Reporter;
import org.testng.annotations.*;

@Test(groups={"smoke","function_test"})
public class TestManager {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    IWebDriverWrapper webDriverWrapper;
    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeclass(ITestContext context, @Optional("chrome") String browser, @Optional("http://localhost:3001/") String url){
        webDriverWrapper=new WebDriverFactory().wrapWebDriver(browser);
        webDriverWrapper.get(url);
        logger.info("launch website url:{}",url);
        Reporter.log("launch website url:"+url);
        embededScreenShot("getUrl");
    }

    @AfterClass
    public void afterclass(){
        logger.info("close website");
        Reporter.log("close website");
        webDriverWrapper.close();
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
            String uploadFilePath= IComFolder.SOURCE_FOLDER +uploadFileName;
            Reporter.log("upload file "+uploadFileName+ " from "+IComFolder.SOURCE_FOLDER);
            ((MeshViewPage)basePage).uploadFile(uploadFilePath);
            embededScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName());
        }else{
            Reporter.log("already uploaded file "+uploadFileName+ " from "+IComFolder.SOURCE_FOLDER);
        }
    }

    @Test(enabled = false)
    public void embededScreenShot(String screenName){
        Reporter.log("take screenshot");
        String screenshotPath=getWebDriverWrapper().takeScreenshotAs(IComFolder.SCREENSHOT_FOLDER+screenName+ IProp.SCREENSHOT_TYPE);
        logger.info("screenshot at {}",screenshotPath);
        Reporter.log("screenshot at "+screenshotPath);
        //Reporter.log(Helper.getTestReportStyle("<img src=\"" + screenshotPath + "\" width=\"400\" height=\"300\"/>","screenshot is "+screenName));
    }

}
