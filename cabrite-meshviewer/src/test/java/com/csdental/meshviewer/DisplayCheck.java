package com.csdental.meshviewer;

import com.csdental.test.Reporter;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups={"smoke","function_test"})
public class DisplayCheck extends TestManager {
    public void topBarDisplayed() {
        Reporter.testStart();
        Reporter.log("static check all buttons in top bar display and enable.");
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            embededScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertTrue(meshViewPage.isDisplayedTopBar(),"some buttons of top bar are missing.");
        }
        Reporter.testEnd();
    }
    public void leftBarDisplayed() {
        Reporter.testStart();
        Reporter.log("static check all buttons in left-top bar display and enable.");
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            embededScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName());
            Assert.assertTrue(meshViewPage.isDisplayedLeftBar(),"some buttons of left bar are missing.");
        }
        Reporter.testEnd();
    }

}
