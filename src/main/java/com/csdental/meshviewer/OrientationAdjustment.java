package com.csdental.meshviewer;

import com.csdental.web.IWebDriverWrapper;
import com.csdental.web.IWebElementWrapper;
import com.csdental.web.IWebSelectWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrientationAdjustment  extends BasePage{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public OrientationAdjustment(IWebDriverWrapper webDriverWrapper) {
        super(webDriverWrapper);
    }

    public Boolean isThePage() throws Exception {
        waitThat();
        IWebElementWrapper elt=element("orientationAdjustment.title");
        String title=elt.getText();
        if(title.equals("Orientation Adjustment")){
            logger.info("page title is {}",title);
            return true;
        }
        return false;
    }
    public String selectRotationStep(String text) throws Exception {
        IWebElementWrapper elt=element("orientationAdjustment.rotationStep");
        if(text.equals(elt.getText())){
            logger.info("default selected {0}",text);
        }else {
            elt.click();
            loading();
            IWebSelectWrapper selt=select("orientationAdjustment.rotationStepUl");
            selt.selectByVisibleText(text);
            loading(3000);
        }
        return elt.getText();
    }

    public String getXsAngle(){
        return null;
    }
    public String getYsAngle(){
        return null;
    }
    public String getZsAngle(){
        return null;
    }

    /**
     *
     * @param xyz
     * @param index [0,1,2]
     * @return
     */
    public String getAngle(String xyz,int index){
        return null;
    }
    public String clickAnglePlus(){
        return null;
    }
    public String clickAngleMinus(){
        return null;
    }
    public String clickAngleReset(){
        return null;
    }
    public String getZsDistance(){
        return null;
    }
    public String getDistance(String z){
        return null;
    }
    public String clickDistancePlus(){
        return null;
    }
    public String clickDistanceMinus(){
        return null;
    }
    public String clickDistanceReset(){
        return null;
    }
    public String clickResetAllAdjustments(){
        return null;
    }
}
