package com.csdental.meshviewer;

import com.csdental.util.IProp;
import com.csdental.web.IWebDriverWrapper;
import com.csdental.web.IWebElementWrapper;
import com.csdental.web.LocatorFactory;
import com.csdental.web.pojo.Locator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BasePage {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private IWebDriverWrapper webDriverWrapper;
    private LocatorFactory locatorFactory;

    public BasePage(IWebDriverWrapper webDriverWrapper){
        this.webDriverWrapper=webDriverWrapper;
        this.locatorFactory=locators();
    }

    public IWebElementWrapper element(String id, String... replacements) throws Exception {
        Locator locator=locatorFactory.getLocatorById( id,replacements);
        return webDriverWrapper.element(locator);
    }

    public void waitThat(long milliseconds){
        try {
            webDriverWrapper.timeout(milliseconds).waitThat();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        return new LocatorFactory(this.getClass().getClassLoader().getResourceAsStream(IProp.LOCATORS_MESHVIEWER));
    }
}
