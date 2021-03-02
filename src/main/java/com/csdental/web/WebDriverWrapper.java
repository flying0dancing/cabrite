package com.csdental.web;

import com.csdental.util.BuildStatus;
import com.csdental.util.FileUtil;
import com.csdental.util.IProp;
import com.csdental.web.pojo.Locator;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class WebDriverWrapper implements IWebDriverWrapper{
    private static final Logger logger = LoggerFactory.getLogger(WebDriverWrapper.class);
    private final long timeOutInSeconds=30;//seconds
    private final long sleepInMillis=3000;//mil

    private final WebDriver driver;
    private Wait<WebDriver> wait;
    private JavascriptExecutor js;
    private TakesScreenshot takesScreenshot;


    public WebDriverWrapper(WebDriver webDriver){
        driver=webDriver;
        driver.manage().window().maximize();
        setWaitJs();
    }

    public void setWaitJs(){
        wait=new WebDriverWait(driver, timeOutInSeconds, sleepInMillis)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(UnreachableBrowserException.class)
                .ignoring(InvalidElementStateException.class);
        js= (JavascriptExecutor) driver;
        takesScreenshot=(TakesScreenshot) driver;
    }



    public WebDriver getDriver() {
        return driver;
    }

    public Wait<WebDriver> getWait() {
        return wait;
    }

    public JavascriptExecutor getJs() {
        return js;
    }

    public void get(String url) {
        driver.get(url);
    }
    public String getTitle(){
        return driver.getTitle();
    }
    public void maximize(){
        logger.info("maximize with title {}",getTitle());
        driver.manage().window().maximize();
    }
    public void quit(){
        driver.quit();
    }
    public void close(){
        logger.info("close tab with title {}",getTitle());
        driver.close();
    }


    public void waitThat() {
        wait.until(new ExpectedCondition<Boolean>() {
            String currentValue=null;
            @Override
            public String toString() {
                return String.format("wait document to be completed while current value is %s",currentValue);
            }

            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                try {
                    currentValue=(String)executeScript("return document.readyState");
                    logger.debug("Wait document to be ready. The document status document.readyState={}",currentValue);
                    return "complete".equalsIgnoreCase(currentValue) || "interactive".equalsIgnoreCase(currentValue);
                } catch (WebDriverException e) {
                    BuildStatus.getInstance().recordError();
                    logger.warn("javascript error while waiting document ready");
                    logger.error(e.getMessage(),e);
                    return false;
                }  catch (NullPointerException e) {
                    BuildStatus.getInstance().recordError();
                    logger.error("javascript error while waiting document ready, there might be a alert pops");
                    return true;
                }
            }
        });
    }

    public boolean isJQuerySupported() {
        try {
            return (boolean) executeScript("return jQuery()!=null");
        } catch (WebDriverException | NullPointerException e) {
            return false;
        }
    }
    private void injectJQuery() {
        executeScript(" var headID = "
                + "document.getElementsByTagName(\"head\")[0];"
                + "var newScript = document.createElement('script');"
                + "newScript.type = 'text/javascript';" + "newScript.src = "
                + "'http://ajax.googleapis.com/ajax/"
                + "libs/jquery/1.11.1/jquery.min.js';"
                + "headID.appendChild(newScript);");
    }

    public void waitToBeInactive() {
        if(isJQuerySupported()){
            wait.until(new ExpectedCondition<Boolean>() {
                private Long currentValue=0L;

                @Override
                public String toString() {
                    return String.format("wait jQuery inactive to be 0 while current value is %d ",currentValue);
                }

                @NullableDecl
                @Override
                public Boolean apply(@NullableDecl WebDriver webDriver) {
                    try {
                        currentValue=(Long)executeScript("return jQuery.active");
                        logger.debug("Wait JQuery to be inactive. The JQuery status jQuery.active={}",currentValue);
                        return 0L == currentValue;
                    } catch (WebDriverException e) {
                        logger.warn("javascript error while waiting jQuery inactive");
                        BuildStatus.getInstance().recordError();
                        return false;
                    } catch (NullPointerException e) {
                        logger.warn("javascript error while waiting jQuery inactive, there might be a alert pops");
                        BuildStatus.getInstance().recordError();
                        return true;
                    }
                }
            });
        }
    }

    public WebDriverWrapper timeout(long milliseconds) throws InterruptedException {
        logger.debug("wait {} milliseconds", milliseconds);
        Thread.sleep(milliseconds);
        return this;
    }
    public String takeScreenshot(){
        String filename=IProp.SCREENSHOT_FOLDER+"screenshot.png";
        return takeScreenshotAs(filename);
    }
    public String takeScreenshot(String name){
        String filename=IProp.SCREENSHOT_FOLDER+name+IProp.SCREENSHOT_TYPE;
        return takeScreenshotAs(filename);
    }

    private String takeScreenshotAs(String filename){
        File scrFile = null;
        try {
            scrFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            File destFile=FileUtil.createNewFile(filename);
            logger.debug("screenshot saved:"+destFile.getPath());
            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException e) {
            BuildStatus.getInstance().recordError();
            logger.error(e.getMessage(),e);
        }
        return scrFile.isFile()?scrFile.getPath():null;
    }


    /**
     * create a web element
     * @param locator
     * @return
     */
    public IWebElementWrapper element(Locator locator){
        return new WebElementWrapper(this,locator);
    }
    public IWebElementWrapper element(WebElement webElement){
        return new WebElementWrapper(this,webElement);
    }
    public IWebSelectWrapper select(Locator locator){return new WebSelectWrapper(this,locator);}
    public Object executeScript(final String script, final Object... args){
        return js.executeScript(script,args);
    }

    public Object executeAsyncScript(final String script, final Object... args){
        return js.executeAsyncScript(script, args);
    }
    public String getBrowserName(){
        Capabilities capabilities=((RemoteWebDriver)driver).getCapabilities();
        logger.debug("current browser is "+capabilities.getBrowserName());
        return capabilities.getBrowserName();
    }

    public void getConsoleInfo(){
        if(getBrowserName().equals("chrome")){
            LogEntries logEntries= ((RemoteWebDriver)driver).manage().logs().get(LogType.BROWSER);
            for(LogEntry entry:logEntries){
                logger.info("chrome.console== {} {}",entry.getLevel(),entry.getMessage());
            }
        /*try {
            submitPerformanceResult(logEntries.getAll());
        } catch (IOException e) {
            BuildStatus.getInstance().recordError();
            logger.error(e.getMessage(),e);
        }*/
        }
    }
    public void submitPerformanceResult(List<LogEntry> perfLogEntries)
            throws IOException, JSONException {
        JSONArray devToolsLog = new JSONArray();
        System.out.println(perfLogEntries.size() + " performance log entries found");
        for (LogEntry entry : perfLogEntries) {
            JSONObject message = new JSONObject(entry.getMessage());
            JSONObject devToolsMessage = message.getJSONObject("message");
             System.out.println(
                 devToolsMessage.getString("method") + " " + message.getString("webview"));
            devToolsLog.put(devToolsMessage);
        }
        /*byte[] screenshot = null;
        if (null == androidPackage) {  // Chrome on Android does not yet support screenshots
            screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        String resultUrl = new WebPageTest(new URL("http://localhost:8888/"), "Test", name)
                .submitResult(devToolsLog, screenshot);
        System.out.println("Result page: " + resultUrl);*/
    }
}
