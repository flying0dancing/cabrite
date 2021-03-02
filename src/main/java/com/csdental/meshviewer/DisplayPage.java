package com.csdental.meshviewer;

import com.csdental.web.IWebDriverWrapper;
import com.csdental.web.IWebElementWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisplayPage extends BasePage{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public DisplayPage(IWebDriverWrapper webDriverWrapper) {
        super(webDriverWrapper);
    }
    public Boolean isThePage() throws Exception {
        waitThat();
        IWebElementWrapper elt=element("display.title");
        String title=elt.getText();
        if(title.equals("Gallery")){
            logger.info("page title is {}",title);
            return true;
        }
        return false;
    }

}
