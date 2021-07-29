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

    public void checkMovementZMaximum(ITestContext context) {
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
            String valueOnLabel=oa.clickDistancePlus(clickTimes);
            String lower=embededScreenShot(screenWithinCase+"_distance_"+valueOnLabel,"click Z plus("+clickTimes.toString()+" times), get distance is "+valueOnLabel);
            Assert.assertEquals(valueOnLabel,"4.9","distance is not same as expected result");

            clickTimes=1;
            valueOnLabel=oa.clickDistancePlus();
            String middle=embededScreenShot(screenWithinCase+"_distance_"+valueOnLabel,"click Z plus("+clickTimes.toString()+" times), get distance is "+valueOnLabel);
            Assert.assertEquals(valueOnLabel,"4.95","distance is not same as expected result");

            text="0.1";
            Reporter.log("select Movement step "+text);
            Assert.assertEquals(oa.selectMovementStep(text),text,"Cann't select "+text);

            valueOnLabel=oa.clickDistancePlus(clickTimes);
            String higher=embededScreenShot(screenWithinCase+"_distance_"+valueOnLabel,"click Z plus("+clickTimes.toString()+" times), get distance is "+valueOnLabel);
            Assert.assertEquals(valueOnLabel,"5","distance is not same as expected result");

            embededCompareResult(lower,middle,higher, IComFolder.RESULT_ACTUAL_FOLDER +caseFolder, IComFolder.RESULT_EXPECTATION_FOLDER+expectationFile);

            valueOnLabel=oa.clickDistanceReset();
            embededScreenShot(screenWithinCase+"_reset_"+valueOnLabel,"click Z reset(1 times), get distance is "+valueOnLabel);
            Assert.assertEquals(valueOnLabel,"0","distance is not same as expected result");
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
            String text="0.1";
            Reporter.log("select Movement step "+text);
            Assert.assertEquals(oa.selectMovementStep(text),text,"Cann't select "+text);

            Integer clickTimes=30;
            String disResult=oa.clickDistanceMinus(clickTimes);
            embededScreenShot(screenWithinCase+"_distance_"+disResult,"click Z minus("+clickTimes.toString()+" times), get distance is "+disResult);
            Assert.assertEquals(disResult,"-3","distance is not same as expected result");

            clickTimes=10;
            disResult=oa.clickDistancePlus(clickTimes);
            String lower=embededScreenShot(screenWithinCase+"_distance_"+disResult,"click Z plus("+clickTimes.toString()+" times), get distance is "+disResult);
            Assert.assertEquals(disResult,"-2","distance is not same as expected result");

            disResult=oa.clickDistancePlus(clickTimes);
            String middle=embededScreenShot(screenWithinCase+"_distance_"+disResult,"click Z plus("+clickTimes.toString()+" times), get distance is "+disResult);
            Assert.assertEquals(disResult,"-1","distance is not same as expected result");

            disResult=oa.clickDistanceReset();
            String higher=embededScreenShot(screenWithinCase+"_distance_"+disResult,"click Z reset(1 times), get distance is "+disResult);
            Assert.assertEquals(disResult,"0","distance is not same as expected result");

            embededCompareResult(lower,middle,higher, IComFolder.RESULT_ACTUAL_FOLDER +caseFolder, IComFolder.RESULT_EXPECTATION_FOLDER+expectationFile);

        }
        Reporter.testEnd();
    }

    //@Test(enabled = false)
    public void checkMovementZMinimum(ITestContext context) throws Exception {
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
            String higher=embededScreenShot(screenWithinCase+"_distance_"+disResult,"click Z minus("+clickTimes.toString()+" times), get distance is "+disResult);

            clickTimes=5;
            disResult=oa.clickDistanceMinus(clickTimes);
            String middle=embededScreenShot(screenWithinCase+"_distance_"+disResult,"click Z minus("+clickTimes.toString()+" times), get distance is "+disResult);

            clickTimes=6;
            disResult=oa.clickDistanceMinus(clickTimes);//click times: 51
            String lower=embededScreenShot(screenWithinCase+"_distance_"+disResult,"click Z minus("+clickTimes.toString()+" times), get distance is "+disResult);
            Assert.assertEquals(disResult,"-5","distance is not same as expected result");

            embededCompareResult(higher,middle,lower, IComFolder.RESULT_ACTUAL_FOLDER +caseFolder, IComFolder.RESULT_EXPECTATION_FOLDER+expectationFile);

            disResult=oa.clickDistanceReset();
            embededScreenShot(screenWithinCase+"_reset_"+disResult,"click Z reset(1 times), get distance is "+disResult);
            Assert.assertEquals(disResult,"0","distance is not same as expected result");
        }
        Reporter.testEnd();
    }

}
