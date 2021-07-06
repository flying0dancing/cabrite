package com.mainland.web;

import com.mainland.util.IProp;
import com.mainland.util.Strs;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
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
                    webDriverWrapper=createChromeDriver();
            }
        }
        return webDriverWrapper;
    }

    private IWebDriverWrapper createChromeDriver(){
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, IProp.WEBDRIVER_CHROME);
        return new WebDriverWrapper(new ChromeDriver(setChromeOptions())) ;
    }
    private ChromeOptions setChromeOptions(){
        String downloadFolder="D:\\";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.setCapability(CapabilityType.BROWSER_VERSION,"0.5");
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
        HashMap<String, Object> prefs=new HashMap<>();
        //prefs.put("prompt_for_download",false);//download prompts disabled
        prefs.put("profile.default_content_settings.popups",0);//download prompts disabled
        prefs.put("download.default_directory",downloadFolder);//set download directory
        options.setExperimentalOption("prefs",prefs);
        /*Proxy proxy=new Proxy();
        proxy.setHttpProxy("localhost:3000");
        options.setCapability("proxy",proxy);*/
        options.setAcceptInsecureCerts(true);
        //options.setCapability(CapabilityType.LOGGING_PREFS,setLogCapabilitiesChrome(options));
        return options;
    }

    private void setChromeDownloadFolder(){

    }
    private LoggingPreferences setLogCapabilitiesChrome(ChromeOptions options){
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        logPrefs.enable(LogType.CLIENT, Level.ALL);
        logPrefs.enable(LogType.DRIVER, Level.ALL);
        logPrefs.enable(LogType.PROFILER, Level.ALL);
        return logPrefs;
    }

    private IWebDriverWrapper createFirefoxDriver(){
        System.setProperty("webdriver.gecko.driver",IProp.WEBDRIVER_FIREFOX);
        return new WebDriverWrapper(new FirefoxDriver(setFirefoxOptions()));
    }
    private FirefoxOptions setFirefoxOptions(){
        FirefoxOptions options=new FirefoxOptions();
        options.setBinary(new FirefoxBinary());
        //options.setCapability("marionette",false);
        options.setProfile(setFirefoxProfile());
        return options;
    }

    private FirefoxProfile setFirefoxProfile(){
        String downloadFolder="D:\\";
        FirefoxProfile profile = new FirefoxProfile();
        //Proxy Setting
        profile.setAssumeUntrustedCertificateIssuer(false);
        profile.setPreference("network.proxy.type",1);
        profile.setPreference("network.proxy.http","localHost");
        profile.setPreference("network.proxy.http_port",3128);

        //Download Setting
        profile.setPreference("browser.download.folderlist",2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/plain,text/xml,text/csv,image/jpeg,application/zip,application/vnd.ms-excel,application/pdf,application/xml");
        profile.setPreference("browser.download.dir",downloadFolder); //set download directory

        //profile.setPreference("devtools.console.stdout.content", true);
        /*profile.setAcceptUntrustedCertificates(true);
        profile.setPreference("devtools.console.stdout.content", true);
        profile.setPreference("dom.successive_dialog_time_limit", 0);

        profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/plain,text/xml,text/csv,image/jpeg,application/zip,application/vnd.ms-excel,application/pdf,application/xml");*/
        return profile;
    }

    private IWebDriverWrapper createEdgeDriver(){
        System.setProperty("webdriver.edge.driver",IProp.WEBDRIVER_EDGE_X86);
        return new WebDriverWrapper(new EdgeDriver(setEdgeOptions()));
    }
    private EdgeOptions setEdgeOptions(){
        String downloadFolder="D:\\";
        EdgeOptions options=new EdgeOptions();
        /*EdgeDriverService edgeDriverService=EdgeDriverService.createDefaultService();
        HashMap<String,Object> commandParams=new HashMap<>();
        commandParams.put("cmd","Page.setDownloadBehavior");
        HashMap<String,String> params=new HashMap<>();
        params.put("behavior","allow");
        params.put("downloadPath", downloadFolder);
        commandParams.put("params",params);
        ObjectMapper objectMapper=new ObjectMapper();
        HttpClient httpClient= HttpClientBuilder.create().build();
        String command=objectMapper*/

        return options;
    }
}
