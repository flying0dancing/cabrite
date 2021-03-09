package com.csdental.meshviewer;

import com.csdental.web.IWebDriverWrapper;
import com.csdental.web.IWebElementWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class OrientationAdjustment  extends BasePage{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public OrientationAdjustment(IWebDriverWrapper webDriverWrapper) {
        super(webDriverWrapper);
    }
    private Map opAngle=new HashMap<String,String>(){{put("Angle","1");put("Decrease","2");put("Increase","3");put("Reset","4");}};
    private Map opDistance=new HashMap<String,String>(){{put("Distance","2");put("Decrease","3");put("Increase","4");put("Reset","5");}};

    public Boolean isThePage() throws Exception {
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

    public String getRotationStep()throws Exception{
        IWebElementWrapper elt=element("orientationAdjustment.rotationStep");
        return elt.getText();
    }
    public String selectRotationStep(String text) throws Exception {
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


    public String getAngle(String xyz) throws Exception {

        IWebElementWrapper elt=element("orientationAdjustment.rotationAngle",xyz, (String) opAngle.get("Angle"));
        String angle=elt.getAttribute("value");
        logger.info("get angle value {}",angle);
        return angle;
    }
    public String clickAngle(String xyz,String increaseOrDecreaseOrReset,int times) throws Exception {
        logger.info("click Rotation Step {} {} {} times",xyz,increaseOrDecreaseOrReset,times);
        IWebElementWrapper elt=element("orientationAdjustment.rotationOperate",xyz, (String) opAngle.get(increaseOrDecreaseOrReset));
        for(int i=0;i<times;i++){
            elt.click();
        }

        loading(1000);
        return getAngle(xyz);
    }
    public String clickXAnglePlus() throws Exception {
        return clickXAnglePlus(1);
    }
    public String clickXAnglePlus(int times) throws Exception {
        return clickAngle("X","Increase",times);
    }
    public String clickXAngleMinus() throws Exception {
        return clickXAngleMinus(1);
    }
    public String clickXAngleMinus(int times) throws Exception {
        return clickAngle("X","Decrease",times);
    }
    public String clickXAngleReset() throws Exception {
        return clickXAngleReset(1);
    }
    public String clickXAngleReset(int times) throws Exception {
        return clickAngle("X","Reset",times);
    }

    public String clickYAnglePlus() throws Exception {
        return clickYAnglePlus(1);
    }
    public String clickYAnglePlus(int times) throws Exception {
        return clickAngle("Y","Increase",times);
    }
    public String clickYAngleMinus() throws Exception {
        return clickYAngleMinus(1);
    }
    public String clickYAngleMinus(int times) throws Exception {
        return clickAngle("Y","Decrease",times);
    }
    public String clickYAngleReset() throws Exception {
        return clickYAngleReset(1);
    }
    public String clickYAngleReset(int times) throws Exception {
        return clickAngle("Y","Reset",times);
    }

    public String clickZAnglePlus() throws Exception {
        return clickZAnglePlus(1);
    }
    public String clickZAnglePlus(int times) throws Exception {
        return clickAngle("Z","Increase",times);
    }
    public String clickZAngleMinus() throws Exception {
        return clickZAngleMinus(1);
    }
    public String clickZAngleMinus(int times) throws Exception {
        return clickAngle("Z","Decrease",times);
    }
    public String clickZAngleReset() throws Exception {
        return clickZAngleReset(1);
    }
    public String clickZAngleReset(int times) throws Exception {
        return clickAngle("Z","Reset",times);
    }

    public String selectMovementStep(String text) throws Exception {
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

    public String getDistance() throws Exception {
        IWebElementWrapper elt=element("orientationAdjustment.movementDistance");
        String distance=elt.getAttribute("value");
        logger.info("get distance value {}",distance);
        return distance;
    }
    public String clickDistance(String increaseOrDecreaseOrReset,int times) throws Exception {
        logger.info("click Movement Step Z {} {} times",increaseOrDecreaseOrReset,times);
        IWebElementWrapper elt=element("orientationAdjustment.movementOperate",(String) opDistance.get(increaseOrDecreaseOrReset));
        for(int i=0;i<times;i++){
            elt.click();
        }

        loading(1000);
        return getDistance();
    }
    public String selectJaw(String upperJawOrLowerJaw){
        return null;
    }
    public void selectUpperJaw() throws Exception {
        logger.info("double click mesh on (300,300)");
        element("canvas.it").canvas_double_click(300,300);
    }
    public void selectLowerJaw() throws Exception {
        logger.info("double click mesh on (400,450)");
        element("canvas.it").canvas_double_click(26,30);
        loading(10000);
    }

    public String clickDistancePlus() throws Exception {
        return clickDistancePlus(1);
    }
    public String clickDistancePlus(int times) throws Exception {
        return clickDistance("Increase", times);
    }
    public String clickDistanceMinus() throws Exception {
        return clickDistanceMinus(1);
    }
    public String clickDistanceMinus(int times) throws Exception {
        return clickDistance("Decrease", times);
    }
    public String clickDistanceReset() throws Exception {
        return clickDistanceReset(1);
    }
    public String clickDistanceReset(int times) throws Exception {
        return clickDistance("Reset", times);
    }

    public Boolean clickResetAllAdjustments() throws Exception {
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
}
