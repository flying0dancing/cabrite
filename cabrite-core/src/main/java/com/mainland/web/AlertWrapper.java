package com.mainland.web;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlertWrapper implements IAlertWrapper{
    private final Logger logger = LoggerFactory.getLogger(AlertWrapper.class);
    WebDriverWrapper webDriverWrapper;
    WebDriver driver;
    Wait<WebDriver> wait;
    Alert alert;
    public AlertWrapper(WebDriverWrapper webDriverWrapper){
        this.webDriverWrapper=webDriverWrapper;
        this.driver=this.webDriverWrapper.getDriver();
        this.wait=this.webDriverWrapper.getWait();
    }

    public void setAlert(){
        if(!isPresent()){
            alert=wait.until(driver->{
                try{
                    return driver.switchTo().alert();
                }catch (WebDriverException e){
                    return null;
                }
            });
        }
    }

    public void dismiss(){
        setAlert();
        if(alert!=null){
            logger.info("dismiss alert");
            alert.dismiss();
        }
    }
    public void acceptAlert(){
        setAlert();
        if(alert!=null){
            logger.info("accept alert");
            alert.accept();
        }
    }

    public String getTextAlert(){
        setAlert();
        return alert==null?null:alert.getText();
    }

    public Boolean isPresent(){
        try {
            this.alert=webDriverWrapper.getDriver().switchTo().alert();
            if(alert!=null){
                logger.info("Alert is present.");
                return true;
            }
        } catch (WebDriverException e) {
        }
        return false;
    }

}
