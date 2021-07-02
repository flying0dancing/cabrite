package com.csdental.meshviewer.displaytool;

import com.csdental.meshviewer.MeshViewPage;
import com.csdental.meshviewer.OrientationAdjustment;
import com.csdental.meshviewer.TestManager;
import com.csdental.test.IComFolder;
import com.csdental.util.ImageUtil;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.csdental.test.Reporter;


public class OrientationAdjustmentTest  extends TestManager {
    String uploadFileName="Common_HD3D/common.HD3D.off[1.0.3.600]/Franklin_Benjamin_[2021-03-03_09-51-18].dcm";


    @Test
    public void checkRotationXIncreaseMaximum(ITestContext context) throws Exception {
        Reporter.testStart();
        String importFile=context.getCurrentXmlTest().getParameter("importFile");
        String expectationFile=context.getCurrentXmlTest().getParameter("expectationFile");
        String name=Thread.currentThread().getStackTrace()[1].getMethodName();
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){

            uploadDCM(meshViewPage,importFile);

            OrientationAdjustment oa= meshViewPage.clickOrientationAdjustment();
            Reporter.log("click Orientation Adjustment tab on left bar");
            String text="0.5";
            Reporter.log("select rotation step "+text);
            Assert.assertEquals(oa.selectRotationStep(text),text,"Cann't select "+text);

            Integer angle=178;//181
            String angleResult=oa.clickXAnglePlus(angle);
            String lower=embededScreenShot(name+"_lower_"+angleResult);
            Reporter.log("click X Angle Increase("+angle.toString()+" times), get angle value is "+angleResult);

            oa.clickXAnglePlus();//179
            Reporter.log("click X Angle Reset(1 times), get angle value is "+angleResult);
            String middle=embededScreenShot(name+"_middle_"+angleResult);

            oa.clickXAnglePlus();
            Reporter.log("click X Angle Reset(1 times), get angle value is "+angleResult);
            String higher=embededScreenShot(name+"_higher_"+angleResult);

            double tolerance=ImageUtil.getTolerance(lower,middle,higher, IComFolder.RESULT_ACTUAL_FOLDER +context.getName());
            System.out.println(String.format("in test tolerance  %.6f", tolerance));

            //TODO IComFolder.SOURCE_FOLDER add compare function
            double actual_tolerance=ImageUtil.compareWithExpectation(higher,IComFolder.SOURCE_EXPECTATION_FOLDER+expectationFile,IComFolder.RESULT_ACTUAL_FOLDER +context.getName());
            System.out.println(String.format("actual_tolerance  %.6f", tolerance));

            angleResult=oa.clickXAnglePlus();
            Reporter.log("click X Angle Reset(1 times), get angle value is "+angleResult);
            embededScreenShot(name+"_max_"+angleResult);
            Assert.assertEquals(angleResult,"90","angle value is not same as expected result");


            angleResult=oa.clickXAngleReset();
            Reporter.log("click X Angle Reset(1 times), get angle value is "+angleResult);
            embededScreenShot(name+"_reset_"+angleResult);
            Assert.assertEquals(angleResult,"0","angle value is not same as expected result");
        }
        Reporter.testEnd();
    }

}
