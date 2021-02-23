package com.csdental.web;

import com.csdental.util.BuildStatus;
import com.csdental.util.FileUtil;
import com.csdental.util.IProp;
import com.csdental.web.pojo.Locator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebElementWrapper implements IWebElementWrapper {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private WebDriverWrapper webDriverWrapper;
    private WebDriver driver;
    private Wait<WebDriver> wait;
    private Locator locator;
    private WebElement element;
    private By by;

    public WebElementWrapper(WebDriverWrapper webDriverWrapper,Locator locator){
        this.webDriverWrapper=webDriverWrapper;
        this.driver=webDriverWrapper.getDriver();
        this.wait=webDriverWrapper.getWait();
        this.locator=locator;
        this.by=locator.getBy(locator.getId());
        this.element=element();

    }
    protected WebElementWrapper(WebDriverWrapper webDriverWrapper,WebElement webElement){
        this.webDriverWrapper=webDriverWrapper;
        this.driver=webDriverWrapper.getDriver();
        this.wait=webDriverWrapper.getWait();
        this.element=webElement;
        this.locator=null;
        this.by=null;


    }
    private WebElement element(){
        logger.info(locator.toString());
        List<WebElement> elts=driver.findElements(by);
        if(elts==null || elts.size()==0){
            return null;
        }
        return wait.until(driver->{
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        });
    }
    public Boolean isPresent(){
        try {
            driver.findElement(by);
            return true;
        }catch ( Exception e){
            return false;
        }
    }
    @Override
    public Boolean isEnabled(){
        return wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
    }
    @Override
    public Boolean isDisplayed(){
        return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }
    @Override
    public Boolean isSelected(){
        return wait.until(ExpectedConditions.visibilityOf(element)).isSelected();
    }

    private List<WebElement> findElements1(){
        logger.debug(locator.toString());
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }
    @Override
    public List<IWebElementWrapper> findElements(){
        if(locator!=null){
            logger.debug(locator.toString());
            List<IWebElementWrapper> webElementWrappers=new ArrayList<>();
            List<WebElement> webElements=findElements1();
            for(WebElement webElement:webElements){
                webElementWrappers.add(webDriverWrapper.element(webElement));
            }
            return webElementWrappers;
        }
        return null;
    }

    @Override
    public String getAttribute(String var1){
        return element.getAttribute(var1);
    }

    private WebElement waitToBeVisible(By by){
        return wait.until(driver->{
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        });
    }

    public void sendKeys(CharSequence... keyToSend){
        WebElement elt=wait.until(ExpectedConditions.visibilityOf(element));
        if(elt.isDisplayed()){
            elt.clear();
            elt.sendKeys(keyToSend);
            wait.until(ExpectedConditions.invisibilityOf(element));
        }

    }
    public IWebElementWrapper type(final CharSequence... value) {
        logger.info("typing {} on {}", StringUtils.join(value), element);
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(value);
        return this;
    }
    public IWebElementWrapper clear() {
        logger.info("clearing value on " + element);
        wait.until(ExpectedConditions.visibilityOf(element)).clear();
        return this;
    }
    public void click(){
        if(locator!=null){
            logger.info("clicking {}",locator);
        }
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public String getText(){
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    public String takeScreenshot(){
        String filename= IProp.SCREENSHOT_FOLDER+"elementScreenshot.png";
        return takeScreenshotAs(filename);
    }
    public String takeScreenshot(String name){
        String filename=IProp.SCREENSHOT_FOLDER+name;
        return takeScreenshotAs(filename);
    }

    private String takeScreenshotAs(String filename){
        File scrFile = null;
        try {
            scrFile = element.getScreenshotAs(OutputType.FILE);
            File destFile= FileUtil.createNewFile(filename);
            logger.info("element screenshot saved:"+destFile.getPath());
            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException e) {
            BuildStatus.getInstance().recordError();
            logger.error(e.getMessage(),e);
        }
        return scrFile.isFile()?scrFile.getPath():null;
    }

    public void canvas(int xStart, int yStart, int xOffset, int yOffset){
        Actions actions=new Actions(driver);
        actions.moveToElement(element,xStart,yStart).clickAndHold().moveByOffset(xOffset,yOffset).release().build().perform();
        actions.release();

    }
}
