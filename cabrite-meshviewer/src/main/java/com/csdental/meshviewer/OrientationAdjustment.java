package com.csdental.meshviewer;

import com.csdental.web.IWebDriverWrapper;
import com.csdental.web.IWebElementWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class OrientationAdjustment extends BasePage  implements ITool {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map opAngle=new HashMap<String,String>(){{put("Angle","1");put("Decrease","2");put("Increase","3");put("Reset","4");}};
    private Map opDistance=new HashMap<String,String>(){{put("Distance","2");put("Decrease","3");put("Increase","4");put("Reset","5");}};
    private ITool tool;
    private IWebDriverWrapper webDriverWrapper;

    public OrientationAdjustment(IWebDriverWrapper webDriverWrapper) {
        super(webDriverWrapper);
        this.webDriverWrapper=webDriverWrapper;
        tool=tool==null?new Tool(webDriverWrapper):tool;
    }

    public Boolean isThePage() {
        waitThat();
        IWebElementWrapper elt=element("orientationAdjustment.title");
        if(elt.isPresent()){
            String title=elt.getText();
            if(title.equals("Orientation Adjustment")){
                logger.info("page title is {}",title);
                return true;
            }
        }
        return false;
    }

    /***
     * click Occlusion Plane, return true when show it
     * @return
     */
    public Boolean clickOcclusionPlane() {
        Boolean flag=false;
        IWebElementWrapper elt=element("orientationAdjustment.occlusionPlane");
        elt.click();
        loading();
        IWebElementWrapper eltStatus=element("orientationAdjustment.occlusionPlaneStatus");
        if(eltStatus.isPresent()){
            logger.info("show Occlusion Plane");
            flag=true;
        }else{
            logger.info("hide Occlusion Plane");
        }
        return flag;
    }

    public String getRotationStep(){
        IWebElementWrapper elt=element("orientationAdjustment.rotationStep");
        return elt.getText();
    }
    public String selectRotationStep(String text){
        IWebElementWrapper elt=element("orientationAdjustment.rotationStep");
        if(text.equals(elt.getText())){
            logger.info("default selected {}",text);
        }else {
            logger.info("select rotation step {}",text);
            elt.click();
            loading();
            element("orientationAdjustment.rotationStepUl").selectByVisibleText(text);
            loading(3000);
        }
        return elt.getText();
    }


    private String getAngle(String xyz) {

        IWebElementWrapper elt=element("orientationAdjustment.rotationAngle",xyz, (String) opAngle.get("Angle"));
        String angle=elt.getAttribute("value");
        logger.info("get angle value {}",angle);
        return angle;
    }
    private String clickAngle(String xyz,String increaseOrDecreaseOrReset,int times) {
        logger.info("click Rotation Step {} {} {} times",xyz,increaseOrDecreaseOrReset,times);
        IWebElementWrapper elt=element("orientationAdjustment.rotationOperate",xyz, (String) opAngle.get(increaseOrDecreaseOrReset));
        for(int i=0;i<times;i++){
            elt.click();
        }

        loading(1000);
        return getAngle(xyz);
    }
    public String clickXAnglePlus() {
        return clickXAnglePlus(1);
    }
    public String clickXAnglePlus(int times) {
        return clickAngle("X","Increase",times);
    }
    public String clickXAngleMinus() {
        return clickXAngleMinus(1);
    }
    public String clickXAngleMinus(int times) {
        return clickAngle("X","Decrease",times);
    }
    public String clickXAngleReset() {
        return clickXAngleReset(1);
    }
    public String clickXAngleReset(int times) {
        return clickAngle("X","Reset",times);
    }

    public String clickYAnglePlus() {
        return clickYAnglePlus(1);
    }
    public String clickYAnglePlus(int times) {
        return clickAngle("Y","Increase",times);
    }
    public String clickYAngleMinus() {
        return clickYAngleMinus(1);
    }
    public String clickYAngleMinus(int times) {
        return clickAngle("Y","Decrease",times);
    }
    public String clickYAngleReset() {
        return clickYAngleReset(1);
    }
    public String clickYAngleReset(int times) {
        return clickAngle("Y","Reset",times);
    }

    public String clickZAnglePlus() {
        return clickZAnglePlus(1);
    }
    public String clickZAnglePlus(int times) {
        return clickAngle("Z","Increase",times);
    }
    public String clickZAngleMinus() {
        return clickZAngleMinus(1);
    }
    public String clickZAngleMinus(int times) {
        return clickAngle("Z","Decrease",times);
    }
    public String clickZAngleReset() {
        return clickZAngleReset(1);
    }
    public String clickZAngleReset(int times) {
        return clickAngle("Z","Reset",times);
    }

    public String selectMovementStep(String text) {
        IWebElementWrapper elt=element("orientationAdjustment.movementStep");
        if(text.equals(elt.getText())){
            logger.info("default selected {}",text);
        }else {
            logger.info("select rotation step {}",text);
            elt.click();
            loading();
            element("orientationAdjustment.movementStepUl").selectByVisibleText(text);
            loading(3000);
        }
        return elt.getText();
    }

    public String getDistance() {
        IWebElementWrapper elt=element("orientationAdjustment.movementDistance");
        String distance=elt.getAttribute("value");
        logger.info("get distance value {}",distance);
        return distance;
    }
    public String clickDistance(String increaseOrDecreaseOrReset,int times) {
        logger.info("click Movement Step Z {} {} times",increaseOrDecreaseOrReset,times);
        IWebElementWrapper elt=element("orientationAdjustment.movementOperate",(String) opDistance.get(increaseOrDecreaseOrReset));
        for(int i=0;i<times;i++){
            elt.click();
        }

        loading(1000);
        return getDistance();
    }

    public void selectUpperJaw_DualView() {
        selectJaw("canvas.left",43,-54,"upper jaw on dual view");
    }
    public void selectLowerJaw_DualView() {
        selectJaw("canvas.left",43,46,"lower jaw on dual view");
    }


    public void selectUpperJaw_SingleView() {
        selectJaw("canvas.it",43,-54,"upper jaw on single view");
    }
    public void selectLowerJaw_SingleView() {
        selectJaw("canvas.it",43,46,"lower jaw on single view");
    }

    private void selectJaw(String canvas,Integer xoffset,Integer yoffset, String notes){
        logger.info("click canvas on ("+xoffset.toString()+", "+yoffset.toString()+") for selected "+notes);
        element(canvas).mouse_click(xoffset,yoffset);
        loading(10000);
    }


    public String clickDistancePlus() {
        return clickDistancePlus(1);
    }
    public String clickDistancePlus(int times) {
        return clickDistance("Increase", times);
    }
    public String clickDistanceMinus() {
        return clickDistanceMinus(1);
    }
    public String clickDistanceMinus(int times) {
        return clickDistance("Decrease", times);
    }
    public String clickDistanceReset() {
        return clickDistanceReset(1);
    }
    public String clickDistanceReset(int times) {
        return clickDistance("Reset", times);
    }

    /**
     * click reset all adjustments and return true when reset all to zero
     * @return
     */
    public Boolean clickResetAllAdjustments() {
        Boolean flag=true;
        IWebElementWrapper elt=element("orientationAdjustment.resetAll");
        elt.click();
        loading();
        String[] xyz=new String[]{"X","Y","Z"};
        for (String s:xyz
             ) {
            if(!getAngle(s).equals("0")){
                flag=false;
            }
        }
        if(!getDistance().equals("0")){
            flag=false;
        }
        return flag;
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
