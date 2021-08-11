package com.mainland.util;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Strs {
    public static String empty(String str){
        if(isEmpty(str)){
            return "";
        }
        return str;
    }
    public static Boolean isEmpty(String str){
        if(str==null || str.equals("") || str.trim().equals("")){
            return true;
        }
        return false;
    }

    public static Boolean isEmptyStrings(String... strings){

        if(strings==null){
            return true;
        }else {
            int len=strings.length;
            if(len==0){
                return true;
            }
        }
        return false;
    }
    /**
     * revise/uniform file path separator, let file's separator followed OS's.
     * @param path
     * @return
     */
    public static String reviseFilePath(String path)
    {
        if(StringUtils.isNotBlank(path)){
            path=path.replace("\"", "");

            if(System.getProperty("file.separator").equals("/")){
                path=path.replace("\\\\", "/");
                path=path.replaceAll("/+", "/");
            }else
            {
                path=path.replace("/", "\\");
                path=path.replaceAll("\\\\+", "\\\\");
            }
            if(path.contains(" "))
            {
                //path="\""+path+"\"";
            }
        }
        return path;
    }

    /**
     * revise/uniform file path separator, let file's separator is linux's.
     * @param path
     * @return
     */
    public static String convertFilePath(String path)
    {
        if(StringUtils.isNotBlank(path)){
            path=reviseFilePath(path);
            path=path.replace("\\", "/");
        }
        return path;
    }

    /***
     * remove last separator of path if it has.
     * @param path
     * @return
     */
    public static String removeLastSlash(String path) {
        if (StringUtils.isNotBlank(path)) {
            path = path.replace("\"", "");
            if (path.endsWith("/") || path.endsWith("\\")) {
                path = path.substring(0, path.length() - 1);
            }
        }
        return path;
    }

    public static String getNameWithoutSuffix(String path){
        String nameWithoutSuffix=null;
        if(StringUtils.isNotBlank(path)){
            path=Strs.removeLastSlash(Strs.reviseFilePath(path));
            if(path.contains("\\")){
                nameWithoutSuffix=path.substring(path.lastIndexOf("\\")+1,path.lastIndexOf("."));
            }
            if(path.contains("/")){
                nameWithoutSuffix=path.substring(path.lastIndexOf("/")+1,path.lastIndexOf("."));
            }
        }
        return nameWithoutSuffix;
    }

    public static String getName(String path){
        String nameWithoutSuffix=null;
        if(StringUtils.isNotBlank(path)){
            path=Strs.removeLastSlash(Strs.reviseFilePath(path));
            if(path.contains("\\")){
                nameWithoutSuffix=path.substring(path.lastIndexOf("\\")+1,path.lastIndexOf("."))+path.substring(path.lastIndexOf("."));
            }
            if(path.contains("/")){
                nameWithoutSuffix=path.substring(path.lastIndexOf("/")+1,path.lastIndexOf("."))+path.substring(path.lastIndexOf("."));
            }
        }
        return nameWithoutSuffix;
    }

    /***
     * get parent path, if it is the top folder, return itself
     * @param path
     * @return
     */
    public static String getParentPath(String path) {
        if (StringUtils.isNotBlank(path)) {
            path = removeLastSlash(path);
            int lastSlash = path.lastIndexOf("\\") == -1 ? path.lastIndexOf("/") : path.lastIndexOf("\\");//get parent path
            if (lastSlash > 0) {
                path = path.substring(0, lastSlash) + System.getProperty("file.separator");
            } else {
                path = path + System.getProperty("file.separator");
            }
        }
        return path;
    }

    public static String projectParent(){
        return getParentPath(System.getProperty("user.dir"));
    }

    public static String projectSuperParent(){
        return getParentPath(getParentPath(System.getProperty("user.dir")));
    }

    public static String projectPath(){
        return System.getProperty("user.dir")+System.getProperty("file.separator");
    }

    public static String validate(String path){
        return FileUtil.validateFileFullName(projectPath()+path,projectSuperParent()+path);
    }
    public static String getFormatDate(){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date=LocalDate.now();
        String str=date.format(formatter);
        return str;
    }
    public static String getFormatTime(){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime date=LocalTime.now();
        String str=date.format(formatter);
        return str.replaceAll(":","-");
    }

    public static String resultToday(String path){
        //String today="result["+ getFormatDate()+"T"+getFormatTime()+"]";
        String today="result["+ getFormatDate()+"]";
        String pathToday=path.replace("/result/","/"+today+"/").replace("\\result\\","\\"+today+"\\");
        return projectPath()+pathToday;
    }


    public static String briefPath(String path, String startFolderName){
        Integer startIndex=path.lastIndexOf(System.getProperty("file.separator")+startFolderName+System.getProperty("file.separator"));
        if(startIndex>0){
            String briefPath=".."+System.getProperty("file.separator")+".."+path.substring(startIndex);
            return briefPath;
        }
        return path;
    }
}
