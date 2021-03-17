package com.csdental.test;

import com.csdental.util.IProp;
import com.csdental.util.PropHelper;

public interface IComFolder extends IConfigCmd{
    String RESULT_FOLDER =ICC_RESULTFOLDER==null? IProp.RESULT_FOLDER.trim().replace("\\", "/"):ICC_RESULTFOLDER.replace("\\", "/");
    String SCREENSHOT_FOLDER=RESULT_FOLDER+PropHelper.getProperty("path.screenshot.folder");

    String SOURCE_FOLDER=ICC_SOURCEFOLDER==null?PropHelper.getProperty("test.source.meshviewer").trim().replace("\\", "/"):ICC_SOURCEFOLDER.replace("\\", "/");

}
