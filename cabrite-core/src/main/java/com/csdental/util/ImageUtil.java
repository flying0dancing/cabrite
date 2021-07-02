package com.csdental.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ImageUtil {
    private final static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
    /**
     * full screen shot
     * @return BufferedImage
     */
    public static BufferedImage getFullScreenShot(){
        BufferedImage bfImage=null;
        Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
        int width=(int) d.getWidth();
        int heith=(int) d.getHeight();
        try {
            Robot robot=new Robot();
            bfImage=robot.createScreenCapture(new Rectangle(0,0,width,heith));
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return bfImage;
    }
    /***
     * read image from local to memory
     * @param path
     * @return
     */
    public static BufferedImage getBfImage(String path){
        BufferedImage bfImage=null;
        try {
            bfImage= ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bfImage;
    }

    /***
     * get RGB data from memory
     * @param bfImage
     * @return
     */
    public static int[][] getImageRGB(BufferedImage bfImage){
        int width=bfImage.getWidth();
        int height=bfImage.getHeight();
        int[][] result=new int[height][width];
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                int pixel=bfImage.getRGB(w,h);
                result[h][w]=pixel & 0xFFFFFF;//ARGB convert to RGB
            }

        }
        return result;
    }

    public static double getDifferent(String lower, String middle){
        ImageInfo lower_info=getImageInfo(lower);
        ImageInfo middle_info=getImageInfo(middle);
        long lower_diff=matchPercentOfCompareImage(lower_info, middle_info, 0,0);
        int lower_width= lower_info.getCompareWidth();
        int lower_height=lower_info.getCompareHeight();
        long lower_rect=lower_width*lower_height;
        double lower_diff_percent=new BigDecimal((float)lower_diff/lower_rect).setScale(6,BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(String.format("lower_diff_percent  %.4f", lower_diff_percent));
        return lower_diff_percent;
    }

    /**
     * Get the maximum similar as tolerance(Get minimum different as tolerance), actual similar should bigger or equal than this result.
     * details: using images lower, middle, higher, calculate different of lower vs middle, middle vs higher, get minimum different as tolerance.
     * @param lower the full path of image, like the movement distance is 4.5, user take a screenshot
     * @param middle the full path of image, like the movement distance is 4.6, user take a screenshot
     * @param higher the full path of image, like the movement distance is 4.7, user take a screenshot
     * @return
     */
    public static double getTolerance(String lower, String middle, String higher, String resultFolder){
        resultFolder=Strs.reviseFilePath(resultFolder+System.getProperty("file.separator"));
        FileUtil.createDirectory(resultFolder);
        double tolerance=0;
        ImageInfo lower_info=getImageInfo(lower);
        ImageInfo middleL_info=getImageInfo(middle);
        ImageInfo middleH_info=getImageInfo(middle);
        ImageInfo higher_info=getImageInfo(higher);
        long lower_diff=matchPercentOfCompareImage(lower_info, middleL_info, 0,0);
        int lower_width= lower_info.getCompareWidth();
        int lower_height=lower_info.getCompareHeight();
        long lower_rect=lower_width*lower_height;
        double lower_diff_percent=new BigDecimal((float)lower_diff/lower_rect).setScale(6,BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(String.format("lower_diff_percent  %.6f", lower_diff_percent));
        long higher_diff=matchPercentOfCompareImage(higher_info, middleH_info, 0,0);
        int higher_width= higher_info.getCompareWidth();
        int higher_height=higher_info.getCompareHeight();
        long higher_rect=higher_width*higher_height;
        double higher_diff_percent=new BigDecimal((float)higher_diff/higher_rect).setScale(6,BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(String.format("higher_diff_percent  %.6f", higher_diff_percent));
        String output_prefix="getTolerance_";
        String middle_output=output_prefix+Strs.getName(middle);
        if(lower_diff_percent>higher_diff_percent){
            tolerance=higher_diff_percent;
            String higher_output=output_prefix+Strs.getName(higher);
            higher_info.setCompareDiffFullName(resultFolder+higher_output);
            middleH_info.setCompareDiffFullName(resultFolder+middle_output);
        }else{
            tolerance=lower_diff_percent;
            String lower_output=output_prefix+Strs.getName(lower);
            lower_info.setCompareDiffFullName(resultFolder+lower_output);
            middleL_info.setCompareDiffFullName(resultFolder+middle_output);
        }
        System.out.println(String.format("tolerance  %.6f", tolerance));
        return 100-tolerance*100;
    }

    /**
     * Get the maximum similar as tolerance(Get minimum different as tolerance), actual similar should bigger or equal than this result.
     * details: using images lower, middle, higher, calculate different of lower vs middle, middle vs higher, get minimum different as tolerance.
     * @param actual the full path of image, user take a screenshot
     * @param expected the full path of image, user take a screenshot
     * @return
     */
    public static double compareWithExpectation(String actual, String expected, String resultFolder){
        resultFolder=Strs.reviseFilePath(resultFolder+System.getProperty("file.separator"));
        FileUtil.createDirectory(resultFolder);
        double tolerance=0;
        ImageInfo actual_info=getImageInfo(actual);
        ImageInfo expected_info=getImageInfo(expected);
        long lower_diff=matchPercentOfCompareImage(actual_info, expected_info, 0,0);
        int lower_width= actual_info.getCompareWidth();
        int lower_height=actual_info.getCompareHeight();
        long lower_rect=lower_width*lower_height;
        double lower_diff_percent=new BigDecimal((float)lower_diff/lower_rect).setScale(6,BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(String.format("actual_diff_percent  %.6f", lower_diff_percent));

        String output_prefix="compareWithExpected_";
        String middle_output=output_prefix+Strs.getName(expected);
        tolerance=lower_diff_percent;
        String lower_output=output_prefix+Strs.getName(actual);
        actual_info.setCompareDiffFullName(resultFolder+lower_output);
        expected_info.setCompareDiffFullName(resultFolder+middle_output);

        System.out.println(String.format("tolerance  %.6f", tolerance));
        return 100-tolerance*100;
    }

    public static long matchPercentOfCompareImage(ImageInfo current_info, ImageInfo contrast_info, int sTolerance, int totalTolerance){
        return current_info.compareImage(contrast_info, sTolerance, totalTolerance);
    }

    /**
     * get the similar percent of two image
     * @param current the full path of image
     * @param contrast the full path of image
     * @param resultFolder the full path of result folder
     * @return
     */
    public static double matchPercentOfCompareImage(String current, String contrast, String resultFolder){
        ImageInfo current_info=getImageInfo(current);
        ImageInfo contrast_info=getImageInfo(contrast);
        long diff= matchPercentOfCompareImage(current_info, contrast_info, 0,0);
        String output_prefix="out_compare_";
        String current_output=output_prefix+current.substring(current.lastIndexOf("\\")+1,current.lastIndexOf("."))+current.substring(current.lastIndexOf("."));
        String contrast_output=output_prefix+contrast.substring(contrast.lastIndexOf("\\")+1,contrast.lastIndexOf("."))+contrast.substring(contrast.lastIndexOf("."));
        current_info.setCompareDiffFullName(resultFolder+current_output);
        contrast_info.setCompareDiffFullName(resultFolder+contrast_output);
        int width=current_info.getCompareWidth();
        int height= current_info.getCompareHeight();
        long rect=width*height;
        System.out.println("current mesh size: ("+current_info.getWidth()+", "+current_info.getHeight()+")");
        System.out.println("contrast mesh size: ("+current_info.getWidth()+", "+current_info.getHeight()+")");
        System.out.println("compared size width:"+width+", height:"+height+", rect:"+rect+", diff:"+diff);
        double result=new BigDecimal((float)(rect-diff)/rect).setScale(6,BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(String.format("%.4f", result*100));
        return result*100;
    }


    public static long matchPercentOfMatchImage(ImageInfo current_info, ImageInfo contrast_info, int sTolerance, int totalTolerance){
        return current_info.matchImage(contrast_info, sTolerance, totalTolerance);
    }
    /**
     * get the similar percent of two image, using adjustment method
     * @param current the full path of image
     * @param contrast the full path of image
     * @param resultFolder the full path of result folder
     * @return
     */
    public static double matchPercentOfMatchImage(String current, String contrast, String resultFolder){
        ImageInfo current_info=getImageInfo(current);
        ImageInfo contrast_info=getImageInfo(contrast);
        long diff= matchPercentOfMatchImage(current_info, contrast_info,0,0);
        String output_prefix="out_match_";
        String current_output=output_prefix+current.substring(current.lastIndexOf("\\")+1,current.lastIndexOf("."))+current.substring(current.lastIndexOf("."));
        String contrast_output=output_prefix+contrast.substring(contrast.lastIndexOf("\\")+1,contrast.lastIndexOf("."))+contrast.substring(contrast.lastIndexOf("."));
        current_info.setCompareDiffFullName(resultFolder+current_output);
        contrast_info.setCompareDiffFullName(resultFolder+contrast_output);
        int width=current_info.getCompareWidth();
        int height= current_info.getCompareHeight();
        long rect=width*height;
        System.out.println("current mesh size: ("+current_info.getWidth()+", "+current_info.getHeight()+")");
        System.out.println("contrast mesh size: ("+current_info.getWidth()+", "+current_info.getHeight()+")");
        System.out.println("compared size width:"+width+", height:"+height+", rect:"+rect+", diff:"+diff);
        double result=new BigDecimal((float)(rect-diff)/rect).setScale(6,BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(String.format("%.4f", result*100));
        return result*100;
    }

    public String createImage(String path, String type, int width,int height, BufferedImage bfImage){
        bfImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        /*Graphics2D graphics= bfImage.createGraphics();
            graphics.setBackground(Color.GRAY);
            //graphics.setColor(Color.WHITE);
            graphics.dispose();*/
        File out=new File(path);
        try {
            ImageIO.write(bfImage,type,out);
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static ImageInfo getImageInfo(String path){
        return new ImageInfo(path);
    }


    public static void main(String[] args) {
        String patha1="D:\\csdworkarea\\fromApril\\checkMovementZIncreaseMaximum_distance_4.9.png";
        String patha2="D:\\csdworkarea\\fromApril\\checkMovementZIncreaseMaximum_max_5.png";
        String resultFolder="D:\\csdworkarea\\fromApril\\result\\";


        double k=ImageUtil.matchPercentOfCompareImage(patha1,patha2,resultFolder);
        //System.out.println("similar(%):"+k);
        double f=ImageUtil.matchPercentOfMatchImage(patha1,patha2,resultFolder);
        //System.out.println("similar(%):"+f);

    }
    static class ImageInfo{
        String fileFullName;
        String fileType;
        int width;
        int height;
        int[][] rgbData;
        int compareWidth;
        int compareHeight;
        BufferedImage bfImage;
        ArrayList<int[]> compareDiffData;//int[] format is {x,y,rgb} or {width,height,rgb}
        String compareDiffFullName;

        public ImageInfo(String fileFullName) {
            this.fileFullName = fileFullName;
            this.fileType=fileFullName.substring(fileFullName.lastIndexOf(".")+1);
            this.bfImage=getBfImage(fileFullName);
            if(bfImage!=null){
                setWidth(bfImage.getWidth());
                setHeight(bfImage.getHeight());
                setRgbData(getImageRGB(bfImage));
            }
        }
        public ImageInfo(BufferedImage bfImage) {
            this.fileType="png";
            this.bfImage=bfImage;
            if(bfImage!=null){
                setWidth(bfImage.getWidth());
                setHeight(bfImage.getHeight());
                setRgbData(getImageRGB(bfImage));
            }
        }

        public Dimension getIntersectionDimension(BufferedImage currentImage, BufferedImage contrastImage){
            int height_contrast=contrastImage.getHeight();
            int width_contrast=contrastImage.getWidth();
            int height_current=currentImage.getHeight();
            int width_current=currentImage.getWidth();
            int height=height_current<height_contrast?height_current:height_contrast;
            int width=width_current<width_contrast?width_current:width_contrast;
            return new Dimension(width,height);
        }


        public Dimension getIntersectionDimension(int[][] current,int[][] contrast){
            int height_contrast=contrast.length;
            int width_contrast=contrast[0].length;
            int height_current=current.length;
            int width_current=current[0].length;
            int height=height_current<height_contrast?height_current:height_contrast;
            int width=width_current<width_contrast?width_current:width_contrast;
            return new Dimension(width,height);
        }

        /**
         * if result is 0 means match all.
         * @param contrast_line
         * @param current_line
         * @return
         */
        public long isMatchLine(int[] current_line, int[] contrast_line, int minWidth, int stolerance){
            int xor=0;
            long different=0;
            for (int i = 0; i < minWidth; i++) {
                //xor=contrast_line[i]^current_line[i];
                xor=getPixelDiff(current_line[i], contrast_line[i], stolerance);
                if(xor!=0){
                    different++;
                }
            }
            return different;
        }



        public int[] calcXOffset(int[] current_line, int[] contrast_line, int minWidth, int stolerance){
            int xor1=0, xor2=0;
            int different1=0, different2=0, minDifferent=0;
            int minDiff=9999;
            int minOffset=0;
            int offset=10;
            //get min offset's position and different value
            for (int o = 0; o < offset; o++) {
                different1=0; different2=0;
                for (int i = 0; i < minWidth-offset; i++) {
                    //xor1=current_line[i]^ contrast_line[i+o];
                    //xor2=current_line[i+o]^ contrast_line[i];
                    xor1=getPixelDiff(current_line[i], contrast_line[i+o], stolerance);
                    xor2=getPixelDiff(current_line[i+o], contrast_line[i], stolerance);
                    if(xor1!=0){different1++;}
                    if(xor2!=0){different2++;}
                }
                minDifferent=different1>different2?different2:different1;
                System.out.println("min different:"+minDifferent+" offset:"+o);
                if(minDiff>minDifferent){
                    minDiff=minDifferent;
                    minOffset=o;
                }
            }
            //calc offset is set to current or contrast
            int[] resultPos=new int[2];
            resultPos[0]=0;//current Y or X
            resultPos[1]=0;//contrast Y or X
            if(minOffset>0){
                different1=0;
                for (int i = 0; i < minWidth-minOffset; i++) {
                    //xor1=current_line[i]^ contrast_line[i+minOffset];
                    xor1=getPixelDiff(current_line[i], contrast_line[i+minOffset], stolerance);
                    if(xor1!=0){different1++;}
                }
                if(minDiff==different1){
                    resultPos[1]=minOffset;//contrast Y or X
                }else{
                    resultPos[0]=minOffset;//current Y or X
                }
            }
            return resultPos;
        }

        public int matchedLine(int[] current_line,int[][] contrast, int stolerance, int tolerance){
            long different;
            long minDiff=9999;
            int minPos=0;
            int minWidth=contrast[0].length>current_line.length?current_line.length:contrast[0].length;
            int contrast_Y_mid=contrast.length/2;
            System.out.println("contrast_Y_mid:"+contrast_Y_mid);
            int distance=contrast.length/7;
            System.out.println("distance(contrast.length/7):"+distance);

            for (int h = contrast_Y_mid-distance; h < distance*2; h++) {
                different=isMatchLine(current_line,contrast[h],minWidth, stolerance);
                if(different<minDiff){
                    minDiff=different;
                    minPos=h;
                    System.out.println("matchedLine: min different:"+minDiff+" pos:"+minPos);
                }
            }
            if(minDiff>=tolerance){//tolerance
                minPos=0;
            }
            System.out.println("matchedLine: min different(final):"+minDiff+" pos:"+minPos);
            return minPos;
        }

        public int[] findFirstMatchLine(int[][] current, int[][] contrast, int stolerance, int tolerance){
            int[] resultPos=new int[2];
            resultPos[0]=0;//current Y or X
            resultPos[1]=0;//contrast Y or X
            int current_Y_mid= current.length/2;//use middle x as baseline to find

            System.out.println("current_Y_mid:"+current_Y_mid);
            resultPos[1]=matchedLine(current[current_Y_mid], contrast, stolerance, tolerance);
            if(Math.abs(resultPos[1]-current_Y_mid)<=contrast.length/40){//tolerance of distance
                resultPos[0]=current_Y_mid;
            }

            return resultPos;
        }

        /**
         * get current or contrast image's first matched x,y, and their intersection(wight, height)
         * @param current
         * @param contrast
         * @param tolerance
         * @return
         */
        public int[] getFistMatchedPositions(int[][] current, int[][] contrast, int stolerance, int tolerance){
            int[] resultY= findFirstMatchLine(current, contrast,stolerance, tolerance);
            System.out.println("resultY: currect="+resultY[0]+", contrast="+resultY[1]);
            int minWidth=contrast[0].length>current[0].length?current[0].length:contrast[0].length;
            int[] resultX= calcXOffset(current[resultY[0]], contrast[resultY[1]], minWidth, stolerance);
            System.out.println("resultX: currect="+resultX[0]+", contrast="+resultX[1]);

            int height=(contrast.length-resultY[1])>(current.length-resultY[0])?(current.length-resultY[0]):(contrast.length-resultY[1]);
            int width=(contrast[0].length-resultX[1])>(current[0].length-resultX[0])?(current[0].length-resultX[0]):(contrast[0].length-resultX[1]);
            System.out.println("intersection width:"+width+", height:"+height);
            int[] result=new int[]{resultX[0],resultY[0],resultX[1],resultY[1],width,height};
            return result;
        }



        public long matchImage(ImageInfo contrast_info, int stolerance, int tolerance){
            long begin_Comparison=System.currentTimeMillis();
            int[][] current=getRgbData();
            int[][] contrast= contrast_info.getRgbData();
            ArrayList<int[]> current_res=new ArrayList<>();
            ArrayList<int[]> contrast_res=new ArrayList<>();
            int[] matchedPositions=getFistMatchedPositions(current, contrast,stolerance, tolerance);
            int currentX=matchedPositions[0];
            int currentY=matchedPositions[1];
            int contrastX=matchedPositions[2];
            int contrastY=matchedPositions[3];
            int width=matchedPositions[4];
            int height=matchedPositions[5];

            setCompareWidth(width);
            setCompareHeight(height);
            contrast_info.setCompareWidth(width);
            contrast_info.setCompareHeight(height);

            long different=isMatchAll(currentX-contrastX, currentY-contrastY, width, height, current, contrast, current_res, contrast_res, stolerance, tolerance);

            //appendCompareDiffData(current_rest, current, getWidth(), getHeight(), currentX+width, currentY+height);
            //appendCompareDiffData(contrast_res, contrast, contrast_info.getWidth(), contrast_info.getHeight(), contrastX+width, contrastY+height);
            setCompareDiffData(current_res);
            contrast_info.setCompareDiffData(contrast_res);
            long end=System.currentTimeMillis();
            System.out.println("comparison used time[seconds]:"+(end-begin_Comparison)/1000.00F);
            return different;
        }

        /**
         *
         * @param offsetX the difference x =current - contrast
         * @param offsetY the difference y =current - contrast
         * @param minWight width of compare area
         * @param minHeight height of compare area
         * @param current RGB data
         * @param contrast RGB data
         * @param current_rest
         * @param contrast_res
         * @param stolerance
         * @param tolerance
         * @return
         */
        public long isMatchAll(int offsetX, int offsetY, int minWight, int minHeight, int[][] current, int[][] contrast, ArrayList<int[]> current_rest, ArrayList<int[]> contrast_res, int stolerance, int tolerance){
            int contrastX=0, contrastY=0, curX=0, curY=0;
            int currentX=0, currentY=0, conX=0, conY=0;

            int xor=0;
            int tmpDiff=0;
            long minDiff=99999;
            long different=0;
            if(offsetX<0){
                conX=-offsetX;
            }else{
                curX=offsetX;
            }
            if(offsetY<0){
                conY=-offsetY;
            }else{
                curY=offsetY;
            }

            for (int h = 0; h <minHeight ; h++) {
                currentY=curY+h;
                contrastY=conY+h;
                tmpDiff=0;
                for (int w = 0; w < minWight; w++) {
                    currentX=curX+w;
                    contrastX=conX+w;
                    //xor=current[currentY][currentX]^contrast[contrastY][contrastX];
                    xor=getPixelDiff(current[currentY][currentX], contrast[contrastY][contrastX], stolerance);
                    if(xor!=0){
                        tmpDiff++;
                        different++;
                        //System.out.println("xor"+xor);
                    }
                }
                if(tmpDiff>tolerance){
                    for (int i = 0; i < minWight; i++) {
                        contrastX=conX+i;
                        currentX=curX+i;
                        xor=getPixelDiff(current[currentY][currentX], contrast[contrastY][contrastX], stolerance);
                        if(xor!=0){
                            current_rest.add(new int[]{currentX,currentY,current[currentY][currentX]});
                            contrast_res.add(new int[]{contrastX,contrastY,contrast[contrastY][contrastX]});
                        }
                    }
                }
                //System.out.println("match Y currentY="+currentY+" contrastY="+contrastY+" different:"+tmpDiff);
            }
            /*if(minHeight+curY<current.length){
                for (int h = minHeight; h < current.length; h++) {
                    current_rest.add(new int[]{currentX,currentY,current[currentY][currentX]});
                }
            }else{
                for (int h = minHeight; h < contrast.length; h++) {
                    contrast_res.add(new int[]{contrastX,contrastY,contrast[contrastY][contrastX]});
                }
            }*/

            return different;
        }


        public long compareImage(ImageInfo contrast_info, int sTolerance, int totalTolerance){
            long begin_Comparison=System.currentTimeMillis();
            long k=0;
            long q=0;
            int[][] current=getRgbData();
            int[][] contrast= contrast_info.getRgbData();
            Dimension d=getIntersectionDimension(current,contrast);
            int width=(int) d.getWidth();
            int height= (int) d.getHeight();
            setCompareWidth(width);
            setCompareHeight(height);
            contrast_info.setCompareWidth(width);
            contrast_info.setCompareHeight(height);
            ArrayList<int[]> current_res=new ArrayList<>();//int[] format is x, y, RGB, for save different RGB data
            ArrayList<int[]> contrast_res=new ArrayList<>();//int[] format is x, y, RGB, for save different RGB data

            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {

                    int Q=getPixelDiff(current[h][w], contrast[h][w], sTolerance);
                    if(Q>totalTolerance){
                        q++;
                        current_res.add(new int[]{w,h,current[h][w]});
                        contrast_res.add(new int[]{w,h,contrast[h][w]});
                    }

                }
            }
            //appendCompareDiffData(current_res, current, getWidth(), getHeight(), width, height);
            //appendCompareDiffData(contrast_res, contrast, contrast_info.getWidth(), contrast_info.getHeight(), width, height);
            setCompareDiffData(current_res);
            contrast_info.setCompareDiffData(contrast_res);
            long end=System.currentTimeMillis();
            System.out.println("comparison used time[seconds]:"+(end-begin_Comparison)/1000.00F);
            return q;
        }

        /**
         * get the totoal different R+G+B between current and contrast prixel
         * @param current
         * @param contrast
         * @param sTolerance single tolerance for R/G/B
         * @return
         */
        private int getPixelDiff(int current, int contrast, int sTolerance){
            int[] rgb_current=new int[3];
            int[] rgb_contrast=new int[3];
            rgb_current[0]=(current & 0xFF0000)>>16;
            rgb_current[1]=(current & 0xFF00)>>8;
            rgb_current[2]=current & 0xFF;
            rgb_contrast[0]=(contrast & 0xFF0000)>>16;
            rgb_contrast[1]=(contrast & 0xFF00)>>8;
            rgb_contrast[2]=contrast & 0xFF;

            int diff_R=Math.abs(rgb_contrast[0]-rgb_current[0]);
            int diff_G=Math.abs(rgb_contrast[1]-rgb_current[1]);
            int diff_B=Math.abs(rgb_contrast[2]-rgb_current[2]);
            int Q=0;
            if((diff_R>sTolerance && diff_G>sTolerance) || (diff_G>sTolerance && diff_B>sTolerance || (diff_B>sTolerance && diff_R>sTolerance)) ){
                Q=diff_R+diff_G+diff_B;
            }
            return Q;
        }

        private void appendCompareDiffData(ArrayList<int[]> res, int[][] rgbData, int width, int height, int startX, int startY){
            //TODO
        }
        public String getFileFullName() {
            return fileFullName;
        }

        public void setFileFullName(String fileFullName) {
            this.fileFullName = fileFullName;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }


        /*public BufferedImage getBfImage() {
            return bfImage;
        }*/

        public void setBfImage(BufferedImage bfImage) {
            this.bfImage = bfImage;
        }

        public int[][] getRgbData() {
            return rgbData;
        }

        public void setRgbData(int[][] rgbData) {
            this.rgbData = rgbData;
        }

        public int getCompareWidth() {
            return compareWidth;
        }

        public void setCompareWidth(int compareWidth) {
            this.compareWidth = compareWidth;
        }

        public int getCompareHeight() {
            return compareHeight;
        }

        public void setCompareHeight(int compareHeight) {
            this.compareHeight = compareHeight;
        }

        public ArrayList<int[]> getCompareDiffData() {
            return compareDiffData;
        }

        public void setCompareDiffData(ArrayList<int[]> compareDiffData) {
            this.compareDiffData = compareDiffData;
        }

        public String getCompareDiffFullName() {
            return compareDiffFullName;
        }

        public void setCompareDiffFullName(String compareDiffFullName) {
            this.compareDiffFullName = createImage(getCompareDiffData(), compareDiffFullName, width,height);
        }
        /**
         * using data for create a result Image
         * @param data an array list, one group data is int[](x, y, RGB data)
         * @param path the full name of image
         * @param width the width of image
         * @param height the height of image

         * @return
         */
        private String createImage(ArrayList<int[]> data, String path, int width, int height){
            String fileFormat=path.substring(path.lastIndexOf(".")+1);
            BufferedImage bfImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < data.size(); i++) {
                int[] tmp=data.get(i);
                bfImage.setRGB(tmp[0],tmp[1],tmp[2]);
            }

            File out=new File(path);
            try {
                ImageIO.write(bfImage,fileFormat,out);
                return path;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}