package com.csdental.util;


public final class BuildStatus {
    private final static BuildStatus buildStatus=new BuildStatus();
    public static BuildStatus getInstance(){
        return buildStatus;
    }
    private static Boolean errors;
    public void recordError(){
        errors=true;
    }
    public Boolean hasErrors(){
        return errors;
    }

}
