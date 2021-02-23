package com.csdental.meshviewer;

import org.testng.Assert;
import org.testng.annotations.*;

@Test(groups={"smoke","function_test"})
public class TopBarTest  extends TestManager {


    public void topBarDisplayedAfterClickDisplay() throws Exception {
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            meshViewPage.clickDisplay();
            getWebDriverWrapper().takeScreenshot(Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertTrue(meshViewPage.isDisplayedTopBar(),"some buttons of top bar are missing, after clicked display.");
        }
    }

    public void topBarDisappearAfterClickGallery() throws Exception {
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            meshViewPage.clickGallery();
            getWebDriverWrapper().takeScreenshot(Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertFalse(meshViewPage.isPresentedTopBar(),"some buttons of top bar are displayed, after clicked Gallery.");
        }
    }
}
