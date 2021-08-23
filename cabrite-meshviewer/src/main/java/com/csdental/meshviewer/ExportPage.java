package com.csdental.meshviewer;

import com.mainland.web.IWebDriverWrapper;
import com.mainland.web.IWebElementWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExportPage extends BasePage{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ExportPage(IWebDriverWrapper webDriverWrapper) {
        super(webDriverWrapper);
    }

    public Boolean isThePage(){
        waitThat();
        IWebElementWrapper elt= element("export.title");
        String title=elt.getText();
        if(title.equals("Export")){
            logger.info("page title is {}",title);
            return true;
        }
        return false;
    }
    /*public Boolean defaultFormatIsSTL(){
        return getSelectedExportFormat().equalsIgnoreCase("stl");
    }*/
    public String getSelectedExportFormat(){
        waitThat();
        IWebElementWrapper elt=element("export.selectedFormat");
        String formatStr=elt.getAttribute("value");
        return formatStr;
    }
    public Boolean selectSTL(){
        return exportFormat("stl").equalsIgnoreCase("stl");
    }
    public Boolean selectedPLY(){
        return exportFormat("ply").equalsIgnoreCase("ply");
    }
    private String exportFormat(String formatStr){
        waitThat();
        IWebElementWrapper elt=element("export.format",formatStr);
        waitThat();
        elt.click();
        waitThat();
        String formatRes=getSelectedExportFormat();
        return formatRes;
    }

    public void checkExportWithOrientationPresets(){
        waitThat();
        IWebElementWrapper elt=element("export.presetsOrientation");
        if(elt.isEnabled()){
            elt.click();
        }
    }

    public Boolean isSelectedExportWithOrientationPresets(){
        waitThat();
        IWebElementWrapper elt=element("export.presetsOrientation");
        return elt.isSelected();
    }

    public Boolean isSelectedOrientation(){
        return isSelectedOrientationRadio("1") && isSelectedOrientationRadio("2") && isSelectedOrientationRadio("3");
    }

    public Boolean select3Shape(){
        selectedOrientation("1");
        return getOrientationValue("1").equalsIgnoreCase("3Shape");
    }
    public Boolean selectExocad(){
        selectedOrientation("2");
        return getOrientationValue("2").equalsIgnoreCase("exocad");
    }
    public Boolean selectDentalWings(){
        selectedOrientation("3");
        return getOrientationValue("3").equalsIgnoreCase("Dental Wings");
    }

    public Boolean isSelectedOrientationRadio(String index){
        waitThat();
        IWebElementWrapper elt=element("export.orientationRadio",index);
        return elt.isSelected();
    }

    public void selectedOrientation(String index){
        waitThat();
        IWebElementWrapper elt=element("export.orientationRadio",index);
        if(elt.isEnabled()){
            elt.click();
        }
    }

    public String getOrientationValue(String index){
        IWebElementWrapper elt=element("export.orientationValue",index);
        return elt.getAttribute("value");
    }

    public void clickExport(){
        IWebElementWrapper elt=element("export.export");
        elt.click();
    }



}
