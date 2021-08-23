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

    /**
     * related to QC case 2.1.2 Display Tools_Adjust Oritation_Rotation
     * @param context
     */
    @Test
    public void checkRotationXMaximum(ITestContext context) throws Exception {
        Reporter.testStart();
        String caseFolder=context.getName()+"/";
        String importFile=context.getCurrentXmlTest().getParameter("importFile");
        String expectationFile=context.getCurrentXmlTest().getParameter("expectationFile");
        String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        String screenWithinCase=caseFolder+methodName;
        MeshViewPage meshViewPage=getMeshViewPage();
        if(meshViewPage.isThePage()){

            uploadDCM(meshViewPage,importFile);

            OrientationAdjustment oa= meshViewPage.clickOrientationAdjustment();
            Reporter.log("click Orientation Adjustment tab on left bar");
            String text="0.5";
            Reporter.log("select rotation step "+text);
            Assert.assertEquals(oa.selectRotationStep(text),text,"Cann't select "+text);

            Integer clickTimes=178;//181
            String angleResult=oa.clickXAnglePlus(clickTimes);
            String lower=embededScreenShot(screenWithinCase+"_angle_"+angleResult,"click X plus("+clickTimes.toString()+" times), get angle is "+angleResult);

            clickTimes=1;
            angleResult=oa.clickXAnglePlus();//179
            String middle=embededScreenShot(screenWithinCase+"_angle_"+angleResult,"click X plus("+clickTimes.toString()+" times), get angle is "+angleResult);

            clickTimes=2;
            angleResult=oa.clickXAnglePlus(clickTimes);
            String higher=embededScreenShot(screenWithinCase+"_angle_"+angleResult,"click X plus("+clickTimes.toString()+" times), get angle is "+angleResult);
            //compare result
            embededCompareResult(lower,middle,higher, IComFolder.RESULT_ACTUAL_FOLDER +caseFolder, IComFolder.RESULT_EXPECTATION_FOLDER+expectationFile);

            Assert.assertEquals(angleResult,"90","angle value is not same as expected result");

            angleResult=oa.clickXAngleReset();
            embededScreenShot(screenWithinCase+"_reset_"+angleResult,"click X reset(1 times), get angle is "+angleResult);
            Assert.assertEquals(angleResult,"0","angle value is not same as expected result");
        }
        Reporter.testEnd();
    }

    /**
     * related to QC case 2.1.2 Display Tools_Adjust Oritation_Rotation
     * @param context
     */
    @Test
    public void checkRotationXMinimum(ITestContext context){
        Reporter.testStart();
        String caseFolder=context.getName()+"/";
        String importFile=context.getCurrentXmlTest().getParameter("importFile");
        String expectationFile=context.getCurrentXmlTest().getParameter("expectationFile");
        String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        String screenWithinCase=caseFolder+methodName;
        MeshViewPage meshViewPage=getMeshViewPage();
        if(meshViewPage.isThePage()){

            uploadDCM(meshViewPage,importFile);

            OrientationAdjustment oa= meshViewPage.clickOrientationAdjustment();

            Reporter.log("click Orientation Adjustment tab on left bar");
            String text="1.0";
            Reporter.log("select rotation step "+text);
            Assert.assertEquals(oa.selectRotationStep(text),text,"Cann't select "+text);

            Integer clickTimes=88;
            String clickResult=oa.clickXAngleMinus(clickTimes);
            String higher=embededScreenShot(screenWithinCase+"_angle_"+clickResult,"click X minus("+clickTimes.toString()+" times), get angle is "+clickResult);
            Assert.assertEquals(clickResult,"-88","angle is not same as expected result");

            clickTimes=1;
            clickResult=oa.clickXAngleMinus();
            String middle=embededScreenShot(screenWithinCase+"_angle_"+clickResult,"click X minus("+clickTimes.toString()+" times), get angle is "+clickResult);
            Assert.assertEquals(clickResult,"-89","angle is not same as expected result");

            text="5.0";
            Reporter.log("select rotation step "+text);
            Assert.assertEquals(oa.selectRotationStep(text),text,"Cann't select "+text);

            clickTimes=1;
            clickResult=oa.clickXAngleMinus(clickTimes);
            String lower=embededScreenShot(screenWithinCase+"_angle_"+clickResult,"click X minus("+clickTimes.toString()+" times), get angle is "+clickResult);
            Assert.assertEquals(clickResult,"-90","angle is not same as expected result");

            embededCompareResult(higher,middle,lower, IComFolder.RESULT_ACTUAL_FOLDER +caseFolder, IComFolder.RESULT_EXPECTATION_FOLDER+expectationFile);

            clickResult=oa.clickXAngleReset();
            embededScreenShot(screenWithinCase+"_reset_"+clickResult,"click X reset(1 times), get angle is "+clickResult);
            Assert.assertEquals(clickResult,"0","angle is not same as expected result");
        }
        Reporter.testEnd();
    }

    /**
     * related to QC case 2.1.2 Display Tools_Adjust Oritation_Rotation
     * @param context
     */
    @Test
    public void checkRotationXReset(ITestContext context){
        Reporter.testStart();
        String caseFolder=context.getName()+"/";
        String importFile=context.getCurrentXmlTest().getParameter("importFile");
        String expectationFile=context.getCurrentXmlTest().getParameter("expectationFile");
        String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        String screenWithinCase=caseFolder+methodName;
        MeshViewPage meshViewPage=getMeshViewPage();
        if(meshViewPage.isThePage()){

            uploadDCM(meshViewPage,importFile);

            OrientationAdjustment oa= meshViewPage.clickOrientationAdjustment();

            Reporter.log("click Orientation Adjustment tab on left bar");
            String text="0.5";
            Reporter.log("select rotation step "+text);
            Assert.assertEquals(oa.selectRotationStep(text),text,"Cann't select "+text);

            Integer clickTimes=3;
            String clickResult=oa.clickXAngleMinus(clickTimes);
            embededScreenShot(screenWithinCase+"_angle_"+clickResult,"click X minus("+clickTimes.toString()+" times), get angle is "+clickResult);
            Assert.assertEquals(clickResult,"-1.5","angle is not same as expected result");

            clickTimes=1;
            clickResult=oa.clickXAnglePlus();
            String lower=embededScreenShot(screenWithinCase+"_angle_"+clickResult,"click X plus("+clickTimes.toString()+" times), get angle is "+clickResult);
            Assert.assertEquals(clickResult,"-1","angle is not same as expected result");

            clickResult=oa.clickXAnglePlus();
            String middle=embededScreenShot(screenWithinCase+"_angle_"+clickResult,"click X plus("+clickTimes.toString()+" times), get angle is "+clickResult);
            Assert.assertEquals(clickResult,"-0.5","angle is not same as expected result");

            clickResult=oa.clickXAngleReset();
            String higher=embededScreenShot(screenWithinCase+"_angle_"+clickResult,"click X reset(1 times), get angle is "+clickResult);
            Assert.assertEquals(clickResult,"0","angle is not same as expected result");

            embededCompareResult(lower,middle,higher, IComFolder.RESULT_ACTUAL_FOLDER +caseFolder, IComFolder.RESULT_EXPECTATION_FOLDER+expectationFile);

        }
        Reporter.testEnd();
    }

    /**
     * related to QC case 2.1.2 Display Tools_Adjust Oritation_Rotation
     * @param context
     */
    @Test
    public void checkRotationYReset(ITestContext context){
        Reporter.testStart();
        String caseFolder=context.getName()+"/";
        String importFile=context.getCurrentXmlTest().getParameter("importFile");
        String expectationFile=context.getCurrentXmlTest().getParameter("expectationFile");
        String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        String screenWithinCase=caseFolder+methodName;
        MeshViewPage meshViewPage=getMeshViewPage();
        if(meshViewPage.isThePage()){

            uploadDCM(meshViewPage,importFile);

            OrientationAdjustment oa= meshViewPage.clickOrientationAdjustment();

            Reporter.log("click Orientation Adjustment tab on left bar");
            String text="0.5";
            Reporter.log("select rotation step "+text);
            Assert.assertEquals(oa.selectRotationStep(text),text,"Cann't select "+text);

            Integer clickTimes=3;
            String clickResult=oa.clickYAngleMinus(clickTimes);
            embededScreenShot(screenWithinCase+"_angle_"+clickResult,"click Y minus("+clickTimes.toString()+" times), get angle is "+clickResult);
            Assert.assertEquals(clickResult,"-1.5","angle is not same as expected result");

            clickTimes=1;
            clickResult=oa.clickYAnglePlus();
            String lower=embededScreenShot(screenWithinCase+"_angle_"+clickResult,"click Y plus("+clickTimes.toString()+" times), get angle is "+clickResult);
            Assert.assertEquals(clickResult,"-1","angle is not same as expected result");

            clickResult=oa.clickYAnglePlus();
            String middle=embededScreenShot(screenWithinCase+"_angle_"+clickResult,"click Y plus("+clickTimes.toString()+" times), get angle is "+clickResult);
            Assert.assertEquals(clickResult,"-0.5","angle is not same as expected result");

            clickResult=oa.clickYAngleReset();
            String higher=embededScreenShot(screenWithinCase+"_angle_"+clickResult,"click Y reset(1 times), get angle is "+clickResult);
            Assert.assertEquals(clickResult,"0","angle is not same as expected result");

            embededCompareResult(lower,middle,higher, IComFolder.RESULT_ACTUAL_FOLDER +caseFolder, IComFolder.RESULT_EXPECTATION_FOLDER+expectationFile);

        }
        Reporter.testEnd();
    }

    /**
     * related to QC case 2.1.2 Display Tools_Adjust Oritation_Rotation
     * @param context
     */
    @Test
    public void checkRotationZReset(ITestContext context){
        Reporter.testStart();
        String caseFolder=context.getName()+"/";
        String importFile=context.getCurrentXmlTest().getParameter("importFile");
        String expectationFile=context.getCurrentXmlTest().getParameter("expectationFile");
        String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        String screenWithinCase=caseFolder+methodName;
        MeshViewPage meshViewPage=getMeshViewPage();
        if(meshViewPage.isThePage()){

            uploadDCM(meshViewPage,importFile);

            OrientationAdjustment oa= meshViewPage.clickOrientationAdjustment();

            Reporter.log("click Orientation Adjustment tab on left bar");
            String text="0.5";
            Reporter.log("select rotation step "+text);
            Assert.assertEquals(oa.selectRotationStep(text),text,"Cann't select "+text);

            Integer clickTimes=3;
            String clickResult=oa.clickZAngleMinus(clickTimes);
            embededScreenShot(screenWithinCase+"_angle_"+clickResult,"click Z minus("+clickTimes.toString()+" times), get angle is "+clickResult);
            Assert.assertEquals(clickResult,"-1.5","angle is not same as expected result");

            clickTimes=1;
            clickResult=oa.clickZAnglePlus();
            String lower=embededScreenShot(screenWithinCase+"_angle_"+clickResult,"click Z plus("+clickTimes.toString()+" times), get angle is "+clickResult);
            Assert.assertEquals(clickResult,"-1","angle is not same as expected result");

            clickResult=oa.clickZAnglePlus();
            String middle=embededScreenShot(screenWithinCase+"_angle_"+clickResult,"click Z plus("+clickTimes.toString()+" times), get angle is "+clickResult);
            Assert.assertEquals(clickResult,"-0.5","angle is not same as expected result");

            clickResult=oa.clickZAngleReset();
            String higher=embededScreenShot(screenWithinCase+"_angle_"+clickResult,"click Z reset(1 times), get angle is "+clickResult);
            Assert.assertEquals(clickResult,"0","angle is not same as expected result");

            embededCompareResult(lower,middle,higher, IComFolder.RESULT_ACTUAL_FOLDER +caseFolder, IComFolder.RESULT_EXPECTATION_FOLDER+expectationFile);

        }
        Reporter.testEnd();
    }

    /**
     * related to QC case 2.1.2 Display Tools_Adjust Oritation_Reset all adjustments
     * @param context
     */
    @Test
    public void checkResetAll(ITestContext context){
        Reporter.testStart();
        String caseFolder=context.getName()+"/";
        String importFile=context.getCurrentXmlTest().getParameter("importFile");
        String expectationFile=context.getCurrentXmlTest().getParameter("expectationFile");
        String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        String screenWithinCase=caseFolder+methodName;
        MeshViewPage meshViewPage=getMeshViewPage();
        if(meshViewPage.isThePage()){

            uploadDCM(meshViewPage,importFile);

            OrientationAdjustment oa= meshViewPage.clickOrientationAdjustment();

            Reporter.log("click Orientation Adjustment tab on left bar");
            String lower=embededScreenShot(screenWithinCase+"_default","default status");
            String text="0.5";
            Reporter.log("select rotation step "+text);
            Assert.assertEquals(oa.selectRotationStep(text),text,"Cann't select "+text);

            Integer clickTimes=3;
            String clickResult=oa.clickXAngleMinus(clickTimes);
            embededScreenShot(screenWithinCase+"_AngleX_"+clickResult,"click X minus("+clickTimes.toString()+" times), get angle is "+clickResult);
            Assert.assertEquals(clickResult,"-1.5","angle is not same as expected result");

            clickResult=oa.clickYAngleMinus(clickTimes);
            embededScreenShot(screenWithinCase+"_AngleY_"+clickResult,"click Y minus("+clickTimes.toString()+" times), get angle is "+clickResult);
            Assert.assertEquals(clickResult,"-1.5","angle is not same as expected result");

            clickResult=oa.clickZAngleMinus(clickTimes);
            embededScreenShot(screenWithinCase+"_AngleZ_"+clickResult,"click Z minus("+clickTimes.toString()+" times), get angle is "+clickResult);
            Assert.assertEquals(clickResult,"-1.5","angle is not same as expected result");

            text="0.1";
            Reporter.log("select Movement step "+text);
            Assert.assertEquals(oa.selectMovementStep(text),text,"Cann't select "+text);
            clickResult=oa.clickDistancePlus(clickTimes);
            embededScreenShot(screenWithinCase+"_distance_"+clickResult,"click distance Z plus("+clickTimes.toString()+" times), get distance is "+clickResult);
            Assert.assertEquals(clickResult,"0.3","angle is not same as expected result");

            Boolean resetAll=oa.clickResetAllAdjustments();
            String higher=embededScreenShot(screenWithinCase+"_resetAll","click Reset ALL Adjustments");
            Assert.assertTrue(resetAll,"It doesn't reset all adjustments.");

            embededCompareResult(lower,lower,higher, IComFolder.RESULT_ACTUAL_FOLDER +caseFolder, IComFolder.RESULT_EXPECTATION_FOLDER+expectationFile);

        }
        Reporter.testEnd();
    }


}
