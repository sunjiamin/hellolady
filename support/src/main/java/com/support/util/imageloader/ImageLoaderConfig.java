package com.support.util.imageloader;

import android.graphics.Bitmap;

public class ImageLoaderConfig {
    private LoadPriority priority;              //下载任务优先级
    private Bitmap.Config decodingOptions;      //bitmap解码类型
    private ImageTargetSize imageSize;          //指定图片大小，超过这个大小会进行剪裁缩放
    private Object tag;                         //给下载图片任务设置标识，可以取消未下载的任务
    private int defResId;                       //下载图片时设置的默认图
    private int errorResId;                     //下载失败,显示的图片
    private boolean noFade;                     //true：无淡出动画效果， false：默认有淡出动画效果
    private String stableKey;					//图片key
    private int rotateDegree;					//旋转角度

    private ImageLoaderConfig(Builder builder) {
        this.priority = builder.priority;
        this.decodingOptions = builder.decodingOptions;
        this.imageSize = builder.imageSize;
        this.tag = builder.tag;
        this.defResId = builder.defResId;
        this.errorResId = builder.errorResId;
        this.noFade = builder.noFade;
        this.stableKey = builder.stableKey;
        this.rotateDegree = builder.rotateDegree;
    }

    public LoadPriority getPriority() {
        return priority;
    }


    public Bitmap.Config getDecodingOptions() {
        return decodingOptions;
    }

    public ImageTargetSize getImageSize() {
        return imageSize;
    }

    public Object getTag() {
        return tag;
    }

    public int getDefResId() {
        return defResId;
    }

    public int getErrorResId() {
        return errorResId;
    }

    public boolean isNoFade() {
        return noFade;
    }
    
    public String getStableKey() {
    	return stableKey;
    }
    
    public int getRotateDegree() {
    	return rotateDegree;
    }

    public static class Builder {
        private LoadPriority priority;
        private Bitmap.Config decodingOptions;
        private ImageTargetSize imageSize;
        private Object tag;
        private int defResId;
        private int errorResId;
        private boolean noFade;
        private String stableKey;
        private int rotateDegree;

        public Builder() {
            this.decodingOptions = Bitmap.Config.RGB_565;
            this.noFade = false;
        }

        public Builder setTag(Object tag){
            if(tag == null) {
                throw new IllegalArgumentException("tag can't be null");
            }
            this.tag = tag;
            return this;
        }

        //设置无淡出动画效果
        public Builder setNoFade(boolean noFade){
            this.noFade = noFade;
            return this;
        }

        public Builder setImageOnLoading(int defResId){
            this.defResId = defResId;
            return this;
        }

        public Builder setImageOnFail(int errorResId){
            this.errorResId = errorResId;
            return this;
        }

       // 优先级  ImageLoaderConfig.Priority.HIGH 、ImageLoaderConfig.Priority.NORMAL、ImageLoaderConfig.Priority.LOW
        public Builder setLoadPriority(LoadPriority loadPriority) {
            this.priority = loadPriority;
            return this;
        }

        public Builder setBitmapConfig(Bitmap.Config bitmapConfig){
            if(bitmapConfig == null) {
                throw new IllegalArgumentException("bitmapConfig can't be null");
            }
            this.decodingOptions = bitmapConfig;
            return this;
        }

        public Builder setImageSize(ImageTargetSize imageSize){
            this.imageSize = imageSize;
            return this;
        }
        public Builder setStableKey(String stableKey) {
        	this.stableKey = stableKey;
        	return this;
        }
        
        public Builder setRotateDegree(int degree) {
        	this.rotateDegree = degree;
        	return this;
        }
        
        public ImageLoaderConfig build() {
            return new ImageLoaderConfig(this);
        }
        
    }


    public static enum LoadPriority {
        LOW,
        NORMAL,
        HIGH;
    }

}
