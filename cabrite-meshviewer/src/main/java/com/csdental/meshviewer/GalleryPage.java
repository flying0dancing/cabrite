package com.csdental.meshviewer;

import com.mainland.web.IWebDriverWrapper;
import com.mainland.web.IWebElementWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GalleryPage extends BasePage{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public GalleryPage(IWebDriverWrapper webDriverWrapper) {
        super(webDriverWrapper);
    }

    public Boolean isThePage(){
        waitThat();
        IWebElementWrapper elt=element("gallery.title");
        String title=elt.getText();
        if(title.equals("Gallery")){
            logger.info("page title is {}",title);
            return true;
        }
        return false;
    }

    public Integer countOfSnapshot() {
        IWebElementWrapper elt=element("gallery.delete");
        if(elt.isPresent()){
            List<IWebElementWrapper> elts=elt.findElements();
            return elts.size();
        }else {
            return 0;
        }
    }

}
