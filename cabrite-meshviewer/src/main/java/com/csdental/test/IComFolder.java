package com.csdental.test;

import com.mainland.test.IConfigCmd;
import com.mainland.util.IProp;
import com.mainland.util.PropHelper;
import com.mainland.util.Strs;

public interface IComFolder extends IConfigCmd {
    String RESULT_FOLDER =ICC_RESULTFOLDER==null? IProp.RESULT_FOLDER: Strs.convertFilePath(ICC_RESULTFOLDER+"/");
    String SCREENSHOT_FOLDER=RESULT_FOLDER+PropHelper.getProperty("path.screenshot.folder");
    String SCREENSHOT_FOLDER_NAME=Strs.removeLastSlash(PropHelper.getProperty("path.screenshot.folder"));
    String REPORT_FOLDER=RESULT_FOLDER+"reportng-reports/";

    String SOURCE_FOLDER=ICC_SOURCEFOLDER==null?Strs.convertFilePath(PropHelper.getProperty("test.source.meshviewer")):Strs.convertFilePath(ICC_SOURCEFOLDER);
    String SOURCE_EXPECTATION_FOLDER=SOURCE_FOLDER+"/expectation/";

    String RESULT_ACTUAL_FOLDER = RESULT_FOLDER+"/actual/";
    String RESULT_ACTUAL_FOLDER_NAME = "actual";
    String RESULT_EXPECTATION_FOLDER = RESULT_ACTUAL_FOLDER+"/expectation/";
}
