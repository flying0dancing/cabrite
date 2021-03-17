package com.csdental.meshviewer;

import com.csdental.web.IWebDriverWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageInformation extends BasePage{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public ImageInformation(IWebDriverWrapper webDriverWrapper){
        super(webDriverWrapper);
    }
}
