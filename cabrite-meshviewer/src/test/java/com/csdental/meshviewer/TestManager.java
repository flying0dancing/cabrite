package com.csdental.meshviewer;

import com.csdental.test.BaseTest;
import com.csdental.test.IComFolder;
import com.csdental.util.IProp;
import com.csdental.util.Strs;
import com.csdental.web.IWebDriverWrapper;
import com.csdental.web.WebDriverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import com.csdental.test.Reporter;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;

import java.io.IOException;

import static com.csdental.util.FileUtil.copyDirectory;


public class TestManager extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private IWebDriverWrapper webDriverWrapper;

    @Parameters({"browser","url","importFile","expectationFile"})
    @BeforeClass(groups={"smoke","function_test"})
    public void beforeclass(ITestContext context, @Optional("chrome") String browser, @Optional("http://localhost:3000/") String url, @Optional("http://localhost:3000/") String importFile, @Optional("http://localhost:3000/") String expectationFile){
        webDriverWrapper=new WebDriverFactory().wrapWebDriver(browser);
        webDriverWrapper.get(url);
        logger.info("launch website url:{}",url);
        Reporter.log("launch website url:"+url);
        embededScreenShot("getUrl");
    }

    @AfterClass(groups={"smoke","function_test"})
    public void afterclass(){
        logger.info("close website");
        Reporter.log("close website");
        //webDriverWrapper.close();
        webDriverWrapper.quit();
    }

    @BeforeMethod(groups={"smoke","function_test"})
    public void beforeMethod(){
        //Reporter.testStart();
    }
    @AfterMethod(groups={"smoke","function_test"})
    public void afterMethod(){
        //Reporter.testEnd();
    }
    @BeforeSuite
    public void beforeSuite(ITestContext context){
    }
    @AfterSuite
    public void afterSuite(XmlTest xmlTest, ITestContext context) throws IOException {
        //copy screenshots folder to reportng-reports folder
        String reportFolder=System.getProperty("org.uncommons.reportng.reportsDirectory");
        if(reportFolder==null){
            reportFolder=IComFolder.REPORT_FOLDER+"html";
        }else {
            reportFolder=reportFolder+System.getProperty("file.separator")+"html";
            logger.info(reportFolder);
            copyDirectory(IComFolder.SCREENSHOT_FOLDER,reportFolder);
        }

    }
    public IWebDriverWrapper getWebDriverWrapper() {
        return webDriverWrapper;
    }


    public void uploadDCM(BasePage basePage, String uploadFileName) {
        MeshViewPage meshViewPage=(MeshViewPage)basePage;
        if(meshViewPage.fileUploadDlgExist()){
            //String uploadFileName="652abe30-4de1-4c44-9ff4-a743617ac5c1_Orthodontics.dcm";
            String uploadFilePath= IComFolder.SOURCE_FOLDER + uploadFileName;
            Reporter.log("upload file "+uploadFileName+ " from "+IComFolder.SOURCE_FOLDER);
            ((MeshViewPage)basePage).uploadFile(Strs.reviseFilePath(uploadFilePath));
            embededScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName());
        }else{
            Reporter.log("already uploaded file "+uploadFileName+ " from "+IComFolder.SOURCE_FOLDER);
        }
    }


    public String embededScreenShot(String caseFolder,String screenName){
        String screenshotPath=getWebDriverWrapper().takeScreenshotAs(IComFolder.SCREENSHOT_FOLDER+caseFolder+screenName+ IProp.SCREENSHOT_TYPE);
        Integer getScreenShotsIndex=screenshotPath.lastIndexOf(System.getProperty("file.separator")+IComFolder.SCREENSHOT_FOLDER_NAME+System.getProperty("file.separator"));
        String path=".."+System.getProperty("file.separator")+".."+screenshotPath.substring(getScreenShotsIndex);
        logger.info("take screenshot, screenshot at {}",path);
        Reporter.log("take screenshot, screenshot at "+path);
        //Reporter.log(Helper.getTestReportStyle(Strs.convertFilePath(path),"screenshot is "+screenName));
        return screenshotPath;
    }


    public String embededScreenShot(String screenName){
        String screenshotPath=getWebDriverWrapper().takeScreenshotAs(IComFolder.SCREENSHOT_FOLDER+screenName+ IProp.SCREENSHOT_TYPE);
        Integer getScreenShotsIndex=screenshotPath.lastIndexOf(System.getProperty("file.separator")+IComFolder.SCREENSHOT_FOLDER_NAME+System.getProperty("file.separator"));
        String path=".."+System.getProperty("file.separator")+".."+screenshotPath.substring(getScreenShotsIndex);
        logger.info("take screenshot, screenshot at {}",path);
        Reporter.log("take screenshot, screenshot at "+path);
        //Reporter.log(Helper.getTestReportStyle(Strs.convertFilePath(path),"screenshot is "+screenName));
        return screenshotPath;
    }

}
