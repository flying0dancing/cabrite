package com.csdental.test;

public class Reporter extends org.testng.Reporter{
    public static void log(String s) {
        org.testng.Reporter.log("<li>"+s+"</li>");
    }
    public static void testStart(){
        org.testng.Reporter.log("<ul>");
    }
    public static void testEnd(){
        org.testng.Reporter.log("</ul>");
    }
}
