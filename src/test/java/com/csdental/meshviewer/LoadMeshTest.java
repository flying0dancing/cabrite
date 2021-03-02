package com.csdental.meshviewer;

import com.csdental.util.IProp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


@Test(groups={"smoke","function_test"})
public class LoadMeshTest extends TestManager {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public void checkUploadDCM() throws Exception {
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            String uploadFilePath= IProp.TEST_SOURCE_MESHVIEWER+"652abe30-4de1-4c44-9ff4-a743617ac5c1_Orthodontics.dcm";
            meshViewPage.uploadFile(uploadFilePath);

            if(meshViewPage.fileUploaded()){
                logger.info("loaded {} successfully",uploadFilePath);
                getWebDriverWrapper().takeScreenshot(Thread.currentThread().getStackTrace()[1].getMethodName()+"_success");
            }else {
                logger.error("fail to loaded {}",uploadFilePath);
                getWebDriverWrapper().takeScreenshot(Thread.currentThread().getStackTrace()[1].getMethodName()+"_fail");
            }
        }else {
            logger.error("fail to open page");
            getWebDriverWrapper().takeScreenshot();
        }
    }
    @Test(dependsOnMethods = { "checkUploadDCM" })
    public void checkMoveMesh() throws Exception {
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            meshViewPage.moveMesh();
            getWebDriverWrapper().takeScreenshot(Thread.currentThread().getStackTrace()[1].getMethodName()+"_move");
        }else {
            getWebDriverWrapper().takeScreenshot();
        }
    }


}
