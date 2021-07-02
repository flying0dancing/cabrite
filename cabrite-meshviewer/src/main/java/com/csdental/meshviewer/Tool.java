package com.csdental.meshviewer;

import com.csdental.web.IWebDriverWrapper;
import com.csdental.web.IWebElementWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Tool extends BasePage implements ITool {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private IWebDriverWrapper webDriverWrapper;
    private String _topbar ="topbar.btn";
    private String[] _topbar_items =new String[]{"ViewFront","ViewBack","ViewTop","ViewBottom","ViewRight","ViewLeft","ZoomFit","TakeSnapshot","ToggleTrueColor","ToggleLight","Reset"};
    private Map topbarMapping=new HashMap<String,String>(){{put("ViewFront","Front");put("ViewBack","Back");put("ViewBottom","Bottom");put("ViewRight","Right");put("ViewLeft","Left");put("ZoomFit","Zoom Fit");put("TakeSnapshot","Snapshot");put("ToggleTrueColor","True Color");put("ToggleLight","Light");put("Reset","Reset");}};
    private String _leftbar="leftbar.btn";


    public Tool(IWebDriverWrapper webDriverWrapper) {
        super(webDriverWrapper);
        this.webDriverWrapper=webDriverWrapper;
    }

    public enum CanvasToolBar
    {
        FRONT("ViewFront",1), BACK("ViewBack",2), TOP("ViewTop",3), BOTTOM("ViewBottom",4), RIGHT("ViewRight",5), LEFT("ViewLeft",6), ZOOMFIT("ZoomFit",7), TAKESNAPSHOT("TakeSnapshot",8), TRUECOLOR("ToggleTrueColor",9), LIGHT("ToggleLight",10), RESET("Reset",11);
        private int btnPosition;
        private String btnSuffix;
        private CanvasToolBar(String btnText,int btnPosition)
        {
            this.btnSuffix =btnText;
            this.btnPosition =btnPosition;
        }

        public String getBtnSuffix()
        {
            return btnSuffix;
        }
        public int getBtnPosition()
        {
            return btnPosition;
        }
        public void setBtnSuffix(String btnSuffix)
        {
            this.btnSuffix = btnSuffix;
        }
        public void setBtnPosition(int btnPosition)
        {
            this.btnPosition = btnPosition;
        }

    }
    public enum ToolBar
    {
        DISPLAY("Display"), ORIENTATIONADJUSTMENT("OrientationAdjustment"), OCCLUSIONPROXIMITY("OcclusionMapping"),  GALLERY("Gallery"), IMAGEINFORMATION("ImageInfo"), ABOUT("Info"), EXPORT("Export"), HELP("Help");

        private String btnText;
        private ToolBar(String btnText)
        {
            this.btnText =btnText;
        }
        public String getBtnText()
        {
            return btnText;
        }
        public void setBtnText(String btnText)
        {
            this.btnText = btnText;
        }

    }

    @Override
    public String getLanguage() {
        logger.info("get current language");
        //TODO
        return null;
    }

    @Override
    public Boolean changeLanguage(String language) {
        logger.info("click change language on right upper corner");
        IWebElementWrapper elt=element("");
        //TODO
        return null;
    }

    @Override
    public Boolean clickFront() {
        logger.info("click View Front button on top bar");
        Boolean flag=false;
        IWebElementWrapper elt=element(_topbar, CanvasToolBar.FRONT.getBtnSuffix());
        if(elt.isDisplayed() && elt.isEnabled()){
            elt.click();
            flag=true;
        }
        return flag;
    }

    @Override
    public Boolean clickBack() {
        logger.info("click View Back button on top bar");
        Boolean flag=false;
        IWebElementWrapper elt=element(_topbar, CanvasToolBar.BACK.getBtnSuffix());
        if(elt.isDisplayed() && elt.isEnabled()){
            elt.click();
            flag=true;
        }
        return flag;
    }

    @Override
    public Boolean clickTop() {
        logger.info("click View Top button on top bar");
        Boolean flag=false;
        IWebElementWrapper elt=element(_topbar, CanvasToolBar.TOP.getBtnSuffix());
        if(elt.isDisplayed() && elt.isEnabled()){
            elt.click();
            flag=true;
        }
        return flag;
    }

    @Override
    public Boolean clickBottom() {
        logger.info("click View Bottom button on top bar");
        Boolean flag=false;
        IWebElementWrapper elt=element(_topbar, CanvasToolBar.BOTTOM.getBtnSuffix());
        if(elt.isDisplayed() && elt.isEnabled()){
            elt.click();
            flag=true;
        }
        return flag;
    }

    @Override
    public Boolean clickRight() {
        logger.info("click View Right button on top bar");
        Boolean flag=false;
        IWebElementWrapper elt=element(_topbar, CanvasToolBar.RIGHT.getBtnSuffix());
        if(elt.isDisplayed() && elt.isEnabled()){
            elt.click();
            flag=true;
        }
        return flag;
    }

    @Override
    public Boolean clickLeft() {
        logger.info("click View Left button on top bar");
        Boolean flag=false;
        IWebElementWrapper elt=element(_topbar, CanvasToolBar.LEFT.getBtnSuffix());
        if(elt.isDisplayed() && elt.isEnabled()){
            elt.click();
            flag=true;
        }
        return flag;
    }

    @Override
    public Boolean clickZoomFit() {
        logger.info("click Zoom Fit button on top bar");
        Boolean flag=false;
        IWebElementWrapper elt=element(_topbar, CanvasToolBar.ZOOMFIT.getBtnSuffix());
        if(elt.isDisplayed() && elt.isEnabled()){
            elt.click();
            flag=true;
        }
        return flag;
    }

    public Boolean clickSnapshot() {
        logger.info("click Snapshot button on top bar");
        Boolean flag=false;
        IWebElementWrapper elt=element(_topbar, CanvasToolBar.TAKESNAPSHOT.getBtnSuffix());
        if(elt.isDisplayed() && elt.isEnabled()){
            elt.click();
            flag=true;
        }
        return flag;
    }

    @Override
    public Boolean clickTrueColor() {
        logger.info("click True Color button on top bar");
        Boolean flag=false;
        IWebElementWrapper elt=element(_topbar, CanvasToolBar.TRUECOLOR.getBtnSuffix());
        if(elt.isDisplayed() && elt.isEnabled()){
            elt.click();
            flag=true;
        }
        return flag;
    }

    @Override
    public Boolean clickLight() {
        logger.info("click Switch Light button on top bar");
        Boolean flag=false;
        IWebElementWrapper elt=element(_topbar, CanvasToolBar.LIGHT.getBtnSuffix());
        if(elt.isDisplayed() && elt.isEnabled()){
            elt.click();
            flag=true;
        }
        return flag;
    }

    @Override
    public Boolean clickReset() {
        logger.info("click Reset button on top bar");
        Boolean flag=false;
        IWebElementWrapper elt=element(_topbar, CanvasToolBar.RESET.getBtnSuffix());
        if(elt.isDisplayed() && elt.isEnabled()){
            elt.click();
            flag=true;
        }
        return flag;
    }

    @Override
    public DisplayPage clickDisplay() {
        logger.info("click Display button on left bar");
        IWebElementWrapper elt=element(_leftbar, ToolBar.DISPLAY.getBtnText());
        elt.click();
        waitThat(5000);
        return new DisplayPage(webDriverWrapper);
    }

    @Override
    public OrientationAdjustment clickOrientationAdjustment() {
        logger.info("click Orientation Adjustment button on left bar");
        loading(3*1000);
        IWebElementWrapper elt=element(_leftbar, ToolBar.ORIENTATIONADJUSTMENT.getBtnText());//toolbar.btnOrientationAdjustment
        if(elt.isPresent()){
            logger.info("element OrientationAdjustment is present on leftBar");
            elt.click();
            loading();
            OrientationAdjustment orientationAdjustment=new OrientationAdjustment(webDriverWrapper);
            if(!orientationAdjustment.isThePage()){
                elt.click();
                loading();
            }
            return orientationAdjustment;
        }else {
            logger.error("no find OrientationAdjustment on leftBar");
        }

        return null;
    }

    @Override
    public GalleryPage clickGallery() {
        logger.info("click Gallery button on left bar");
        IWebElementWrapper elt=element(_leftbar, ToolBar.GALLERY.getBtnText());
        elt.click();
        return new GalleryPage(webDriverWrapper);
    }

    @Override
    public ImageInformation clickImageInformation() {
        logger.info("click Image Information button on left bar");
        IWebElementWrapper elt=element(_leftbar, ToolBar.IMAGEINFORMATION.getBtnText());//toolbar.btnImageInfo
        elt.click();
        return new ImageInformation(webDriverWrapper);
    }

    @Override
    public AboutDialog clickAbout() {
        logger.info("click About button on left bar");
        IWebElementWrapper elt=element(_leftbar, ToolBar.ABOUT.getBtnText());//toolbar.btnInfo
        elt.click();
        return new AboutDialog(webDriverWrapper);
    }

    @Override
    public ExportPage clickExport() {
        logger.info("click Export button on left bar");
        IWebElementWrapper elt=element(_leftbar, ToolBar.EXPORT.getBtnText());//toolbar.btnExport
        elt.click();
        return new ExportPage(webDriverWrapper);
    }


    /**
     * check top bar is displayed, if one button of top bar provided is missing return false.
     * @param name value in {"ViewFront","ViewBack","ViewTop","ViewBottom","ViewRight","ViewLeft","ZoomFit","TakeSnapshot","ToggleTrueColor","ToggleLight","Reset"}
     * @return
     */
    public Boolean isDisplayedTopBar(String... name) {
        return isDisplayed(_topbar, name);
    }

    /**
     * check top bar is displayed, if one button of top bar is missing return false.
     * default displayed buttons are {"ViewFront","ViewBack","ViewTop","ViewBottom","ViewRight","ViewLeft","ZoomFit","TakeSnapshot","ToggleTrueColor","ToggleLight","Reset"};
     * @return
     */
    public Boolean isDisplayedTopBar() {
        //String[] items=new String[]{"Front","Back","Top","Bottom","Right","Left","Zoom Fit","Snapshot","True Color","Light","Reset"};
        return isDisplayed(_topbar, _topbar_items);
    }


    /**
     * check top bar is presented, if one button of top bar provided is missing return false.
     * @param name value in {"ViewFront","ViewBack","ViewTop","ViewBottom","ViewRight","ViewLeft","ZoomFit","TakeSnapshot","ToggleTrueColor","ToggleLight","Reset"}
     * @return
     */
    public Boolean isPresentedTopBar(String... name) {
        return isPresent(_topbar, name);
    }

    public Boolean isPresentedTopBar() {
        //String[] items=new String[]{"Front","Back","Top","Bottom","Right","Left","Zoom Fit","Snapshot","True Color","Light","Reset"};
        //String[] items=new String[]{"ViewFront","ViewBack","ViewTop","ViewBottom","ViewRight","ViewLeft","ZoomFit","TakeSnapshot","ToggleTrueColor","ToggleLight","Reset"};
        return isPresent("topbar.btn", _topbar_items);
    }


    /**
     * check top bar is enabled, if one button of top bar is missing return false.
     * buttons are {"ViewFront","ViewBack","ViewTop","ViewBottom","ViewRight","ViewLeft","ZoomFit","TakeSnapshot","ToggleTrueColor","ToggleLight","Reset"};
     *
     * @return
     */
    public Boolean isEnabledTopBar(String... name) {
        return isEnabled(_topbar, name);
    }

    /**
     * get top bar is enabled, if one button of top bar is missing return false.
     * default enabled buttons are {"ViewFront","ViewBack","ViewTop","ViewBottom","ViewRight","ViewLeft","ZoomFit","TakeSnapshot","ToggleTrueColor","Reset"};
     * "ToggleLight" button is disabled.
     * @return
     */
    public Boolean getDefaultEnabledStatusTopBar() {
        String[] items=new String[]{"ViewFront","ViewBack","ViewTop","ViewBottom","ViewRight","ViewLeft","ZoomFit","TakeSnapshot","ToggleTrueColor","Reset"};
        //String[] items=new String[]{"Front","Back","Top","Bottom","Right","Left","Zoom Fit","Snapshot","True Color","Reset"};
        return isEnabled(_topbar, items) && !isEnabledTopBar("ToggleLight");
    }


    @Override
    public Boolean isDisplayedLeftBar(String... name) {
        return isDisplayed(_leftbar, name);
    }

    /**
     * check left bar is displayed, if one button of bar is missing return false.
     * default displayed buttons are {"Display","OrientationAdjustment","Gallery","ImageInfo","Info","Help"};
     * @return
     * @throws Exception
     */
    @Override
    public Boolean isDisplayedLeftBar() {
        String[] items=new String[]{"Display","OrientationAdjustment","Gallery","ImageInfo","Info","Help"};
        return isDisplayed(_leftbar, items);
    }



    @Override
    public Boolean isPresentedLeftBar(String... name) {
        return isDisplayed(_leftbar, name);
    }

    @Override
    public Boolean isPresentedLeftBar() {
        String[] items=new String[]{"Display","OrientationAdjustment","Gallery","ImageInfo","Info","Help"};
        return isPresent(_leftbar, items);
    }

    @Override
    public Boolean isEnabledLeftBar(String... name) {
        return isEnabled(_leftbar, name);
    }

    @Override
    public Boolean isEnabledLeftBar() {
        String[] items=new String[]{"Display","OrientationAdjustment","Gallery","ImageInfo","Info","Help"};
        return isEnabled(_leftbar, items);
    }

    @Override
    public Boolean getDefaultPresentedStatusLeftBar() {
        String[] items=new String[]{"Display","OrientationAdjustment","Gallery","ImageInfo","Info","Help"};
        return isPresent(_leftbar, items) && !isPresent(_leftbar,"OcclusionMapping");
    }


}
