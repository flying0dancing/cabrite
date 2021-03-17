package com.csdental.web;

import com.csdental.web.pojo.Locator;
import org.openqa.selenium.WebElement;

public interface IWebDriverWrapper {
    void get(String url);
    String getTitle();
    void maximize();
    void close();
    void quit();

    /**
     * take screen shot, store at path.screenshot.folder, default name is screenshot.png
     */
    String takeScreenshot();

    /**
     * take screen shot, store at path.screenshot.folder
     * @param filename
     */
    String takeScreenshot(String filename);
    String takeScreenshotAs(String filename);
    WebDriverWrapper timeout(long milliseconds) throws InterruptedException;
    void waitThat();
    void waitToBeInactive();
    IWebElementWrapper element(Locator locator);
    IWebElementWrapper element(WebElement webElement);
    Object executeScript(final String script, final Object... args);
    Object executeAsyncScript(final String script, final Object... args);
    void getConsoleInfo();

}
