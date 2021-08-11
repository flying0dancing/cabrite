package com.mainland.util;

public interface IProp {
    String WEBDRIVER_CHROME=Strs.validate(PropHelper.getProperty("path.webdriver.chrome"));
    String WEBDRIVER_FIREFOX=Strs.validate(PropHelper.getProperty("path.webdriver.firefox"));
    String WEBDRIVER_EDGE_X86=Strs.validate(PropHelper.getProperty("path.webdriver.edge.x86"));

    String RESULT_FOLDER=Strs.resultToday(PropHelper.getProperty("path.result.folder"));

    String SCREENSHOT_FOLDER=RESULT_FOLDER+PropHelper.getProperty("path.screenshot.folder");
    String SCREENSHOT_TYPE=".png";

    String REPORT_FOLDER=RESULT_FOLDER+"reportng-reports";

    String SOURCE_FOLDER =PropHelper.getProperty("test.source");


    //product meshviewer
    String LOCATORS_MESHVIEWER=PropHelper.getProperty("locators.meshviewer");
    String PRODUCT_MESHVIEWER="MeshViewer_TestData";
    String TEST_SOURCE_MESHVIEWER= SOURCE_FOLDER +PRODUCT_MESHVIEWER+"/";


}
