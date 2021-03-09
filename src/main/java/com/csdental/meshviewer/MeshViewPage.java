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
        waitThat();
        logger.info("page title is {}",title);
        return true;
    }


    public DisplayPage clickDisplay() throws Exception {
        IWebElementWrapper elt=element("leftbar.display");
        elt.click();
        return new DisplayPage(webDriverWrapper);
    }

    public OrientationAdjustment clickOrientationAdjustment() throws Exception {
        logger.info("click Orientation Adjustment tab on left bar");
        IWebElementWrapper elt=element("leftbar.orientationAdjustment");
        elt.click();
        loading();
        OrientationAdjustment orientationAdjustment=new OrientationAdjustment(webDriverWrapper);
        if(!orientationAdjustment.isThePage()){
            elt.click();
            loading();
        }
        return orientationAdjustment;
    }

    public GalleryPage clickGallery() throws Exception {
        IWebElementWrapper elt=element("leftbar.gallery");
        elt.click();
        return new GalleryPage(webDriverWrapper);
    }

    /**
     * check top bar is displayed, if one button of top bar is missing return false.
     * default displayed buttons are {"Front","Back","Top","Bottom","Right","Left","Zoom Fit","Snapshot","True Color","Light","Reset"};
     * @return
     * @throws Exception
     */
    public Boolean isDisplayedTopBar() throws Exception {
        String[] items=new String[]{"Front","Back","Top","Bottom","Right","Left","Zoom Fit","Snapshot","True Color","Light","Reset"};
        //String[] items=new String[]{"Front","Back","Top","Bottom","Right","Left","Zoom Fit","Snapshot","True Color","Reset"};
        return isDisplayed("topbar.btn", items);
    }

    /**
     * check top bar is displayed, if one button of top bar provided is missing return false.
     * @param title value in {"Front","Back","Top","Bottom","Right","Left","Zoom Fit","Snapshot","True Color","Light","Reset"}
     * @return
     * @throws Exception
     */
    public Boolean isDisplayedTopBar(String... title) throws Exception {
        return isDisplayed("topbar.btn", title);
    }

    public Boolean isPresentedTopBar() throws Exception {
        String[] items=new String[]{"Front","Back","Top","Bottom","Right","Left","Zoom Fit","Snapshot","True Color","Light","Reset"};
        return isPresent("topbar.btn", items);
    }

    /**
     * check top bar is presented, if one button of top bar provided is missing return false.
     * @param title value in {"Front","Back","Top","Bottom","Right","Left","Zoom Fit","Snapshot","True Color","Light","Reset"}
     * @return
     * @throws Exception
     */
    public Boolean isPresentedTopBar(String... title) throws Exception {
        return isPresent("topbar.btn", title);
    }

    /**
     * check top bar is enabled, if one button of top bar is missing return false.
     * default displayed buttons are {"Front","Back","Top","Bottom","Right","Left","Zoom Fit","Snapshot","True Color","Reset"};
     * "Light" button is disabled not include in this function.
     * @return
     * @throws Exception
     */
    public Boolean isEnabledTopBar() throws Exception {
        String[] items=new String[]{"Front","Back","Top","Bottom","Right","Left","Zoom Fit","Snapshot","True Color","Reset"};
        return isEnabled("topbar.btn", items);
    }
    /**
     * check top bar is enabled, if one button of top bar is missing return false.
     * default displayed buttons are {"Front","Back","Top","Bottom","Right","Left","Zoom Fit","Snapshot","True Color","Light","Reset"};
     * "Light" button is disabled not include in this function.
     * @return
     * @throws Exception
     */
    public Boolean isEnabledTopBar(String... title) throws Exception {
        return isEnabled("topbar.btn", title);
    }
    
    /**
     * check left bar is displayed, if one button of bar is missing return false.
     * default displayed buttons are {"Display","Orientation Adjustment","Gallery"};
     * @return
     * @throws Exception
     */
    public boolean isDisplayedLeftBar() throws Exception {
        String[] items=new String[]{"Display","Orientation Adjustment","Gallery"};
        return isDisplayed("leftbar.btn", items);
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
        logger.info("upload file {}",filefullname);
        String js="document.getElementsByTagName('input')[0].removeAttribute('hidden');";
        executeScript(js);
        IWebElementWrapper elt=element("upload");
        elt.sendKeys(filefullname);
        loading();
    }

    public Boolean fileUploaded() throws Exception {
        return isDisplayedOcclusionProximity();
    }

    public void moveMesh() throws Exception {
        logger.info("move mesh from (0,0) to (200,200)");
        element("canvas.it").canvas_move(0,0,200,200);
    }

    public Boolean fileUploadDlgExist() throws Exception {
        IWebElementWrapper elt=element("uploadDlg");
        if(elt.isPresent()){
            return true;
        }
        return false;
    }
}
