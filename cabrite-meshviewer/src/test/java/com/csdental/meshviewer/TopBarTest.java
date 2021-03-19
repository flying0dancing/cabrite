package com.csdental.meshviewer;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

@Test(groups={"smoke","function_test"})
public class TopBarTest  extends TestManager {


    public void topBarDisplayedAfterClickDisplay() throws Exception {
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            Reporter.log("click Display in left-top bar");
            meshViewPage.clickDisplay();
            embededScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertTrue(meshViewPage.isDisplayedTopBar(),"some buttons of top bar are missing, after clicked display.");
        }
    }

    public void topBarHideAfterClickGallery() throws Exception {
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            Reporter.log("click Gallery in left-top bar");
            meshViewPage.clickGallery();
            embededScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertFalse(meshViewPage.isPresentedTopBar(),"some buttons of top bar are not hidden, after clicked Gallery.");
        }
    }
}
