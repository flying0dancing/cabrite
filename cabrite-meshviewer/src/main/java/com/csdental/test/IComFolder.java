package com.csdental.test;

import com.csdental.util.IProp;
import com.csdental.util.PropHelper;
import com.csdental.util.Strs;

public interface IComFolder extends IConfigCmd{
    String RESULT_FOLDER =ICC_RESULTFOLDER==null? IProp.RESULT_FOLDER: Strs.convertFilePath(ICC_RESULTFOLDER+"/");
    String SCREENSHOT_FOLDER=RESULT_FOLDER+PropHelper.getProperty("path.screenshot.folder");
    String REPORT_FOLDER=RESULT_FOLDER+"reportng-reports/";

    String SOURCE_FOLDER=ICC_SOURCEFOLDER==null?Strs.convertFilePath(PropHelper.getProperty("test.source.meshviewer")):Strs.convertFilePath(ICC_SOURCEFOLDER);

}
