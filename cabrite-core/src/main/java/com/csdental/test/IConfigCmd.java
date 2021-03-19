package com.csdental.test;

import com.csdental.util.Strs;
import org.testng.util.Strings;

public interface IConfigCmd {
    String ICC_SOURCEFOLDER=System.getProperties().containsKey("source")?Strs.reviseFilePath(System.getProperty("source")):null;

    String ICC_RESULTFOLDER=System.getProperties().containsKey("result")? Strs.reviseFilePath(System.getProperty("result")):null;

    Boolean ICCB_RERUN=System.getProperties().containsKey("rerun");//rerun
    String ICC_RERUNCONTENT=ICCB_RERUN?(Strings.isNullOrEmpty(System.getProperty("rerun"))?"all":System.getProperty("rerun").toLowerCase()):null;

    Boolean ICCB_SCENARIO_XML=System.getProperties().containsKey("suiteXmlFile");
    String ICC_SCENARIO_XML=ICCB_SCENARIO_XML?System.getProperty("suiteXmlFile").replace("\\", "/"):"";
    Integer ICC_SCENARIO_INDEX=ICC_SCENARIO_XML.lastIndexOf('/');
    String ICC_SCENARIOSFOLDER=ICC_SCENARIO_XML.substring(0,ICC_SCENARIO_INDEX+1);


}
