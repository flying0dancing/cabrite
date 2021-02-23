package com.csdental.meshviewer;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups={"smoke","function_test"})
public class DisplayCheck extends TestManager {
    public void topBarDisplayed() throws Exception {
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            getWebDriverWrapper().takeScreenshot(Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertTrue(meshViewPage.isDisplayedTopBar(),"some buttons of top bar are missing.");
        }
    }
    public void leftBarDisplayed() throws Exception {
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            getWebDriverWrapper().takeScreenshot(Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertTrue(meshViewPage.isDisplayedLeftBar(),"some buttons of left bar are missing.");
        }
    }

}
