package com.csdental.meshviewer.displaytool;

import com.csdental.meshviewer.MeshViewPage;
import com.csdental.meshviewer.OrientationAdjustment;
import com.csdental.meshviewer.TestManager;
import com.csdental.util.IProp;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

@Test(groups={"smoke","function_test"})
public class OrientationAdjustmentTest  extends TestManager {
    String uploadFileName="Common_HD3D/common.HD3D.off[1.0.3.600]/Franklin_Benjamin_[2021-03-03_09-51-18].dcm";
    public void checkRotationXIncreaseMaximum() throws Exception {
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
            String angleResult=oa.clickXAnglePlus(angle);
            Reporter.log("click X Angle Increase("+angle.toString()+" times), get angle value is "+angleResult);
            embededScreenShot(name+"_max_"+angleResult);
            Assert.assertEquals(angleResult,"90","angle value is not same as expected result");


            angleResult=oa.clickXAngleReset();
            Reporter.log("click X Angle Reset(1 times), get angle value is "+angleResult);
            embededScreenShot(name+"_reset_"+angleResult);
            Assert.assertEquals(angleResult,"0","angle value is not same as expected result");
        }
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
