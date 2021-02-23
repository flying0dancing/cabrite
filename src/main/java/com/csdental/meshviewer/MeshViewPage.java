package com.csdental.meshviewer;

import com.csdental.web.IWebDriverWrapper;
import com.csdental.web.IWebElementWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MeshViewPage extends BasePage{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private IWebDriverWrapper webDriverWrapper;
    String title;

    public MeshViewPage(IWebDriverWrapper webDriverWrapper){
        super(webDriverWrapper);
        this.webDriverWrapper=webDriverWrapper;
        title=webDriverWrapper.getTitle();
    }

    public Boolean isThePage(){
        waitThat(3000);
        logger.info("page title is {}",title);
        return true;
    }


    public DisplayPage clickDisplay() throws Exception {
        IWebElementWrapper elt=element("leftbar.display");
        elt.click();
        return new DisplayPage(webDriverWrapper);
    }

    public MeshViewPage clickOrientationAdjustment() throws Exception {
        IWebElementWrapper elt=element("leftbar.orientationAdjustment");
        elt.click();
        return this;
    }

    public GalleryPage clickGallery() throws Exception {
        IWebElementWrapper elt=element("leftbar.gallery");
        elt.click();
        return new GalleryPage(webDriverWrapper);
    }

    /**
     * check top bar is displayed, if one button of top bar is missing return false.
     * @return
     * @throws Exception
     */
    public Boolean isDisplayedTopBar() throws Exception {
        String[] items=new String[]{"Front","Back","Top","Bottom","Zoom Fit"};
        return isDisplayed("topbar.btn", items);
    }
    public boolean isDisplayedLeftBar() throws Exception {
        String[] items=new String[]{"Display","Orientation Adjustment","Gallery"};
        return isDisplayed("leftbar.btn", items);
    }

    public Boolean isDisplayed(String element, String[] items) throws Exception {
        Boolean flag=true;
        for (String item:items) {
            IWebElementWrapper elt=element(element,item);
            if(elt.isDisplayed() && elt.isEnabled()){
                logger.info("button {} on top bar is enabled.",item);
            }else {
                logger.info("button {} on top bar isn't enabled.",item);
                flag=false;
            }
        }
        return flag;
    }
    public Boolean isPresentedTopBar() throws Exception {
        Boolean flag=true;
        String[] items=new String[]{"Front","Back","Top","Bottom","Zoom Fit"};
        for (String item:items) {
            IWebElementWrapper elt=element("topbar.btn",item);
            if(elt.isPresent()){
                logger.info("button {} on top bar is present.",item);
            }else {
                logger.info("button {} on top bar isn't present.",item);
                flag=false;
            }
        }
        return flag;
    }


    public Boolean clickSnapshot() throws Exception {
        Boolean flag=false;
        String item="Snapshot";
        IWebElementWrapper elt=element("topbar.btn",item);
        if(elt.isDisplayed() && elt.isEnabled()){
            elt.click();
            flag=true;
        }
        return flag;
    }


    public void count4MuiIconButton_label() throws Exception {
        IWebElementWrapper elt=element("MuiIconButton-label");
        List<IWebElementWrapper> elts=elt.findElements();
        System.out.println(elts.size());
    }
    public Boolean isDisplayedOcclusionProximity() throws Exception {
        IWebElementWrapper elt=element("leftbar.occlusionProximity");
        if(elt.isDisplayed() && elt.isEnabled()){
            return true;
        }
        return false;
    }

    public void uploadFile(String filefullname) throws Exception {
        String js="document.getElementsByTagName('input')[0].removeAttribute('hidden');";
        executeScript(js);
        IWebElementWrapper elt=element("upload");
        elt.sendKeys(filefullname);
        loading(3000);
        logger.info("upload:"+filefullname);
    }

    public Boolean fileUploaded() throws Exception {
        if(isDisplayedOcclusionProximity()){
            return true;
        }
        return false;
    }

    public void moveMesh() throws Exception {
        element("canvas.it").canvas(0,0,200,200);
    }

}
