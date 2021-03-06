package com.csdental.meshviewer.displaytool;

import com.csdental.meshviewer.MeshViewPage;
import com.csdental.meshviewer.OrientationAdjustment;
import com.csdental.meshviewer.TestManager;
import com.csdental.test.IComFolder;
import com.csdental.util.ImageUtil;
import org.testng.Assert;
import org.testng.ITestContext;
import com.csdental.test.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(groups={"smoke","function_test"}, description="maximun value of moved default lower jaw in movement adjustment")
public class OrientationAdjustmentDisTest extends TestManager {
    String uploadFileName="Common_HD3D/common.HD3D.off[1.0.3.600]/Franklin_Benjamin_[2021-03-03_09-51-18].dcm";
    //String uploadFileName="Common+Preparation/c1873bee-98ff-4e48-b9bc-b92064636987_Restore.dcm";
    @Parameters({"importFile","expectationFile"})
    public void checkRotationXIncreaseMaximum(ITestContext context, @Optional("Common_HD3D/common.HD3D.off[1.0.3.600]/Franklin_Benjamin_[2021-03-03_09-51-18].dcm") String importFile, @Optional("http://localhost:3001/") String expectationFile) throws Exception {
        Reporter.testStart();

        String name=Thread.currentThread().getStackTrace()[1].getMethodName();
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){

            uploadDCM(meshViewPage,uploadFileName);

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

            double tolerance=ImageUtil.getTolerance(lower,middle,higher, IComFolder.RESULT_EXPECTATION_FOLDER+context.getName());
            System.out.println(String.format("in test tolerance  %.6f", tolerance));

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


    public void checkMovementZIncreaseMaximumbk(ITestContext context) {
        Reporter.testStart();
        String name=Thread.currentThread().getStackTrace()[1].getMethodName();
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){

            uploadDCM(meshViewPage,uploadFileName);

            OrientationAdjustment oa= meshViewPage.clickOrientationAdjustment();
            Reporter.log("click Orientation Adjustment tab on left bar");
            String text="0.05";
            Reporter.log("select Movement step "+text);
            Assert.assertEquals(oa.selectMovementStep(text),text,"Cann't select "+text);
            Integer distance=99;

            String disResult=oa.clickDistancePlus(distance);
            Reporter.log("click distance Increase("+distance.toString()+" times), get distance is "+disResult);
            embededScreenShot(name+"_distance_"+disResult);
            Assert.assertEquals(disResult,"4.95","distance is not same as expected result");

            text="0.1";
            Reporter.log("select Movement step "+text);
            Assert.assertEquals(oa.selectMovementStep(text),text,"Cann't select "+text);

            distance=1;
            disResult=oa.clickDistancePlus(distance);
            Reporter.log("click distance Increase("+distance.toString()+" times), get distance is "+disResult);
            embededScreenShot(name+"_max_"+disResult);
            Assert.assertEquals(disResult,"5","distance is not same as expected result");


            disResult=oa.clickDistanceReset();
            Reporter.log("click distance Reset(1 times), get distance is "+disResult);
            embededScreenShot(name+"_reset_"+disResult);
            Assert.assertEquals(disResult,"0","distance is not same as expected result");
        }
        Reporter.testEnd();
    }

