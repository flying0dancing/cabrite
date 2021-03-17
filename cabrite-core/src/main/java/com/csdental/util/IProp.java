package com.csdental.util;

public interface IProp {
    String WEBDRIVER_CHROME=Strs.validate(PropHelper.getProperty("path.webdriver.chrome"));


    String RESULT_FOLDER=Strs.resultToday(PropHelper.getProperty("path.result.folder"));
    String SCREENSHOT_FOLDER=RESULT_FOLDER+PropHelper.getProperty("path.screenshot.folder");
    String SCREENSHOT_TYPE=".png";

    String SOURCE_FOLDER =PropHelper.getProperty("test.source");


    //product meshviewer
    String LOCATORS_MESHVIEWER=PropHelper.getProperty("locators.meshviewer");
    String PRODUCT_MESHVIEWER="MeshViewer_TestData";
    String TEST_SOURCE_MESHVIEWER= SOURCE_FOLDER +PRODUCT_MESHVIEWER+"/";


}
