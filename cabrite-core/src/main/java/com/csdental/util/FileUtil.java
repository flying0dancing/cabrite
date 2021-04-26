package com.csdental.util;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.copyFile;
import static org.apache.commons.io.FileUtils.copyToDirectory;

public class FileUtil {
    private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    public static void createDirectory(String folderPath)
    {
        if(folderPath!=null)
        {
            File folder = new File(folderPath);
            if(!folder.isDirectory())
            {
                folder.mkdirs();
            }
        }
    }
    public static void createDirectory(String outputDir, String subDir) throws Exception
    {
        File file = new File(outputDir);
        if (!(subDir == null || subDir.trim().equals("")))
        {
            file = new File(outputDir + File.separator + subDir);
        }
        if (!file.exists())
        {
            file.mkdirs();
        }
    }
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

    public static void copyFileToDirectory(String sourceFolder,String fileType, String destFolder) throws Exception
    {
        try
        {
            if(sourceFolder!=null && fileType!=null && destFolder!=null)
            {
                if(fileType.lastIndexOf(";")==fileType.length()-1){fileType=fileType.substring(0, fileType.length()-1);}
                String[] fileTypes=fileType.split(";");

                for(int i=0; i<fileTypes.length;i++)
                {
                    final String fileTypeStr=fileTypes[i];
                    File sourceFolderHandle=new File(sourceFolder);
                    File[] files=sourceFolderHandle.listFiles(new FilenameFilter(){
                        public boolean accept(File f , String name){
                            return name.endsWith(fileTypeStr);}
                    });
                    File destFolderHandle=new File(destFolder);
                    for(File file:files)
                    {
                        copyToDirectory(file, destFolderHandle);
                    }
                }


            }

        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
        }

    }

    public static void copyDirectory(String sourcePath,String destPath) {
        try
        {
            if(sourcePath!=null && destPath!=null)
            {
                File sourceFile=new File(sourcePath);
                File destFile=new File(destPath);
                if(!destFile.exists()){createDirectory(destPath);}
                copyToDirectory(sourceFile,destFile);
            }

        }catch(Exception e)
        {logger.error(e.getMessage());}
    }
    /**
     * copy files and folders newer than startTime
     * @author kun shen
     * @param sourcePath
     * @param destPath
     * @param startTime
     */
    public static void copyDirectory(String sourcePath, String destPath,long startTime, long endTime)
    {
        try
        {
            if(sourcePath!=null && destPath!=null)
            {
                File sourceFile=new File(sourcePath);
                File destFile=new File(destPath);
                if(!destFile.exists()){createDirectory(destPath);}
                if(sourceFile.isDirectory())
                {
                    File[] subfiles = sourceFile.listFiles();
                    if(subfiles.length>0)
                    {
                        for(File subfile:subfiles)
                        {
                            if(subfile.isFile() && subfile.lastModified()>=startTime && subfile.lastModified()<=endTime)
                            {
                                copyFile(subfile,new File(destPath+subfile.getAbsolutePath().replace(sourcePath, "")));
                            }
                            if(subfile.isDirectory())
                            {
                                String destSubDirectory=destPath+subfile.getAbsolutePath().replace(sourcePath, "");
                                copyDirectory(subfile.getAbsolutePath(),destSubDirectory,startTime, endTime);
                            }
                        }
                    }

                }
                if(sourceFile.isFile() && sourceFile.lastModified()>=startTime  && sourceFile.lastModified()<=endTime)
                {
                    copyFile(sourceFile,destFile);
                }
            }

        }catch(Exception e)
        {logger.error(e.getMessage());}

    }



}
