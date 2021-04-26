package com.csdental.web;

import com.csdental.util.IProp;
import com.csdental.util.Strs;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;

public class WebDriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);

    public IWebDriverWrapper wrapWebDriver(String browser){
        IWebDriverWrapper webDriverWrapper=null;
        if(!Strs.isEmpty(browser)){
            switch(browser.toLowerCase()){
                case "edge":logger.info("loading edge driver......");
                    webDriverWrapper=createEdgeDriver();
                    break;
                case "firefox":logger.info("loading firefox driver......");
                    webDriverWrapper=createFirefoxDriver();
                    break;
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
        //setLogCapabilitiesChrome(capabilities);
        return capabilities;
    }
    private DesiredCapabilities setLogCapabilitiesChrome(DesiredCapabilities capabilities){
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        logPrefs.enable(LogType.CLIENT, Level.ALL);
        logPrefs.enable(LogType.DRIVER, Level.ALL);
        logPrefs.enable(LogType.PROFILER, Level.ALL);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS,logPrefs);
        return capabilities;
    }

    private IWebDriverWrapper createFirefoxDriver(){
        System.setProperty("webdriver.gecko.driver",IProp.WEBDRIVER_FIREFOX);
        FirefoxOptions options=new FirefoxOptions();
        options.setBinary(new FirefoxBinary());
        FirefoxProfile profile=new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);
        profile.setPreference("devtools.console.stdout.content", true);
        profile.setPreference("dom.successive_dialog_time_limit", 0);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/plain,text/xml,text/csv,image/jpeg,application/zip,application/vnd.ms-excel,application/pdf,application/xml");
        options.setProfile(profile);
        //options.merge(setCapabilitiesFirefox());
        return new WebDriverWrapper(new FirefoxDriver(options));
    }
    private DesiredCapabilities setCapabilitiesFirefox(){
        DesiredCapabilities capabilities=DesiredCapabilities.firefox();
        capabilities.setCapability(CapabilityType.BROWSER_VERSION,"0.5");
        //capabilities.setCapability("marionette",false);
        return capabilities;
    }
    private void setFirefoxProfile(){
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("devtools.console.stdout.content", true);
    }

    private IWebDriverWrapper createEdgeDriver(){

        System.setProperty("webdriver.edge.driver",IProp.WEBDRIVER_EDGE_X86);
        EdgeOptions options=new EdgeOptions();
        return new WebDriverWrapper(new EdgeDriver(options));
    }
}
