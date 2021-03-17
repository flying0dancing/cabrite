package com.csdental.meshviewer;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

@Test(groups={"smoke","function_test"})
public class DisplayCheck extends TestManager {
    public void topBarDisplayed() {
        Reporter.log("static check all buttons in top bar display and enable.");
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            Reporter.log("take screenshot");
            getWebDriverWrapper().takeScreenshot(Thread.currentThread().getStackTrace()[1].getMethodName());

            Assert.assertTrue(meshViewPage.isDisplayedTopBar(),"some buttons of top bar are missing.");
        }
    }
    public void leftBarDisplayed() {
        Reporter.log("static check all buttons in left-top bar display and enable.");
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            Reporter.log("take screenshot");
            getWebDriverWrapper().takeScreenshot(Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertTrue(meshViewPage.isDisplayedLeftBar(),"some buttons of left bar are missing.");
        }
    }

}
