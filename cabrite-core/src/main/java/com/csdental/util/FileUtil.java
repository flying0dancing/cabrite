package com.csdental.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static void createParentFolder(File file){
        File parent=file.getParentFile();
        parent.mkdirs();
    }
    public static File createNewFile(String filefullname){
        File file=null;
        if(StringUtils.isNotBlank(filefullname)){
            try {
                file=new File(filefullname);
                if(file.exists())
                {
                    file=createNewFileWithSuffix(file,null,null);
                }else{
                    System.out.println(file.getPath());
                    createParentFolder(file);
                    file.createNewFile();
                }
            } catch (IOException e) {
                BuildStatus.getInstance().recordError();
                logger.error("error: failed to create new file{}.");
                logger.error(e.getMessage(),e);
            }
        }
        return file;
    }
    /***
     * support folder and file, generate a new file name with path.
     * @param filefullname
     * @param suffix can be null
     * @param newFilePath can be null, get filefullname's parent path
     * @return new file full path with name
     */
    public static String createNewFileWithSuffix(String filefullname,String suffix,String newFilePath)
    {
        String newFileFullName=null;
        File file=new File(filefullname);
        if(file.exists())
        {
            file=createNewFileWithSuffix( file, suffix, newFilePath);
            newFileFullName=file.getPath();
        }else
        {
            BuildStatus.getInstance().recordError();
            logger.error("argument:filefullname[{}] doesn't exist.",filefullname);
        }

        return newFileFullName;
    }

    public static File createNewFileWithSuffix(File file,String suffix,String newFilePath)
    {
        String newFileFullName=null;
        String fileName=file.getName();
        int count=1;
        String namePrefix=fileName, nameSuffix="";
        if(file.isFile() && fileName.contains(".")){
            namePrefix=fileName.substring(0, fileName.lastIndexOf("."));
            nameSuffix=fileName.replace(namePrefix, "");
        }
        //target newFilePath is null
        if(StringUtils.isBlank(newFilePath))
        {newFilePath=file.getPath().replace(namePrefix+nameSuffix, "");}
        if(StringUtils.isBlank(suffix)){
            suffix="";
        }
        newFileFullName=namePrefix+suffix+nameSuffix;
        File newFile=new File(newFilePath+newFileFullName);
        while(newFile.exists())
        {
            newFileFullName=namePrefix+suffix+"("+String.valueOf(count)+")"+nameSuffix;
            count++;
            newFile=new File(newFilePath+newFileFullName);
        }

        newFileFullName=newFilePath+newFileFullName;
        logger.debug("renew file name is:{}",newFileFullName);
        return newFile;
    }

    public static boolean exists(final String fileFullName) {
        if (StringUtils.isNotBlank(fileFullName)) {
            File file = new File(fileFullName);
            return file.exists();
        }
        return false;
    }

    public static String validateFileFullName(final String... fileFullNames) {
        for(String fileFullName:fileFullNames){
            if(exists(fileFullName)){
                return fileFullName;
            }
        }
        return "";
    }

}
