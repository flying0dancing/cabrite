package com.csdental.meshviewer;

import com.mainland.test.BaseTest;
import com.csdental.test.IComFolder;
import com.mainland.util.IProp;
import com.mainland.util.ImageUtil;
import com.mainland.util.Strs;
import com.mainland.web.IWebDriverWrapper;
import com.mainland.web.WebDriverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import com.csdental.test.Reporter;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;

import java.io.IOException;

import static com.mainland.util.FileUtil.copyDirectory;


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
        copyDirectory(IComFolder.SOURCE_EXPECTATION_FOLDER,IComFolder.RESULT_ACTUAL_FOLDER);

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
            copyDirectory(IComFolder.RESULT_ACTUAL_FOLDER,reportFolder);
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


   /* public String embededScreenShot(String caseFolder,String screenName,String msg){
        Reporter.log(msg);
        String screenshotPath=getWebDriverWrapper().takeScreenshotAs(IComFolder.SCREENSHOT_FOLDER+caseFolder+screenName+ IProp.SCREENSHOT_TYPE);
        String path=Strs.briefPath(screenshotPath,IComFolder.SCREENSHOT_FOLDER_NAME);
        logger.info("take screenshot, screenshot at {}",path);
        Reporter.log("take screenshot, screenshot at "+path);
        return screenshotPath;
    }*/


    public String embededScreenShot(String screenName,String msg){
        Reporter.log(msg);
        String screenshotPath=getWebDriverWrapper().takeScreenshotAs(IComFolder.SCREENSHOT_FOLDER+screenName+ IProp.SCREENSHOT_TYPE);
        String path=Strs.briefPath(screenshotPath,IComFolder.SCREENSHOT_FOLDER_NAME);
        logger.info("take screenshot, screenshot at {}",path);
        Reporter.log("take screenshot, screenshot at "+path);
        //Reporter.log(Helper.getTestReportStyle(Strs.convertFilePath(path),"screenshot is "+screenName));
        return screenshotPath;
    }
    public String embededScreenShot(String screenName){
        String screenshotPath=getWebDriverWrapper().takeScreenshotAs(IComFolder.SCREENSHOT_FOLDER+screenName+ IProp.SCREENSHOT_TYPE);
        String path=Strs.briefPath(screenshotPath,IComFolder.SCREENSHOT_FOLDER_NAME);
        logger.info("take screenshot, screenshot at {}",path);
        Reporter.log("take screenshot, screenshot at "+path);
        //Reporter.log(Helper.getTestReportStyle(Strs.convertFilePath(path),"screenshot is "+screenName));
        return screenshotPath;
    }

    public void embededComparedScreen(String logPrefix,String screenshotPath){
        String path=Strs.briefPath(screenshotPath,IComFolder.RESULT_ACTUAL_FOLDER_NAME);
        logger.info(logPrefix+path);
        Reporter.log(logPrefix+path);
        return ;
    }

    /**
     * compare higher with expectedFile, put result in resultFolder.
     * get expected tolerance by compared lower with middle, middle with higher;
     * @param lower
     * @param middle
     * @param higher
     * @param resultFolder
     * @param expectedFile
     */
    public void embededCompareResult(String lower,String middle, String higher, String resultFolder, String expectedFile){
        //using is embededCompareResult(lower,middle,higher, IComFolder.RESULT_ACTUAL_FOLDER +caseFolder, IComFolder.SOURCE_EXPECTATION_FOLDER+expectationFile)
        double tolerance= ImageUtil.getTolerance(lower,middle,higher, resultFolder);
        Reporter.log(String.format("expected similar percent is  %.6f", tolerance));
        String[] compareResult=ImageUtil.compareWithExpectation(higher,expectedFile,resultFolder);
        double actual_tolerance= Double.parseDouble(compareResult[0]);
        embededComparedScreen("expected result(before compared) is ",Strs.reviseFilePath(expectedFile));
        embededComparedScreen("expected result(after compared) is ",compareResult[2]);
        embededComparedScreen("actual result(after compared) is ",compareResult[1]);
        Reporter.log(String.format("actual similar percent is  %.6f", actual_tolerance));
        Assert.assertTrue(actual_tolerance>=tolerance,"actual tolerance should smaller than expectation.");
    }

}
