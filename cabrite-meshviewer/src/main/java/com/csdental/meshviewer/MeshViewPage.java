package com.csdental.meshviewer;

import com.csdental.web.IWebDriverWrapper;
import com.csdental.web.IWebElementWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MeshViewPage extends BasePage implements ITool {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private IWebDriverWrapper webDriverWrapper;
    private ITool tool;
    String title;

    public MeshViewPage(IWebDriverWrapper webDriverWrapper){
        super(webDriverWrapper);
        this.webDriverWrapper=webDriverWrapper;
        tool=tool==null?new Tool(webDriverWrapper):tool;
        title=webDriverWrapper.getTitle();
    }

    public Boolean isThePage(){
        waitThat();
        logger.info("page title is {}",title);
        return true;
    }

    public Boolean isDisplayedOcclusionProximity() throws Exception {
        return isDisplayedLeftBar("OcclusionMapping");
        /*IWebElementWrapper elt=element("leftbar.occlusionProximity");
        if(elt.isDisplayed() && elt.isEnabled()){
            return true;
        }
        return false;*/
    }

    public void uploadFile(String filefullname) {
        logger.info("upload file {}",filefullname);
        String js="document.getElementsByTagName('input')[0].removeAttribute('hidden');";
        executeScript(js);
        IWebElementWrapper elt=element("upload");
        elt.sendKeys(filefullname);
        loading(5000);
    }

    public Boolean fileUploaded() throws Exception {
        return isDisplayedOcclusionProximity();
    }

    public void moveMesh() {
        logger.info("move mesh from (0,0) to (200,200)");
        element("canvas.it").mouse_move(0,0,200,200);
    }

    public Boolean fileUploadDlgExist() {
        IWebElementWrapper elt=element("uploadDlg");
        if(elt.isPresent()){
            return true;
        }
        return false;
    }


    @Override
    public String getLanguage() {
        return tool.getLanguage();
    }

    @Override
    public Boolean changeLanguage(String language) {
        return tool.changeLanguage(language);
    }

    @Override
    public Boolean clickFront() {
        return tool.clickFront();
    }

    @Override
    public Boolean clickBack() {
        return tool.clickBack();
    }

    @Override
    public Boolean clickTop() {
        return tool.clickTop();
    }

    @Override
    public Boolean clickBottom() {
        return tool.clickBottom();
    }

    @Override
    public Boolean clickRight() {
        return tool.clickRight();
    }

    @Override
    public Boolean clickLeft() {
        return tool.clickLeft();
    }

    @Override
    public Boolean clickZoomFit() {
        return tool.clickZoomFit();
    }

    @Override
    public Boolean clickSnapshot() {
        return tool.clickSnapshot();
    }

    @Override
    public Boolean clickTrueColor() {
        return tool.clickTrueColor();
    }

    @Override
    public Boolean clickLight() {
        return tool.clickLight();
    }

    @Override
    public Boolean clickReset() {
        return tool.clickReset();
    }

    @Override
    public DisplayPage clickDisplay() {
        return tool.clickDisplay();
    }

    @Override
    public OrientationAdjustment clickOrientationAdjustment() {
        return tool.clickOrientationAdjustment();
    }

    @Override
    public GalleryPage clickGallery() {
        return tool.clickGallery();
    }

    @Override
    public ImageInformation clickImageInformation() {
        return tool.clickImageInformation();
    }

    @Override
    public AboutDialog clickAbout() {
        return tool.clickAbout();
    }

    @Override
    public ExportPage clickExport() {
        return tool.clickExport();
    }

    @Override
    public Boolean isDisplayedTopBar(String... name) {
        return tool.isDisplayedTopBar(name);
    }

    @Override
    public Boolean isDisplayedTopBar() {
        return tool.isDisplayedTopBar();
    }

    @Override
    public Boolean isPresentedTopBar(String... name) {
        return tool.isPresentedTopBar(name);
    }

    @Override
    public Boolean isPresentedTopBar() {
        return tool.isPresentedTopBar();
    }

    @Override
    public Boolean isEnabledTopBar(String... name) {
        return tool.isEnabledTopBar(name);
    }

    @Override
    public Boolean getDefaultEnabledStatusTopBar() {
        return tool.getDefaultEnabledStatusTopBar();
    }

    @Override
    public Boolean isDisplayedLeftBar(String... name) {
        return tool.isDisplayedLeftBar(name);
    }

    @Override
    public Boolean isDisplayedLeftBar() {
        return tool.isDisplayedLeftBar();
    }

    @Override
    public Boolean isPresentedLeftBar(String... name) {
        return tool.isPresentedLeftBar(name);
    }

    @Override
    public Boolean isPresentedLeftBar() {
        return tool.isPresentedLeftBar();
    }

    @Override
    public Boolean isEnabledLeftBar(String... name) {
        return tool.isEnabledLeftBar(name);
    }

    @Override
    public Boolean isEnabledLeftBar() {
        return tool.isEnabledLeftBar();
    }

    @Override
    public Boolean getDefaultPresentedStatusLeftBar() {
        return tool.getDefaultPresentedStatusLeftBar();
    }
}
