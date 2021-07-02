package com.csdental.meshviewer.displaytool;

import com.csdental.meshviewer.DisplayPage;
import com.csdental.meshviewer.MeshViewPage;
import com.csdental.meshviewer.TestManager;
import org.junit.Assert;
import org.testng.annotations.Test;

@Test(groups={"smoke","function_test"})
public class DisplayTest extends TestManager {
    //String uploadFileName="Common_HD3D/common.HD3D.off[1.0.3.600]/Franklin_Benjamin_[2021-03-03_09-51-18].dcm";
    String uploadFileName="FullArchWithExtraBite/a870192f-f6df-40ae-b134-a9d4f198945e_Orthodontics.dcm";
    public void mok(){
        String name=Thread.currentThread().getStackTrace()[1].getMethodName();
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            uploadDCM(meshViewPage,uploadFileName);
            DisplayPage displayPage=meshViewPage.clickDisplay();
            //displayPage.getArchCount();
            //displayPage.getBiteCount();
            displayPage.getIndexOfArchByName("Mandibular","Mandibular Anatomy");
            /*displayPage.isCheckedNormalBite();
            displayPage.showOrHideNormalBite();
            displayPage.moveNormalBiteSlider(25);
            displayPage.moveNormalBiteSlider(76);
            displayPage.moveNormalBiteSlider(100);
            displayPage.moveNormalBiteSlider(0);

            displayPage.checkExtralBite2();

            Boolean flag=displayPage.moveMandibularSlider(85);
            displayPage.getMandibularStatus();
            displayPage.getMaxillaryStatus();
            displayPage.showOrHideMandibular();
            displayPage.showOrHideMandibular();
            displayPage.showOrHideMaxillary();
            displayPage.showOrHideMaxillary();
            displayPage.moveMandibularSlider(20);
            displayPage.moveMandibularSlider(55);
            displayPage.moveMandibularSlider(50);
            displayPage.moveMandibularSlider(78);
            displayPage.moveMandibularSlider(0);
            displayPage.moveMandibularSlider(90);
            displayPage.moveMaxillarySlider(100);

            Assert.assertTrue("slider doesn't move to correct position",flag);*/
        }
    }


}
