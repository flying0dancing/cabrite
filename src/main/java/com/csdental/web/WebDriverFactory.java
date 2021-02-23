package com.csdental.web;

import com.csdental.util.IProp;
import com.csdental.util.Strs;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);
    private String browser;

    public IWebDriverWrapper wrapWebDriver(String browser){
        IWebDriverWrapper webDriverWrapper=null;
        if(!Strs.isEmpty(browser)){
            switch(browser.toLowerCase()){
                case "chrome":logger.info("loading chrome driver......");
                    webDriverWrapper=createChromeDriver();
                    break;
                default:logger.info("loading default driver chrome......");
            }
        }
        return webDriverWrapper;
    }

    private IWebDriverWrapper createChromeDriver(){
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, IProp.WEBDRIVER_CHROME);
        ChromeOptions options = new ChromeOptions();
        return new WebDriverWrapper(new ChromeDriver(options.merge(setCapabilitiesChrome()))) ;
    }
    private DesiredCapabilities setCapabilitiesChrome(){
        DesiredCapabilities capabilities=DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.BROWSER_VERSION,"0.5");
        return capabilities;
    }

}
