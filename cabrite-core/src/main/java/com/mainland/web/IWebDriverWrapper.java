package com.mainland.web;

import com.mainland.web.pojo.Locator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.SessionId;

public interface IWebDriverWrapper {
    IWebDriverWrapper doPostActions();
    IWebDriverWrapper deleteAllCookies();
    IAlertWrapper alert();
    INavigationWrapper navigation();
    SessionId getSessionId();
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