    //@Test(enabled = false)
    public void checkMovementZReset() {
        String name=Thread.currentThread().getStackTrace()[1].getMethodName();
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){

            uploadDCM(meshViewPage,uploadFileName);

            OrientationAdjustment oa= meshViewPage.clickOrientationAdjustment();
            Reporter.log("click Orientation Adjustment tab on left bar");
            String text="0.01";
            Reporter.log("select rotation step "+text);
            Assert.assertEquals(oa.selectMovementStep(text),text,"Cann't select "+text);

            Integer distance=1;
            String disResult=oa.clickDistancePlus();
            Reporter.log("click distance Increase(1 times), get distance is "+disResult);
            embededScreenShot(name+"_distance_"+disResult);
            Assert.assertEquals(disResult,"0.01","distance is not same as expected result");

            disResult=oa.clickDistanceMinus();
            Reporter.log("click distance Decrease(1 times), get distance is "+disResult);
            embededScreenShot(name+"_distance_"+disResult);
            Assert.assertEquals(disResult,"0","distance is not same as expected result");

            disResult=oa.clickDistanceMinus();
            Reporter.log("click distance Decrease(1 times), get distance is "+disResult);
            embededScreenShot(name+"_distance_"+disResult);
            Assert.assertEquals(disResult,"-0.01","distance is not same as expected result");

            disResult=oa.clickDistanceReset();
            Reporter.log("click distance Reset(1 times), get distance is "+disResult);
            embededScreenShot(name+"_reset_"+disResult);
            Assert.assertEquals(disResult,"0","distance is not same as expected result");
        }
    }
    //@Test(enabled = false)
    public void checkMovementZDecreaseMinimum() throws Exception {
        String name=Thread.currentThread().getStackTrace()[1].getMethodName();
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){

            uploadDCM(meshViewPage,uploadFileName);

            OrientationAdjustment oa= meshViewPage.clickOrientationAdjustment();
            Reporter.log("click Orientation Adjustment tab on left bar");
            String text="0.1";
            Reporter.log("select Movement step "+text);
            Assert.assertEquals(oa.selectMovementStep(text),text,"Cann't select "+text);


            Integer distance=51;
            String disResult=oa.clickDistanceMinus(distance);
            Reporter.log("click distance Decrease("+distance.toString()+" times), get distance is "+disResult);
            embededScreenShot(name+"_min_"+disResult);
            Assert.assertEquals(disResult,"-5","distance is not same as expected result");

            SoftAssert softAssert=new SoftAssert();

            disResult=oa.clickDistanceReset();
            Reporter.log("click distance Reset(1 times), get distance is "+disResult);
            embededScreenShot(name+"_reset_"+disResult);
            Assert.assertEquals(disResult,"0","distance is not same as expected result");
        }
    }

    @Test(enabled = false)
    public void checkResetAllAdjustment() throws Exception {
        String name=Thread.currentThread().getStackTrace()[1].getMethodName();
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){

            uploadDCM(meshViewPage,uploadFileName);

            OrientationAdjustment oa= meshViewPage.clickOrientationAdjustment();
            Reporter.log("click Orientation Adjustment tab on left bar");
            String text="0.5";
            Reporter.log("select rotation step "+text);
            Assert.assertEquals(oa.selectRotationStep(text),text,"Cann't select "+text);

            text="0.01";
            Reporter.log("select Movement step "+text);
            Assert.assertEquals(oa.selectMovementStep(text),text,"Cann't select "+text);

            String angleResult=oa.clickXAnglePlus();
            Reporter.log("click X Angle Increase(1 times), get angle value is "+angleResult);
            embededScreenShot(name+"_x_angle_"+angleResult);
            Assert.assertEquals(angleResult,"0.5","angle value is not same as expected result");

            angleResult=oa.clickYAngleMinus();
            Reporter.log("click Y Angle Decrease(1 times), get angle value is "+angleResult);
            embededScreenShot(name+"_y_angle_"+angleResult);
            Assert.assertEquals(angleResult,"-0.5","angle value is not same as expected result");

            angleResult=oa.clickZAnglePlus();
            Reporter.log("click Z Angle Increase(1 times), get angle value is "+angleResult);
            embededScreenShot(name+"_z_angle_"+angleResult);
            Assert.assertEquals(angleResult,"0.5","angle value is not same as expected result");

            String disResult=oa.clickDistanceMinus();
            Reporter.log("click distance Decrease(1 times), get distance is "+disResult);
            embededScreenShot(name+"_distance_"+disResult);
            Assert.assertEquals(disResult,"-0.01","distance is not same as expected result");

        }
    }
}
