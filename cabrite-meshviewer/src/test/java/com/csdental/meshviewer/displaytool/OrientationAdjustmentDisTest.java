package com.csdental.meshviewer.displaytool;

import com.csdental.meshviewer.MeshViewPage;
import com.csdental.meshviewer.OrientationAdjustment;
import com.csdental.meshviewer.TestManager;
import com.csdental.test.IComFolder;
import org.testng.Assert;
import org.testng.ITestContext;
import com.csdental.test.Reporter;
import org.testng.annotations.Test;

@Test(groups={"smoke","function_test"}, description="maximun value of moved default lower jaw in movement adjustment")
public class OrientationAdjustmentDisTest extends TestManager {
    String uploadFileName="Common_HD3D/common.HD3D.off[1.0.3.600]/Franklin_Benjamin_[2021-03-03_09-51-18].dcm";
    //String uploadFileName="Common+Preparation/c1873bee-98ff-4e48-b9bc-b92064636987_Restore.dcm";

    public void checkMovementZIncreaseMaximum(ITestContext context) {
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
            String text="0.05";
            Reporter.log("select Movement step "+text);
            Assert.assertEquals(oa.selectMovementStep(text),text,"Cann't select "+text);

            Integer clickTimes=98;
            String disResult=oa.clickDistancePlus(clickTimes);
            String lower=embededScreenShot(screenWithinCase+"_distance_"+disResult,"click clickTimes Increase("+clickTimes.toString()+" times), get clickTimes is "+disResult+"[lower]");
            Assert.assertEquals(disResult,"4.9","clickTimes is not same as expected result");

            disResult=oa.clickDistancePlus();
            String middle=embededScreenShot(screenWithinCase+"_distance_"+disResult,"click clickTimes Increase(1 times), get clickTimes is "+disResult+"[middle]");
            Assert.assertEquals(disResult,"4.95","clickTimes is not same as expected result");

            text="0.1";
            Reporter.log("select Movement step "+text);
            Assert.assertEquals(oa.selectMovementStep(text),text,"Cann't select "+text);

            clickTimes=1;
            disResult=oa.clickDistancePlus(clickTimes);
            String higher=embededScreenShot(screenWithinCase+"_max_"+disResult,"click clickTimes Increase("+clickTimes.toString()+" times), get clickTimes is "+disResult+"[higher]");
            Assert.assertEquals(disResult,"5","clickTimes is not same as expected result");

            embededCompareResult(lower,middle,higher, IComFolder.RESULT_ACTUAL_FOLDER +caseFolder, IComFolder.RESULT_EXPECTATION_FOLDER+expectationFile);

            disResult=oa.clickDistanceReset();
            embededScreenShot(screenWithinCase+"_reset_"+disResult,"click clickTimes Reset(1 times), get clickTimes is "+disResult);
            Assert.assertEquals(disResult,"0","clickTimes is not same as expected result");
        }
        Reporter.testEnd();
    }

    public void checkMovementZReset(ITestContext context) {
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
            String text="0.01";
            Reporter.log("select rotation step "+text);
            Assert.assertEquals(oa.selectMovementStep(text),text,"Cann't select "+text);

            Integer clickTimes=50;
            String disResult=oa.clickDistancePlus(clickTimes);
            String higher=embededScreenShot(screenWithinCase+"_distance_"+disResult,"click distance Increase(50 times), get distance is "+disResult+"[higher]");
            Assert.assertEquals(disResult,"0.5","distance is not same as expected result");

            disResult=oa.clickDistanceMinus(clickTimes);
            embededScreenShot(screenWithinCase+"_distance_"+disResult,"click distance Decrease(50 times), get distance is "+disResult);
            Assert.assertEquals(disResult,"0","distance is not same as expected result");

            disResult=oa.clickDistanceMinus(clickTimes);
            String lower=embededScreenShot(screenWithinCase+"_distance_"+disResult,"click distance Decrease(50 times), get distance is "+disResult+"[lower]");
            Assert.assertEquals(disResult,"-0.5","distance is not same as expected result");

            disResult=oa.clickDistanceReset();
            String middle=embededScreenShot(screenWithinCase+"_reset_"+disResult,"click distance Reset(1 times), get distance is "+disResult+"[middle]");
            Assert.assertEquals(disResult,"0","distance is not same as expected result");

            embededCompareResult(lower,middle,higher, IComFolder.RESULT_ACTUAL_FOLDER +caseFolder, IComFolder.RESULT_EXPECTATION_FOLDER+expectationFile);

        }
        Reporter.testEnd();
    }

    //@Test(enabled = false)
    public void checkMovementZDecreaseMinimum(ITestContext context) throws Exception {
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
            String text="0.1";
            Reporter.log("select Movement step "+text);
            Assert.assertEquals(oa.selectMovementStep(text),text,"Cann't select "+text);

            Integer clickTimes=40;
            String disResult=oa.clickDistanceMinus(clickTimes);
            String lower=embededScreenShot(screenWithinCase+"_distance_"+disResult,"click distance Decrease("+clickTimes.toString()+" times), get distance is "+disResult+"[lower]");

            clickTimes=5;
            disResult=oa.clickDistanceMinus(clickTimes);
            String middle=embededScreenShot(screenWithinCase+"_distance_"+disResult,"click distance Decrease("+clickTimes.toString()+" times), get distance is "+disResult+"[middle]");

            clickTimes=6;
            disResult=oa.clickDistanceMinus(clickTimes);//click times: 51
            String higher=embededScreenShot(screenWithinCase+"_distance_"+disResult,"click distance Decrease("+clickTimes.toString()+" times), get distance is "+disResult+"[higher]");
            Assert.assertEquals(disResult,"-5","distance is not same as expected result");

            embededCompareResult(lower,middle,higher, IComFolder.RESULT_ACTUAL_FOLDER +caseFolder, IComFolder.RESULT_EXPECTATION_FOLDER+expectationFile);

            disResult=oa.clickDistanceReset();
            embededScreenShot(screenWithinCase+"_reset_"+disResult,"click distance Reset(1 times), get distance is "+disResult);
            Assert.assertEquals(disResult,"0","distance is not same as expected result");
        }
        Reporter.testEnd();
    }

}
