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

    public Boolean isThePage(){
        waitThat();
        IWebElementWrapper elt= element("display.title");
        String title=elt.getText();
        if(title.equals("Display")){
            logger.info("page title is {}",title);
            return true;
        }
        return false;
    }

    public void showOrHideMaxillary(){
        IWebElementWrapper elt=element("display.maxillaryShow");
        elt.click();
        waitThat(1000);
    }

    public void showOrHideMandibular(){
        IWebElementWrapper elt=element("display.mandibularShow");
        elt.click();
        waitThat(1000);
    }

    public Integer getMaxillaryValue(){
        return getValue("display.maxillaryData");
    }

    public Integer getMandibularValue(){
        return getValue("display.mandibularData");
    }

    private Integer getValue(String locator){
        IWebElementWrapper elt=element(locator);
        String text=elt.getText();
        logger.debug("element[{}] value is {}",locator,text);
        return Integer.parseInt(text);
    }

    private Boolean setMaxillaryToZero(IWebElementWrapper elt){
        Integer xCurrent=getMaxillaryValue();
        while(xCurrent!=0){
            elt.mouse_move(-Math.floorDiv(xCurrent*183,100*2),1);
            waitThat(1000);
            xCurrent=getMaxillaryValue();
        }

        return getMaxillaryValue()==0;
    }
    private Boolean setMandibularToZero(IWebElementWrapper elt){
        Integer xCurrent=getMandibularValue();
        while(xCurrent!=0){
            elt.mouse_move(-Math.floorDiv(xCurrent*183,100*2),1);
            waitThat(1000);
            xCurrent=getMandibularValue();
        }

        return getMandibularValue()==0;
    }

    public Boolean moveMaxillarySlider(int value){
        waitThat(3000);
        Boolean flag=false;
        IWebElementWrapper elt=element("display.maxillarySlider");
        flag=setMaxillaryToZero(elt);
        if(value==0){return flag;}
        Integer step=Math.floorDiv(value*184,100);
        logger.info("want to move maxillary slider to {}",value);
        elt.mouse_move(0,0,step,1);
        waitThat(1000);
        Integer xActual=getMaxillaryValue();
        logger.info("acutally, moved maxillary slider to {}",xActual);
        if(xActual==value){
            flag=true;
        }
        return flag;
    }

    public Boolean moveMandibularSlider(int value){
        waitThat(3000);
        Boolean flag=false;
        IWebElementWrapper elt=element("display.mandibularSlider");
        flag=setMandibularToZero(elt);
        if(value==0){return flag;}
        Integer step=Math.floorDiv(value*184,100);
        logger.info("want to move Mandibular slider to {}",value);
        elt.mouse_move(0,0,step,1);
        waitThat(1000);
        Integer xActual=getMandibularValue();
        logger.info("acutally, moved Mandibular slider to {}",xActual);
        if(xActual==value){
            flag=true;
        }
        return flag;
    }


}
