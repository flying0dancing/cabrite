package com.csdental.util;

public interface IProp {
    String WEBDRIVER_CHROME=Strs.projectParent()+PropHelper.getProperty("path.webdriver.chrome");

    String RESULT_FOLDER=Strs.projectPath()+PropHelper.getProperty("path.result.folder");
    String SCREENSHOT_FOLDER=RESULT_FOLDER+PropHelper.getProperty("path.screenshot.folder");
    String LOCATORS_MESHVIEWER=PropHelper.getProperty("locators.meshviewer");

    String PRODUCT_MESHVIEWER="meshviewer";
    String TEST_SOURCE=PropHelper.getProperty("test.source");
    String TEST_SOURCE_MESHVIEWER=PropHelper.getProperty("test.source")+PRODUCT_MESHVIEWER+"/";
    String SCREENSHOT_TYPE=".png";
}
