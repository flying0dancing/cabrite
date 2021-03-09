package com.csdental.util;

public interface IProp {
    String WEBDRIVER_CHROME=Strs.projectParent()+PropHelper.getProperty("path.webdriver.chrome");

    String RESULT_FOLDER=Strs.projectPath()+PropHelper.getProperty("path.result.folder");
    String SCREENSHOT_FOLDER=RESULT_FOLDER+PropHelper.getProperty("path.screenshot.folder");

    String TEST_SOURCE=PropHelper.getProperty("test.source");
    String SCREENSHOT_TYPE=".png";

    //product meshviewer
    String LOCATORS_MESHVIEWER=PropHelper.getProperty("locators.meshviewer");
    String PRODUCT_MESHVIEWER="meshviewer";
    //String TEST_SOURCE_MESHVIEWER=TEST_SOURCE+PRODUCT_MESHVIEWER+"/";
    String TEST_SOURCE_MESHVIEWER=TEST_SOURCE;

}
