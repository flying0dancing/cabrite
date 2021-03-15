package com.csdental.meshviewer.displaytool;

import com.csdental.meshviewer.DisplayPage;
import com.csdental.meshviewer.MeshViewPage;
import com.csdental.meshviewer.TestManager;
import org.junit.Assert;
import org.testng.annotations.Test;

@Test(groups={"smoke","function_test"})
public class DisplayTest extends TestManager {
    String uploadFileName="Common_HD3D/common.HD3D.off[1.0.3.600]/Franklin_Benjamin_[2021-03-03_09-51-18].dcm";
    public void mok(){
        String name=Thread.currentThread().getStackTrace()[1].getMethodName();
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            uploadDCM(meshViewPage,uploadFileName);
            DisplayPage displayPage=meshViewPage.clickDisplay();
            Boolean flag=displayPage.moveMandibularSlider(85);
            displayPage.showOrHideMandibular();
            displayPage.showOrHideMandibular();
            displayPage.showOrHideMaxillary();
            displayPage.showOrHideMaxillary();
            /*displayPage.moveMandibularSlider(20);
            displayPage.moveMandibularSlider(55);
            displayPage.moveMandibularSlider(50);
            displayPage.moveMandibularSlider(78);
            displayPage.moveMandibularSlider(0);
            displayPage.moveMandibularSlider(90);
            displayPage.moveMandibularSlider(100);*/

            org.junit.Assert.assertTrue("slider doesn't move to correct position",flag);
        }
    }
}
