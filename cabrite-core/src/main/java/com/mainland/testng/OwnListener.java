package com.mainland.testng;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class OwnListener extends TestListenerAdapter {
    private int m_fail=0;
    private int m_pass=0;
    private int m_skip=0;
    private int m_total=0;
    @Override
    public void onTestStart(ITestResult result) {
        m_total++;
        super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        super.onTestSuccess(result);
        m_pass++;
        log(".");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        m_fail++;
        log("F");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        super.onTestSkipped(result);
        m_skip++;
        log("S");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onStart(ITestContext context) {
        super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        super.onFinish(context);
    }

    private void log(String str){
        System.out.println(str);
        if(++m_pass%40==0){
            System.out.println("");
        }
    }


}
