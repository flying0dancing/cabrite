package com.csdental.meshviewer;

import com.csdental.web.IWebDriverWrapper;
import com.csdental.web.IWebElementWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
    public int getIndexOfArchByName(String... archNames){
        IWebElementWrapper eltW=element("display.dentalArch");
        if(eltW.isPresent()){
            IWebElementWrapper elt=element("display.dentalArchNames");
            List<IWebElementWrapper> elts=elt.findElements();

            for (int i = 0; i < elts.size(); i++) {
                String name=elts.get(i).getText();
                //logger.info(name);
                for (int j = 0; j < archNames.length; j++) {
                    if(name.equalsIgnoreCase(archNames[j])){
                        //logger.info("find [{}] at index [{}]",archNames[j],i+1);
                        return i+1;
                    }
                }
            }
        }
        return 0;
    }
    //return 0,1,...
    public Integer getArchCount(){
        IWebElementWrapper eltW=element("display.dentalArch");
        if(eltW.isPresent()){
            IWebElementWrapper elt=element("display.dentalArchCount");
            List<IWebElementWrapper> elts=elt.findElements();
            //logger.info("arch count is {}",elts.size());
            return elts==null?0:elts.size();
        }
        return 0;
    }

    /**
     * click button to show or hide Maxillary
     * @return
     */
    public String showOrHideMaxillary(){
        logger.info("click visible button on Maxillary to show or hide");
        return showOrHideArch(1);
    }
    /**
     * click button to show or hide Mandibular
     * @return
     */
    public String showOrHideMandibular(){
        logger.info("click visible button on Mandibular to show or hide");
        return showOrHideArch(getIndexOfArchByName("Mandibular","Mandibular Anatomy"));
    }
    public String showOrHideMaxillaryPreScan(){
        logger.info("click visible button on Maxillary Pre Scan to show or hide");
        return showOrHideArch(getIndexOfArchByName("Maxillary Pre Scan"));
    }
    public String showOrHideMandibularPreScan(){
        logger.info("click visible button on Mandibular Pre Scan to show or hide");
        return showOrHideArch(getIndexOfArchByName("Mandibular Pre Scan"));
    }
    public String showOrHideExtraObject(){
        logger.info("click visible button on Extra Object to show or hide");
        return showOrHideArch(getIndexOfArchByName("Extra Object"));
    }
    /**
     * get Maxillary status: show or hide
     * @return
     */
    public String getMaxillaryStatus(){
        logger.info("get Maxillary status");
        return getArchStatus(1);
    }
    /**
     * get Mandibular status: show or hide
     * @return
     */
    public String getMandibularStatus(){
        logger.info("get Mandibular status");
        return getArchStatus(getIndexOfArchByName("Mandibular","Mandibular Anatomy"));
    }
    public String getMaxillaryPreScanStatus(){
        logger.info("get Maxillary Pre Scan status");
        return getArchStatus(getIndexOfArchByName("Maxillary Pre Scan"));
    }
    public String getMandibularPreScanStatus(){
        logger.info("get Mandibular Pre Scan status");
        return getArchStatus(getIndexOfArchByName("Mandibular Pre Scan"));
    }
    public String getExtraObjectStatus(){
        logger.info("get Extra Object status");
        return getArchStatus(getIndexOfArchByName("Extra Object"));
    }
    /**
     * get the Maxillary transparent value(0..100)
     * @return
     */
    public Integer getMaxillaryValue(){
        logger.info("get Maxillary transparent value");
        return getArchValue(1);
    }
    /**
     * get the Mandibular transparent value(0..100)
     * @return
     */
    public Integer getMandibularValue(){
        logger.info("get Mandibular transparent value");
        return getArchValue(getIndexOfArchByName("Mandibular","Mandibular Anatomy"));
    }
    public Integer getMaxillaryPreScanValue(){
        logger.info("get Maxillary Pre Scan transparent value");
        return getArchValue(getIndexOfArchByName("Maxillary Pre Scan"));
    }
    public Integer getMandibularPreScanValue(){
        logger.info("get Mandibular Pre Scan transparent value");
        return getArchValue(getIndexOfArchByName("Mandibular Pre Scan"));
    }
    public Integer getExtraObjectValue(){
        logger.info("get Extra Object transparent value");
        return getArchValue(getIndexOfArchByName("Extra Object"));
    }

    public Boolean moveMaxillarySlider(int value){
        logger.info("move Maxillary slider");
        return moveArchSlider(1,value);
    }
    public Boolean moveMandibularSlider(int value){
        logger.info("move Mandibular slider");
        return moveArchSlider(getIndexOfArchByName("Mandibular","Mandibular Anatomy"),value);
    }
    public Boolean moveMaxillaryPreScanSlider(int value){
        logger.info("move Maxillary Pre Scan slider");
        return moveArchSlider(getIndexOfArchByName("Maxillary Pre Scan"),value);
    }
    public Boolean moveMandibularPreScanSlider(int value){
        logger.info("move Mandibular Pre Scan slider");
        return moveArchSlider(getIndexOfArchByName("Mandibular Pre Scan"),value);
    }
    public Boolean moveExtraObjectSlider(int value){
        logger.info("move Extra Object slider");
        return moveArchSlider(getIndexOfArchByName("Extra Object"),value);
    }


    private String showOrHideArch(int index){
        IWebElementWrapper elt=element("display.dentalArchShow",Integer.toString(index));
        elt.click();
        String str=elt.getAttribute("aria-label");
        //logger.info("click show or hide Dental Arch index {}", index);
        logger.info("Dental Arch index {}, status is {}",index,str);
        return str;
    }
    /**
     * get Mandibular status: show or hide
     * @return
     */
    private String getArchStatus(int index){
        waitThat(3000);
        IWebElementWrapper elt=element("display.dentalArchShow",Integer.toString(index));
        String str=elt.getAttribute("aria-label");
        logger.info("Dental Arch index {}, status is {}",index,str);
        return str;
    }
    private Integer getArchValue(int index){
        return getValue("display.dentalArchData",Integer.toString(index));
    }
    private Boolean setArchToZero(int index, IWebElementWrapper elt){
        Integer xCurrent=getArchValue(index);
        while(xCurrent!=0){
            elt.mouse_move(-Math.floorDiv(xCurrent*183,100*2),1);
            waitThat(1000);
            xCurrent=getArchValue(index);
        }

        return getArchValue(index)==0;
    }

    private Boolean moveArchSlider(int index, int value){
        waitThat(3000);
        Boolean flag=false;
        IWebElementWrapper elt=element("display.dentalArchSlider",Integer.toString(index));
        logger.info("Dental Arch index {}, slider it to zero", index);
        flag=setArchToZero(index,elt);
        if(value==0){return flag;}
        Integer step=Math.floorDiv(value*184,100);
        logger.info("Dental Arch index {}, want to move slider to {}",index,value);
        elt.mouse_move(0,0,step,1);
        waitThat(1000);
        Integer xActual=getArchValue(index);
        logger.info("acutally, moved slider to {}",xActual);
        if(xActual==value){
            flag=true;
        }
        return flag;
    }


    //return 0,1,..., max value is 6 (contains normal bite)
    public Integer getBiteCount(){
        IWebElementWrapper eltW=element("display.buccalBite");
        if(eltW.isPresent()){
            IWebElementWrapper elt=element("display.buccalBiteCount");
            List<IWebElementWrapper> elts=elt.findElements();
            //logger.info("Bite count is {}",elts.size());
            return elts==null?0:elts.size();
        }
        return 0;
    }

    public Boolean isCheckedNormalBite(){
        if(getBiteCount()==0){return false;}
        IWebElementWrapper elt=element("display.buccalBiteRadioStatus","1");
        String str=elt.getAttribute("checked");
        logger.info("checked status of Normal Bite is {}",str);
        if(str!=null && str=="true"){
            return true;
        }
        return false;
    }

    public void checkNormalBite(){
        logger.info("want to check Normal Bite radio");
        checkBite(1);
    }
    public void checkExtralBite1(){
        logger.info("want to check Extra Bite 1 radio");
        checkBite(2);
    }
    public void checkExtralBite2(){
        logger.info("want to check Extra Bite 2 radio");
        checkBite(3);
    }
    public void checkExtralBite3(){
        logger.info("want to check Extra Bite 3 radio");
        checkBite(4);
    }
    public void checkExtralBite4(){
        logger.info("want to check Extra Bite 4 radio");
        checkBite(5);
    }


    /**
     * check some bite, like normal bite, extral bite1,...
     */
    private void checkBite(int index){
        int biteCount=getBiteCount();
        if(biteCount>0 && index<=biteCount){
            logger.info("now check radio of index {}",index);
            IWebElementWrapper elt=element("display.buccalBiteRadio",Integer.toString(index));
            elt.click();
            waitThat(1000);
        }
    }

    /**
     * click button to show or hide bite
     * @return
     */
    public String showOrHideNormalBite(){
        logger.info("click show or hide on Normal Bite");
        return showOrHideBite(1);
    }
    /**
     * bite status should be show or hide
     * @return
     */
    public String getNormalBiteStatus(){
        logger.info("get Normal Bite status");
        return getBiteStatus(1);
    }

    /**
     * get the bite transparent value(0..100)
     * @return
     */
    public Integer getNormalBiteValue(){
        logger.info("get Normal Bite transparent value");
        return getBiteValue(1);
    }
    /**
     * set the bite transparent value(0..100)
     * @return
     */
    public Boolean moveNormalBiteSlider(int value){
        logger.info("move Normal Bite slider");
        return moveBiteSlider(1, value);
    }

    /**
     * show or hide buccal bite like normal bite, extral bite 1,...
     * @param index 1(normal bite),2(extral bite 1),3(extral bite 2),4(extral bite 3),5(extral bite 4)
     * @return
     */
    private String showOrHideBite(int index){
        IWebElementWrapper elt=element("display.buccalBiteShow",Integer.toString(index));
        elt.click();
        String str=elt.getAttribute("aria-label");
        logger.info("click visible button on Buccal Bite index {}", index);
        logger.info("Buccal Bite index {}, status is {}",index,str);
        return str;
    }

    /**
     * show or hide buccal bite like normal bite, extral bite 1,...
     * @param index 1(normal bite),2(extral bite 1),3(extral bite 2),4(extral bite 3),5(extral bite 4)
     * @return
     */
    private String getBiteStatus(int index){
        IWebElementWrapper elt=element("display.buccalBiteShow",Integer.toString(index));
        String str=elt.getAttribute("aria-label");
        logger.info("Buccal Bite index {}, status is {}",index,str);
        return str;
    }
    private Integer getBiteValue(int index){
        return getValue("display.buccalBiteData",Integer.toString(index));
    }

    private Boolean setBiteToZero(int index, IWebElementWrapper elt){
        Integer xCurrent=getBiteValue(index);
        while(xCurrent!=0){
            elt.mouse_move(-Math.floorDiv(xCurrent*183,100*2),1);
            waitThat(1000);
            xCurrent=getBiteValue(index);
        }
        return getBiteValue(index)==0;
    }

    private Boolean moveBiteSlider(int index,int value){
        waitThat(3000);
        Boolean flag=false;
        IWebElementWrapper elt=element("display.buccalBiteSlider",Integer.toString(index));
        logger.info("Buccal Bite index {}, slider it to zero", index);
        flag=setBiteToZero(index,elt);
        if(value==0){return flag;}
        Integer step=Math.floorDiv(value*184,100);
        logger.info("Buccal Bite index {}, want to move slider to {}",index,value);
        elt.mouse_move(0,0,step,1);
        waitThat(1000);
        Integer xActual=getBiteValue(index);
        logger.info("acutally, moved slider to {}",xActual);
        if(xActual==value){
            flag=true;
        }
        return flag;
    }


    private Integer getValue(String locator){
        IWebElementWrapper elt=element(locator);
        String text=elt.getText();
        logger.info("element[{}] value is {}",locator,text);
        return Integer.parseInt(text);
    }

    private Integer getValue(String locator, String... replacements){
        IWebElementWrapper elt=element(locator,replacements);
        String text=elt.getText();
        logger.info("element[{}][{}] value is {}",locator,replacements,text);
        return Integer.parseInt(text);
    }

}
