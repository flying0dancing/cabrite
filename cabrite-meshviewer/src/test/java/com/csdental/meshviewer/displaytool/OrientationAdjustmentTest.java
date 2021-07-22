package com.csdental.meshviewer.displaytool;

import com.csdental.meshviewer.MeshViewPage;
import com.csdental.meshviewer.OrientationAdjustment;
import com.csdental.meshviewer.TestManager;
import com.csdental.test.IComFolder;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.csdental.test.Reporter;


public class OrientationAdjustmentTest  extends TestManager {
    String uploadFileName="Common_HD3D/common.HD3D.off[1.0.3.600]/Franklin_Benjamin_[2021-03-03_09-51-18].dcm";

    @Test
    public void checkRotationXIncreaseMaximum(ITestContext context) throws Exception {
        Reporter.testStart();
        String caseFolder=context.getName()+"/";
        String importFile=context.getCurrentXmlTest().getParameter("importFile");
        String expectationFile=context.getCurrentXmlTest().getParameter("expectationFile");
        String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        String screenWithinCase=caseFolder+methodName;
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){

            uploadDCM(meshViewPage,importFile);

            OrientationAdjustment oa= meshViewPage.clickOrientationAdjustment();
            Reporter.log("click Orientation Adjustment tab on left bar");
            String text="0.5";
            Reporter.log("select rotation step "+text);
            Assert.assertEquals(oa.selectRotationStep(text),text,"Cann't select "+text);

            Integer angle=178;
            String angleResult=oa.clickXAnglePlus(angle);
            String lower=embededScreenShot(screenWithinCase+"_lower_"+angleResult,"click X Angle Increase("+angle.toString()+" times), get angle value is "+angleResult);
            Assert.assertEquals(angleResult,"89","angle should be same as expected result");

            angleResult=oa.clickXAnglePlus();//179
            String middle=embededScreenShot(screenWithinCase+"_middle_"+angleResult,"click X Angle Reset(1 times), get angle value is "+angleResult);
            Assert.assertEquals(angleResult,"89.5","angle should be same as expected result");


            angleResult=oa.clickXAnglePlus();
            String higher=embededScreenShot(screenWithinCase+"_higher_"+angleResult,"click X Angle Reset(1 times), get angle value is "+angleResult);
            Assert.assertEquals(angleResult,"90","angle should be same as expected result");
            //compare result
            embededCompareResult(lower,middle,higher, IComFolder.RESULT_ACTUAL_FOLDER +caseFolder, IComFolder.RESULT_EXPECTATION_FOLDER+expectationFile);

            angleResult=oa.clickXAnglePlus();
            embededScreenShot(screenWithinCase+"_max_"+angleResult,"click X Angle Reset(1 times), get angle value is "+angleResult);
            Assert.assertEquals(angleResult,"90","angle value should be same as expected result");

            angleResult=oa.clickXAngleReset();
            embededScreenShot(screenWithinCase+"_reset_"+angleResult,"click X Angle Reset(1 times), get angle value is "+angleResult);
            Assert.assertEquals(angleResult,"0","angle value should be same as expected result");
        }
        Reporter.testEnd();
    }

}
