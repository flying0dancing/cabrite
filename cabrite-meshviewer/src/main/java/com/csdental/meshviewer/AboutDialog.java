package com.csdental.meshviewer;

import com.mainland.web.IWebDriverWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AboutDialog extends BasePage{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public AboutDialog(IWebDriverWrapper webDriverWrapper) {
        super(webDriverWrapper);
    }
}
