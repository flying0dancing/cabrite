package com.csdental.meshviewer;

import com.mainland.web.IWebElementWrapper;

public interface IBasePage extends IPage{

    IWebElementWrapper element(String id, String... replacements);
    Boolean isDisplayed(String element, String... items);
    Boolean isEnabled(String element, String... items);
    Boolean isPresent(String element, String... items);
    void waitThat();
    void waitThat(long milliseconds);
    void loading();
    void loading(long milliseconds);
}
