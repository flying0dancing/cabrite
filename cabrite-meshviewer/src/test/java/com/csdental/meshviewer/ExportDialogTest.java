package com.csdental.meshviewer;

import com.csdental.test.Reporter;
import org.junit.Assert;
import org.testng.annotations.Test;

public class ExportDialogTest extends TestManager{
    String uploadFileName="Common_HD3D/common.HD3D.off[1.0.3.600]/Franklin_Benjamin_[2021-03-03_09-51-18].dcm";
    @Test
    public void exportDefaultCheck(){
        Reporter.testStart();
        String caseFolder="export";
        //String caseFolder=context.getName()+"/";
        //String importFile=context.getCurrentXmlTest().getParameter("importFile");
        //String expectationFile=context.getCurrentXmlTest().getParameter("expectationFile");
        String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        String screenWithinCase=caseFolder+methodName;
        MeshViewPage meshViewPage=getMeshViewPage();
        if(meshViewPage.isThePage()){
            uploadDCM(meshViewPage,uploadFileName);
            ExportPage exportPage=meshViewPage.clickExport();
            Reporter.log("Click export tab on left bar");
            embededScreenShot(screenWithinCase+"_default","default status in export tab");
            if(exportPage.isThePage()){
                Assert.assertEquals("Default export format should be STL",exportPage.getSelectedExportFormat(),"stl");
                Assert.assertFalse("Default exportWithOrientationPresets is selected",exportPage.isSelectedExportWithOrientationPresets());
                Assert.assertFalse("Default exportWithOrientationPresets is enabled",exportPage.isSelectedOrientation());
            }
        }
        Reporter.testEnd();
    }

    @Test(enabled = false)
    public void exportPLYCheck(){
        Reporter.testStart();
        String caseFolder="export";
        //String caseFolder=context.getName()+"/";
        //String importFile=context.getCurrentXmlTest().getParameter("importFile");
        //String expectationFile=context.getCurrentXmlTest().getParameter("expectationFile");
        String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        String screenWithinCase=caseFolder+methodName;
        MeshViewPage meshViewPage=getMeshViewPage();
        if(meshViewPage.isThePage()){
            uploadDCM(meshViewPage,uploadFileName);
            ExportPage exportPage=meshViewPage.clickExport();
            Reporter.log("Click export tab on left bar");
            embededScreenShot(screenWithinCase+"_default","default status in export tab");
            if(exportPage.isThePage()){
                Reporter.log("Select PLY on left bar");
                embededScreenShot(screenWithinCase+"_PLY","select PLY in export tab");
                exportPage.selectedPLY();
                System.out.println(exportPage.getSelectedExportFormat());
                exportPage.checkExportWithOrientationPresets();
                if(exportPage.select3Shape()){
                    embededScreenShot(screenWithinCase+"_PLY_3Shape","select Orientation 3Shape in export tab");
                    System.out.println("3Shape......");
                    exportPage.clickExport();
                }
                if(exportPage.selectDentalWings()){
                    embededScreenShot(screenWithinCase+"_PLY_DentalWings","select Orientation DentalWings in export tab");
                    System.out.println("DentalWings....");
                }
                if(exportPage.selectExocad()){
                    embededScreenShot(screenWithinCase+"_PLY_Exocad","select Orientation Exocad in export tab");
                    System.out.println("Exocad....");
                }
            }
            System.out.println("end");
        }
        Reporter.testEnd();
    }

}
