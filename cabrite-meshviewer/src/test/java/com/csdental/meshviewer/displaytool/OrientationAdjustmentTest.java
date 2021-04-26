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

@Test(groups={"smoke","function_test"})
public class OrientationAdjustmentTest  extends TestManager {
    String uploadFileName="Common_HD3D/common.HD3D.off[1.0.3.600]/Franklin_Benjamin_[2021-03-03_09-51-18].dcm";
    public void checkMovementZIncreaseMaximum(ITestContext context) {
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
            Integer distance=98;

            String disResult=oa.clickDistancePlus(distance);
            Reporter.log("click distance Increase("+distance.toString()+" times), get distance is "+disResult);
            String lower=embededScreenShot(name+"_distance_"+disResult);
            Assert.assertEquals(disResult,"4.9","distance is not same as expected result");

            disResult=oa.clickDistancePlus();
            Reporter.log("click distance Increase("+distance.toString()+" times), get distance is "+disResult);
            String middle=embededScreenShot(name+"_distance_"+disResult);
            Assert.assertEquals(disResult,"4.95","distance is not same as expected result");

            text="0.1";
            Reporter.log("select Movement step "+text);
            Assert.assertEquals(oa.selectMovementStep(text),text,"Cann't select "+text);

            distance=1;
            disResult=oa.clickDistancePlus(distance);
            Reporter.log("click distance Increase("+distance.toString()+" times), get distance is "+disResult);
            String higher=embededScreenShot(name+"_max_"+disResult);

            double tolerance=ImageUtil.getTolerance(lower,middle,higher, IComFolder.RESULT_EXPECTATION_FOLDER+context.getName());
            System.out.println(String.format("in test tolerance  %.6f", tolerance));

            Assert.assertEquals(disResult,"5","distance is not same as expected result");


            disResult=oa.clickDistanceReset();
            Reporter.log("click distance Reset(1 times), get distance is "+disResult);
            embededScreenShot(name+"_reset_"+disResult);
            Assert.assertEquals(disResult,"0","distance is not same as expected result");
        }
        Reporter.testEnd();
    }

    public void checkRotationXReset() throws Exception {
        String name=Thread.currentThread().getStackTrace()[1].getMethodName();
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){

            uploadDCM(meshViewPage,uploadFileName);

            OrientationAdjustment oa= meshViewPage.clickOrientationAdjustment();
            Reporter.log("click Orientation Adjustment tab on left bar");
            String text="0.5";
            Reporter.log("select rotation step "+text);
            Assert.assertEquals(oa.selectRotationStep(text),text,"Cann't select "+text);


            Integer angle=1;
            String angleResult=oa.clickXAnglePlus(angle);
            Reporter.log("click X Angle Increase("+angle.toString()+" times), get angle value is "+angleResult);
            embededScreenShot(name+"_angle_"+angleResult);
            Assert.assertEquals(angleResult,text,"angle value is not same as expected result");

            angleResult=oa.clickXAngleMinus(angle);
            Reporter.log("click X Angle Decrease("+angle.toString()+" times), get angle value is "+angleResult);
            embededScreenShot(name+"_angle_"+angleResult);
            Assert.assertEquals(angleResult,"0","angle value is not same as expected result");

            angleResult=oa.clickXAngleMinus(angle);
            Reporter.log("click X Angle Decrease("+angle.toString()+" times), get angle value is "+angleResult);
            embededScreenShot(name+"_angle_"+angleResult);
            Assert.assertEquals(angleResult,"-"+text,"angle value is not same as expected result");

            angleResult=oa.clickXAngleReset();
            Reporter.log("click X Angle Reset(1 times), get angle value is "+angleResult);
            embededScreenShot(name+"_reset_"+angleResult);
            Assert.assertEquals(angleResult,"0","angle value is not same as expected result");
        }
    }

    public void checkRotationXDecreaseMinimum() throws Exception {
        String name=Thread.currentThread().getStackTrace()[1].getMethodName();
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            uploadDCM(meshViewPage,uploadFileName);
            OrientationAdjustment oa= meshViewPage.clickOrientationAdjustment();
            Reporter.log("click Orientation Adjustment tab on left bar");
            String text="0.5";
            Reporter.log("select rotation step "+text);
            Assert.assertEquals(oa.selectRotationStep(text),text,"Cann't select "+text);

            Integer angle=181;
            String angleResult=oa.clickXAngleMinus(angle);
            Reporter.log("click X Angle Decrease("+angle.toString()+" times), get angle value is "+angleResult);
            embededScreenShot(name+"_min_"+angleResult);
            Assert.assertEquals(angleResult,"-90","angle value is not same as expected result");

            angleResult=oa.clickXAngleReset();
            Reporter.log("click X Angle Reset(1 times), get angle value is "+angleResult);
            embededScreenShot(name+"_reset_"+angleResult);
            Assert.assertEquals(angleResult,"0","angle value is not same as expected result");
        }
    }

    public void checkRotationYIncreaseMaximum() throws Exception {
        String name=Thread.currentThread().getStackTrace()[1].getMethodName();
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){

            uploadDCM(meshViewPage,uploadFileName);

            OrientationAdjustment oa= meshViewPage.clickOrientationAdjustment();
            Reporter.log("click Orientation Adjustment tab on left bar");
            String text="1.0";
            Reporter.log("select rotation step "+text);
            Assert.assertEquals(oa.selectRotationStep(text),text,"Cann't select "+text);


            Integer angle=91;
            String angleResult=oa.clickYAnglePlus(angle);
            Reporter.log("click Y Angle Increase("+angle.toString()+" times), get angle value is "+angleResult);
            embededScreenShot(name+"_max_"+angleResult);
            Assert.assertEquals(angleResult,"90","angle value is not same as expected result");


            angleResult=oa.clickYAngleReset();
            Reporter.log("click Y Angle Reset(1 times), get angle value is "+angleResult);
            embededScreenShot(name+"_reset_"+angleResult);
            Assert.assertEquals(angleResult,"0","angle value is not same as expected result");
        }
    }

    public void checkRotationYReset() throws Exception {
        String name=Thread.currentThread().getStackTrace()[1].getMethodName();
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){

            uploadDCM(meshViewPage,uploadFileName);

            OrientationAdjustment oa= meshViewPage.clickOrientationAdjustment();
            Reporter.log("click Orientation Adjustment tab on left bar");
            String text="1.0";
            Reporter.log("select rotation step "+text);
            Assert.assertEquals(oa.selectRotationStep(text),text,"Cann't select "+text);


            Integer angle=1;
            String angleResult=oa.clickYAnglePlus(angle);
            Reporter.log("click Y Angle Increase("+angle.toString()+" times), get angle value is "+angleResult);
            embededScreenShot(name+"_angle_"+angleResult);
            Assert.assertEquals(angleResult,"1","angle value is not same as expected result");

            angleResult=oa.clickYAngleMinus(angle);
            Reporter.log("click Y Angle Decrease("+angle.toString()+" times), get angle value is "+angleResult);
            embededScreenShot(name+"_angle_"+angleResult);
            Assert.assertEquals(angleResult,"0","angle value is not same as expected result");

            angleResult=oa.clickYAngleMinus(angle);
            Reporter.log("click Y Angle Decrease("+angle.toString()+" times), get angle value is "+angleResult);
            embededScreenShot(name+"_angle_"+angleResult);
            Assert.assertEquals(angleResult,"-1","angle value is not same as expected result");

            angleResult=oa.clickYAngleReset();
            Reporter.log("click Y Angle Reset(1 times), get angle value is "+angleResult);
            embededScreenShot(name+"_reset_"+angleResult);
            Assert.assertEquals(angleResult,"0","angle value is not same as expected result");
        }
    }

    public void checkRotationYDecreaseMinimum() throws Exception {
        String name=Thread.currentThread().getStackTrace()[1].getMethodName();
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            uploadDCM(meshViewPage,uploadFileName);
            OrientationAdjustment oa= meshViewPage.clickOrientationAdjustment();
            Reporter.log("click Orientation Adjustment tab on left bar");
            String text="1.0";
            Reporter.log("select rotation step "+text);
            Assert.assertEquals(oa.selectRotationStep(text),text,"Cann't select "+text);


            Integer angle=91;
            String angleResult=oa.clickYAngleMinus(angle);
            Reporter.log("click Y Angle Decrease("+angle.toString()+" times), get angle value is "+angleResult);
            embededScreenShot(name+"_min_"+angleResult);
            Assert.assertEquals(angleResult,"-90","angle value is not same as expected result");

            angleResult=oa.clickYAngleReset();
            Reporter.log("click Y Angle Reset(1 times), get angle value is "+angleResult);
            embededScreenShot(name+"_reset_"+angleResult);
            Assert.assertEquals(angleResult,"0","angle value is not same as expected result");
        }
    }

    public void checkRotationZIncreaseMaximum() throws Exception {
        String name=Thread.currentThread().getStackTrace()[1].getMethodName();
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){

            uploadDCM(meshViewPage,uploadFileName);

            OrientationAdjustment oa= meshViewPage.clickOrientationAdjustment();
            Reporter.log("click Orientation Adjustment tab on left bar");
            String text="5.0";
            Reporter.log("select rotation step "+text);
            Assert.assertEquals(oa.selectRotationStep(text),text,"Cann't select "+text);


            Integer angle=19;
            String angleResult=oa.clickZAnglePlus(angle);
            Reporter.log("click Z Angle Increase("+angle.toString()+" times), get angle value is "+angleResult);
            embededScreenShot(name+"_max_"+angleResult);
            Assert.assertEquals(angleResult,"90","angle value is not same as expected result");


            angleResult=oa.clickZAngleReset();
            Reporter.log("click Z Angle Reset(1 times), get angle value is "+angleResult);
            embededScreenShot(name+"_reset_"+angleResult);
            Assert.assertEquals(angleResult,"0","angle value is not same as expected result");
        }
    }

    public void checkRotationZReset() throws Exception {
        String name=Thread.currentThread().getStackTrace()[1].getMethodName();
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){

            uploadDCM(meshViewPage,uploadFileName);

            OrientationAdjustment oa= meshViewPage.clickOrientationAdjustment();
            Reporter.log("click Orientation Adjustment tab on left bar");
            String text="5.0";
            Reporter.log("select rotation step "+text);
            Assert.assertEquals(oa.selectRotationStep(text),text,"Cann't select "+text);


            Integer angle=1;
            String angleResult=oa.clickZAnglePlus(angle);
            Reporter.log("click Z Angle Increase("+angle.toString()+" times), get angle value is "+angleResult);
            embededScreenShot(name+"_angle_"+angleResult);
            Assert.assertEquals(angleResult,"5","angle value is not same as expected result");

            angleResult=oa.clickZAngleMinus(angle);
            Reporter.log("click Z Angle Decrease("+angle.toString()+" times), get angle value is "+angleResult);
            embededScreenShot(name+"_angle_"+angleResult);
            Assert.assertEquals(angleResult,"0","angle value is not same as expected result");

            angleResult=oa.clickZAngleMinus(angle);
            Reporter.log("click Z Angle Decrease("+angle.toString()+" times), get angle value is "+angleResult);
            embededScreenShot(name+"_angle_"+angleResult);
            Assert.assertEquals(angleResult,"-5","angle value is not same as expected result");

            angleResult=oa.clickZAngleReset();
            Reporter.log("click Z Angle Reset(1 times), get angle value is "+angleResult);
            embededScreenShot(name+"_reset_"+angleResult);
            Assert.assertEquals(angleResult,"0","angle value is not same as expected result");
        }
    }

    public void checkRotationZDecreaseMinimum() throws Exception {
        String name=Thread.currentThread().getStackTrace()[1].getMethodName();
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            uploadDCM(meshViewPage,uploadFileName);
            OrientationAdjustment oa= meshViewPage.clickOrientationAdjustment();
            Reporter.log("click Orientation Adjustment tab on left bar");
            String text="5.0";
            Reporter.log("select rotation step "+text);
            Assert.assertEquals(oa.selectRotationStep(text),text,"Cann't select "+text);


            Integer angle=19;
            String angleResult=oa.clickZAngleMinus(angle);
            Reporter.log("click Z Angle Decrease("+angle.toString()+" times), get angle value is "+angleResult);
            embededScreenShot(name+"_min_"+angleResult);
            Assert.assertEquals(angleResult,"-90","angle value is not same as expected result");

            angleResult=oa.clickZAngleReset();
            Reporter.log("click Z Angle Reset(1 times), get angle value is "+angleResult);
            embededScreenShot(name+"_reset_"+angleResult);
            Assert.assertEquals(angleResult,"0","angle value is not same as expected result");
        }
    }
}
