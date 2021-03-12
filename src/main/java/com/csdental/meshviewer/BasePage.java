package com.csdental.meshviewer;

import com.csdental.web.IWebDriverWrapper;
import com.csdental.web.IWebElementWrapper;
import com.csdental.web.LocatorFactory;
import com.csdental.web.pojo.Locator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BasePage implements IBasePage{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public IWebDriverWrapper getWebDriverWrapper() {
        return webDriverWrapper;
    }

    private IWebDriverWrapper webDriverWrapper;
    private LocatorFactory locatorFactory;
    private long sleepInMillis=2000;//mil

    public BasePage(IWebDriverWrapper webDriverWrapper){
        this.webDriverWrapper=webDriverWrapper;
        this.locatorFactory=locators();
    }

    public IWebElementWrapper element(String id, String... replacements) {
        Locator locator=locatorFactory.getLocatorById( id,replacements);
        return webDriverWrapper.element(locator);
    }

    /*public IWebSelectWrapper select(String id, String... replacements){
        Locator locator=locatorFactory.getLocatorById( id,replacements);
        return webDriverWrapper.select(locator);
    }*/
    public void waitThat(){
        waitThat(sleepInMillis);
    }
    public void waitThat(long milliseconds){
        try {
            webDriverWrapper.timeout(milliseconds).waitThat();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void loading(){
        loading(sleepInMillis);
    }
    public void loading(long milliseconds){
        try {
            webDriverWrapper.timeout(milliseconds).waitToBeInactive();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Object executeScript(String script,Object... args){
        return webDriverWrapper.executeScript(script, args);
    }

    public LocatorFactory locators(){
        return LocatorFactory.Meshviewer();
    }


    public Boolean isDisplayed(String element, String... items) {
        Boolean flag=true;
        for (String item:items) {
            IWebElementWrapper elt=element(element,item);
            if(elt.isPresent() && elt.isDisplayed()){
                logger.info("button {} on top bar is displayed.",item);
            }else {
                logger.info("button {} on top bar isn't displayed.",item);
                flag=false;
            }
        }
        return flag;
    }

    public Boolean isEnabled(String element, String... items) {
        Boolean flag=true;
        for (String item:items) {
            IWebElementWrapper elt=element(element,item);
            if(elt.isDisplayed() && elt.isEnabled()){
                logger.info("button {} on top bar is enabled.",item);
            }else {
                logger.info("button {} on top bar isn't enabled.",item);
                flag=false;
            }
        }
        return flag;
    }
    public Boolean isPresent(String element, String... items) {
        Boolean flag=true;
        for (String item:items) {
            IWebElementWrapper elt=element(element,item);
            if(elt.isDisplayed() && elt.isEnabled()){
                logger.info("button {} on top bar is present.",item);
            }else {
                logger.info("button {} on top bar isn't present.",item);
                flag=false;
            }
        }
        return flag;
    }

    @Override
    public Boolean isThePage() {
        return false;
    }
}
