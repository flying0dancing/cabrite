package com.csdental.test;

import com.csdental.util.IProp;
import com.csdental.util.Strs;


public class Reporter extends org.testng.Reporter{

    public static void log(String s) {
        org.testng.Reporter.log("<li>"+s+"</li>");
        if(s.toLowerCase().contains(IProp.SCREENSHOT_TYPE.toLowerCase())){
            String suffix=s.substring(s.lastIndexOf("."));
            if(suffix.equalsIgnoreCase(IProp.SCREENSHOT_TYPE)){
                Integer getScreenShotsIndex=s.lastIndexOf(System.getProperty("file.separator")+IComFolder.SCREENSHOT_FOLDERNAME+System.getProperty("file.separator"));
                String path="."+System.getProperty("file.separator")+s.substring(getScreenShotsIndex);
                //org.testng.Reporter.log("<img src = '"+path+"' width = '100px' />",true);
                org.testng.Reporter.log("<img src = '"+path+"' onclick=\"window.open('"+Strs.convertFilePath(path)+"','newwindow','height=600,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')\" width = '100px' />");
            }
        }
    }
    public static void testStart(){
        org.testng.Reporter.log("<ul>");
    }
    public static void testEnd(){
        org.testng.Reporter.log("</ul>");
    }

}
