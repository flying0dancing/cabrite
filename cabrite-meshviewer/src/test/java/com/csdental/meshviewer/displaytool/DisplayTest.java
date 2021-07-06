package com.csdental.meshviewer.displaytool;

import com.csdental.meshviewer.DisplayPage;
import com.csdental.meshviewer.MeshViewPage;
import com.csdental.meshviewer.TestManager;
import com.csdental.test.IComFolder;
import com.csdental.test.Reporter;
import org.junit.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

@Test(groups={"smoke","function_test"})
public class DisplayTest extends TestManager {
    //String uploadFileName="Common_HD3D/common.HD3D.off[1.0.3.600]/Franklin_Benjamin_[2021-03-03_09-51-18].dcm";
    String uploadFileName="FullArchWithExtraBite/a870192f-f6df-40ae-b134-a9d4f198945e_Orthodontics.dcm";
    public void checkCommonNormalBite(ITestContext context){
        Reporter.testStart();
        String caseFolder=context.getName()+"/";
        String importFile=context.getCurrentXmlTest().getParameter("importFile");
        String expectationFile=context.getCurrentXmlTest().getParameter("expectationFile");
        String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        String screenWithinCase=caseFolder+methodName;
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            uploadDCM(meshViewPage,importFile);
            DisplayPage displayPage=meshViewPage.clickDisplay();
            Reporter.log("Click Display tab on left bar");
            embededScreenShot(screenWithinCase+"_default","Default status in Display tab");
            int archCount=displayPage.getArchCount();
            Assert.assertEquals("Dental Arch should be 2",2,archCount);
            //displayPage.getArchCount();
            //displayPage.getBiteCount();
            int indexofMaxillary=displayPage.getIndexOfArchByName("Maxillary","Maxillary Anatomy");
            Reporter.log("Index of Maxillary (Anatomy) should be 1");
            Assert.assertEquals("Index of Maxillary (Anatomy) should be 1",1,indexofMaxillary);
            String defaultStatusOfMaxillary=displayPage.getMaxillaryStatus();
            Reporter.log("Default status of Maxillary (Anatomy) should be Show");
            Assert.assertEquals("Default status of Maxillary (Anatomy) should be Show","Show",defaultStatusOfMaxillary);
            int defaultValueofMaxillary=displayPage.getMaxillaryValue();
            Reporter.log("Default transparent value of Maxillary (Anatomy) should be 0");
            Assert.assertEquals("Default transparent value of Maxillary (Anatomy) should be 0",0,defaultValueofMaxillary);

            int indexofMandibular=displayPage.getIndexOfArchByName("Mandibular","Mandibular Anatomy");
            Reporter.log("Index of Mandibular (Anatomy) should be 2");
            Assert.assertEquals("Index of Mandibular (Anatomy) should be 2",2,indexofMandibular);
            String defaultStatusOfMandibular=displayPage.getMandibularStatus();
            Reporter.log("Default status of Mandibular (Anatomy) should be Show");
            Assert.assertEquals("Default status of Mandibular (Anatomy) should be Show","Show",defaultStatusOfMandibular);
            int defaultValueofMandibular=displayPage.getMandibularValue();
            Reporter.log("Default transparent value of Mandibular (Anatomy) should be 0");
            Assert.assertEquals("Default transparent value of Mandibular (Anatomy) should be 0",0,defaultValueofMandibular);
            int bitecount=displayPage.getBiteCount();
            Reporter.log("Display page should contains Normal Bite");
            Assert.assertTrue("Display page should contains Normal Bite",bitecount>0);
            Boolean isCheckedNormalBite=displayPage.isCheckedNormalBite();
            Reporter.log("Display page's Normal Bite should be checked");
            Assert.assertTrue("Display page's Normal Bite should be checked",isCheckedNormalBite);

            displayPage.checkNormalBite();
            String defaultNormalBiteStatus=displayPage.getNormalBiteStatus();
            Reporter.log("Default status of Normal bite should be Hide");
            Assert.assertEquals("Default status of Normal bite should be Hide","Hide",defaultNormalBiteStatus);
            int defaultValueofNormalBite=displayPage.getNormalBiteValue();
            Reporter.log("Default transparent value of Normal bite  should be 0");
            Assert.assertEquals("Default transparent value of Normal bite should be 0",0,defaultValueofNormalBite);

            displayPage.showOrHideNormalBite();
            embededScreenShot(screenWithinCase+"_ShowNormalBite","click to show Normal Bite");
            String statusNormalBite=displayPage.getNormalBiteStatus();
            Assert.assertEquals("Normal Bite should be Shown.","Show",statusNormalBite);
            displayPage.moveNormalBiteSlider(40);
            String lower=embededScreenShot(screenWithinCase+"_Transparent_40","move Normal Bite Slider to 40");
            displayPage.moveNormalBiteSlider(45);
            String middle=embededScreenShot(screenWithinCase+"_Transparent_45","move Normal Bite Slider to 45");
            displayPage.moveNormalBiteSlider(50);
            String higher=embededScreenShot(screenWithinCase+"_Transparent_50","move Normal Bite Slider to the half");
            int transparentData=displayPage.getNormalBiteValue();
            Assert.assertEquals("Normal Bite should be shown with transparent 50.",50,transparentData);
            embededCompareResult(lower,middle,higher, IComFolder.RESULT_ACTUAL_FOLDER +caseFolder, IComFolder.RESULT_EXPECTATION_FOLDER+expectationFile);
            displayPage.showOrHideNormalBite();
            embededScreenShot(screenWithinCase+"_HideNormalBite","click to hide Normal Bite");
            statusNormalBite=displayPage.getNormalBiteStatus();
            Assert.assertEquals("Normal Bite should be hidden.","Hide",statusNormalBite);
            Reporter.testEnd();
        }
    }

    public void checkCommonExtraBites(ITestContext context){
        Reporter.testStart();
        String caseFolder=context.getName()+"/";
        String importFile=context.getCurrentXmlTest().getParameter("importFile");
        String expectationFile=context.getCurrentXmlTest().getParameter("expectationFile");
        String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        String screenWithinCase=caseFolder+methodName;
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            uploadDCM(meshViewPage,importFile);
            DisplayPage displayPage=meshViewPage.clickDisplay();
            Reporter.log("Click Display tab on left bar");
            embededScreenShot(screenWithinCase+"_default","Default status in Display tab");

            int archCount=displayPage.getArchCount();
            Assert.assertEquals("Dental Arch should be 2",2,archCount);
            //displayPage.getArchCount();
            //displayPage.getBiteCount();
            int indexofMaxillary=displayPage.getIndexOfArchByName("Maxillary","Maxillary Anatomy");
            Reporter.log("Index of Maxillary (Anatomy) should be 1");
            Assert.assertEquals("Index of Maxillary (Anatomy) should be 1",1,indexofMaxillary);
            String defaultStatusOfMaxillary=displayPage.getMaxillaryStatus();
            Reporter.log("Default status of Maxillary (Anatomy) should be Show");
            Assert.assertEquals("Default status of Maxillary (Anatomy) should be Show","Show",defaultStatusOfMaxillary);
            int defaultValueofMaxillary=displayPage.getMaxillaryValue();
            Reporter.log("Default transparent value of Maxillary (Anatomy) should be 0");
            Assert.assertEquals("Default transparent value of Maxillary (Anatomy) should be 0",0,defaultValueofMaxillary);

            int indexofMandibular=displayPage.getIndexOfArchByName("Mandibular","Mandibular Anatomy");
            Reporter.log("Index of Mandibular (Anatomy) should be 2");
            Assert.assertEquals("Index of Mandibular (Anatomy) should be 2",2,indexofMandibular);
            String defaultStatusOfMandibular=displayPage.getMandibularStatus();
            Reporter.log("Default status of Mandibular (Anatomy) should be Show");
            Assert.assertEquals("Default status of Mandibular (Anatomy) should be Show","Show",defaultStatusOfMandibular);
            int defaultValueofMandibular=displayPage.getMandibularValue();
            Reporter.log("Default transparent value of Mandibular (Anatomy) should be 0");
            Assert.assertEquals("Default transparent value of Mandibular (Anatomy) should be 0",0,defaultValueofMandibular);

            int bitecount=displayPage.getBiteCount();
            Reporter.log("Display page Bite Count should be more than 1");
            Assert.assertTrue("Display page Bite Count should be more than 1",bitecount>1);
            Boolean isCheckedNormalBite=displayPage.isCheckedNormalBite();
            Reporter.log("Display page's Normal Bite should be checked");
            Assert.assertTrue("Display page's Normal Bite should be checked",isCheckedNormalBite);

            displayPage.checkNormalBite();
            String defaultNormalBiteStatus=displayPage.getNormalBiteStatus();
            Reporter.log("Default status of Normal bite should be Hide");
            Assert.assertEquals("Default status of Normal bite should be Hide","Hide",defaultNormalBiteStatus);
            int defaultValueofNormalBite=displayPage.getNormalBiteValue();
            Reporter.log("Default transparent value of Normal bite  should be 0");
            Assert.assertEquals("Default transparent value of Normal bite should be 0",0,defaultValueofNormalBite);

            for (int i = 1; i < 5; i++) {
                Reporter.log("========================================================");
                int index=i+1;
                String tmpName=screenWithinCase+i;
                displayPage.checkBite(index);
                embededScreenShot(tmpName+"_CheckExtraBite"+i,"check Extra Bite"+i);
                String status=displayPage.getBiteStatus(index);
                Assert.assertEquals("Extra Bite "+i+" should be hidden(default status).","Hide",status);
                displayPage.showOrHideBite(index);
                embededScreenShot(tmpName+"_ShowExtraBite"+i,"click to show Extra Bite"+i);
                status=displayPage.getBiteStatus(index);
                Assert.assertEquals("Extra Bite "+i+" should be shown.","Show",status);

                if(i==1){
                    displayPage.moveBiteSlider(index,40);
                    String lower=embededScreenShot(tmpName+"_Transparent_40","move Normal Bite Slider to 40");
                    displayPage.moveBiteSlider(index,45);
                    String middle=embededScreenShot(tmpName+"_Transparent_45","move Normal Bite Slider to 45");
                    displayPage.moveBiteSlider(index,50);
                    String higher=embededScreenShot(tmpName+"_Transparent_50","move Extra Bite "+i+" Slider to the half");
                    int transparentData=displayPage.getBiteValue(index);
                    Assert.assertEquals("Extra Bite "+i+" should be shown with transparent 50.",50,transparentData);
                    embededCompareResult(lower,middle,higher, IComFolder.RESULT_ACTUAL_FOLDER +caseFolder, IComFolder.RESULT_EXPECTATION_FOLDER+expectationFile);
                }else{
                    displayPage.moveBiteSlider(index,50);
                    String higher=embededScreenShot(tmpName+"_Transparent_50","move Extra Bite "+i+" Slider to the half");
                }
                displayPage.showOrHideBite(index);
                embededScreenShot(tmpName+"_HideExtraBite"+i,"click to hide Extra Bite "+i);
                status=displayPage.getBiteStatus(index);
                Assert.assertEquals("Extra Bite "+i+" should be hidden.","Hide",status);
            }

        }

        Reporter.testEnd();
    }

}
