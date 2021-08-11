package com.csdental.meshviewer;

import com.csdental.test.IComFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


@Test(groups={"smoke","function_test"})
public class LoadMeshTest extends TestManager {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void checkUploadDCM() throws Exception {
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            String uploadFilePath= IComFolder.SOURCE_FOLDER+
            "Common_HD3D/common.HD3D.off[1.0.3.600]/Franklin_Benjamin_[2021-03-03_09-51-18].dcm";
            meshViewPage.uploadFile(uploadFilePath);

            if(meshViewPage.fileUploaded()){
                logger.info("loaded {} successfully",uploadFilePath);
                embededScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName()+"_success");
            }else {
                logger.error("fail to loaded {}",uploadFilePath);
                embededScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName()+"_fail");
            }
        }else {
            logger.error("fail to open page");
            getWebDriverWrapper().takeScreenshot();
        }
    }
    @Test(dependsOnMethods = { "checkUploadDCM" })
    public void checkMoveMesh() {
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            meshViewPage.moveMesh();
            embededScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName()+"_move");

        }else {
            getWebDriverWrapper().takeScreenshot();
        }
    }


}
