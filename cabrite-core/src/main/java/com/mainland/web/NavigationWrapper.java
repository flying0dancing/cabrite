package com.mainland.web;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class NavigationWrapper implements INavigationWrapper{
    private final Logger logger = LoggerFactory.getLogger(NavigationWrapper.class);
    private IWebDriverWrapper webDriverWrapper;
    private WebDriver driver;

    public  NavigationWrapper(WebDriverWrapper webDriverWrapper) {
        this.webDriverWrapper= webDriverWrapper;
        this.driver= webDriverWrapper.getDriver();
    }

    @Override
    public INavigationWrapper back() {
        logger.info("navigating back");
        driver.navigate().back();
        return this;
    }

    @Override
    public INavigationWrapper forward() {
        logger.info("navigating forward");
        driver.navigate().forward();
        return this;
    }

    @Override
    public INavigationWrapper to(String url) {
        logger.info("navigating to "+url);
        driver.navigate().to(url);
        return this;
    }

    @Override
    public INavigationWrapper to(URL url) {
        logger.info("navigating to "+url.toString());
        driver.navigate().to(url);
        return this;
    }

    @Override
    public INavigationWrapper refresh() {
        logger.info("navigating refresh");
        driver.navigate().refresh();
        return this;
    }
}
