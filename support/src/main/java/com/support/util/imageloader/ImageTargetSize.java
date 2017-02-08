package com.support.util.imageloader;

public class ImageTargetSize {
    
    public final static int CENTER_CROP = 1;
    public final static int CENTER_INSIDE = 2;
    
    private final int targetWidth;
    private final int targeHeight;
    private int scaleType = 0;    //resize图片源方式，1：等比例缩放图片的size居中显示，使得图片的长宽的等于或大于resize设置的长宽，去除超出部分得到图片
                                  //2:图片的内容完整居中显示，通过按比例缩放图片的size使得图片长宽等于或小于resize设置的长宽，得到图片

    public ImageTargetSize(int targetWidth, int targeHeight) {
        this.targetWidth = targetWidth;
        this.targeHeight = targeHeight;
    }
    
    public void centerCrop(){
        this.scaleType = CENTER_CROP;
    }
    
    public void centerInside(){
        this.scaleType = CENTER_INSIDE;
    }
    
    public int getTargetWidth() {
        return this.targetWidth;
    }

    public int getTargetHeight() {
        return this.targeHeight;
    }
    
    public int getResizeScaleType(){
        return scaleType;
    }
}
