package com.support.util.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build;
import android.util.TypedValue;

/**
 *   bitmap 优化策略
 *  </br>
 *   1、BitmapConfig的配置</br>
 *   2、使用decodeFile、decodeResource、decodeStream进行解析Bitmap时，
 *   配置inDensity和inTargetDensity，两者应该相等,值可以等于屏幕像素密度*0.75f</br>
 *   3、使用inJustDecodeBounds预判断Bitmap的大小及使用inSampleSize进行压缩</br>
 *   4、对Density>240的设备进行Bitmap的适配（缩放Density）</br>
 *   5、2.3版本inNativeAlloc的使用</br>
 *   6、4.4以下版本inPurgeable、inInputShareable的使用</br>
 *   7、Bitmap的回收</br>
 * @author Jiamin.Sun
 *
 */
public class BitmapDecodeUtil {
    private static final int DEFAULT_DENSITY = 240;
	private static final float SCALE_FACTOR = 0.75f;
	/**
	 * 　   　    A　　R　　G　　B</br>
      *   透明度　红色　绿色　蓝色</br>
      *    Bitmap.Config ARGB_4444 16 每个像素 占四位   </br>
      *    Bitmap.Config ARGB_8888 32 每个像素 占八位  </br>
      *    Bitmap.Config RGB_565 16 R占5位 G占6位 B占5位 没有透明度（A）</br>
      *   一般情况下我们都是用argb888 但是无可厚非 它也相对的很占内存</br>
      *   因为一个像素32位 8位一个字节 如果是800*480的图片的话自己算 估计有1M多了</br>
	 */
    private static final Bitmap.Config DEFAULT_BITMAP_CONFIG = Bitmap.Config.RGB_565;
	
    /**
     * 对应优化策略2 </br>
     * 2、使用decodeFile、decodeResource、decodeStream进行解析Bitmap时，
     *    配置inDensity和inTargetDensity，两者应该相等,值可以等于屏幕像素密度*0.75f
     * @param context
     * @return
     */
    private static BitmapFactory.Options getBitmapOptions(Context context) {
    	BitmapFactory.Options option = new BitmapFactory.Options();
    	option.inScaled=true;//是否支持缩放，默认为true，当设置了这个，Bitmap将会以inTargetDensity的值进行缩放
    	option.inPreferredConfig=DEFAULT_BITMAP_CONFIG;
    	option.inPurgeable=true;//当存储Pixel的内存空间在系统内存不足时是否可以被回收
    	option.inInputShareable=true;//inPurgeable为true情况下才生效，是否可以共享一个InputStream
    	option.inJustDecodeBounds=false;//为true仅返回Bitmap的宽高等属性bitmap为null的
    	if(Build.VERSION.SDK_INT<=Build.VERSION_CODES.GINGERBREAD_MR1){
    		//当前手机系统版本小于等于10  Android 2.3.3.
    		 Field field = null;
    		 try {
    			 // 5、2.3版本inNativeAlloc的使用</br>
				field = BitmapFactory.Options.class.getDeclaredField("inNativeAlloc");
				field.setAccessible(true);
				field.setBoolean(option, true);
			} catch (NoSuchFieldException e) {
				 
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				 
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				 
				e.printStackTrace();
			}
    		 
    	}
    	int displayDensityDpi = context.getResources().getDisplayMetrics().densityDpi;//屏幕密度
    	float  displayDensity  = context.getResources().getDisplayMetrics().density ;
    	if(displayDensityDpi>DEFAULT_DENSITY && displayDensity > 1.5f){
    		//4、对Density>240的设备进行Bitmap的适配（缩放Density）
    		int density = (int) (displayDensityDpi * SCALE_FACTOR);
    		 option.inDensity = density;
             option.inTargetDensity = density;
    	}
		return option;
    }

    /**
     * decodeResource
     * @param c
     * @param resId
     * @return
     */
    public static Bitmap decodeBitmap(Context c,int resId){
    	checkParam(c);
    	return BitmapFactory.decodeResource(c.getResources(), resId,getBitmapOptions(c));
    }
    /**
     * decodeFile
     * @param c
     * @param resId
     * @return
     */
    public static Bitmap decodeBitmap(Context c,String fileName){
    	checkParam(c);
    	return BitmapFactory.decodeFile(fileName,getBitmapOptions(c));
    }
    /**
     * decodeStream
     * @param c
     * @param resId
     * @return
     */
    public static Bitmap decodeBitmap(Context c,InputStream is){
    	checkParam(c);
    	return BitmapFactory.decodeStream(is,null,getBitmapOptions(c));
    }
    
    /**
     * 通过资源ID
     * @param context
     * @param resId
     * @param maxWidth
     * @param maxHeight
     * @return
     */
    public static Bitmap compressBitmap(Context context,int resId,int maxWidth,int maxHeight){
    	checkParam(context);
    	InputStream is = null;
    	final TypedValue value = new TypedValue();
    	try {
			is = context.getResources().openRawResource(resId, value);
			return  compressBitmap(context, is, maxWidth, maxHeight);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    	 return null;
    }
    
    /**
     * 通过文件路径pathName 
     * @param context
     * @param pathName
     * @param maxWidth
     * @param maxHeight
     * @return
     */
    public static Bitmap compressBitmap(Context context, String pathName, int maxWidth, int maxHeight) {
        checkParam(context);
        InputStream is = null;
        try {
            is = new FileInputStream(pathName);
            return compressBitmap(context, is, maxWidth, maxHeight);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    
    
    
    /**
     * 压缩图片
     * 3、使用inJustDecodeBounds预判断Bitmap的大小及使用inSampleSize进行压缩
     * @param context
     * @param is
     * @param maxWidth
     * @param maxHeight
     * @return
     */
    private static Bitmap compressBitmap(Context context, InputStream is,
			int maxWidth, int maxHeight) {
		checkParam(is);
		checkParam(context);
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds=true;
		BitmapFactory.decodeStream(is, null, options);
		int outHeight = options.outHeight;
		int outWidth= options.outWidth;
		int inSimpleSize = computeSimpleSize(outWidth,outHeight,maxWidth,maxHeight);
		options.inJustDecodeBounds=false;
		BitmapFactory.Options opt =getBitmapOptions(context);
		opt.inSampleSize=inSimpleSize;				
		return BitmapFactory.decodeStream(is, null, opt);
	}

    /**
     * 计算inSampleSize
     * @param outWidth
     * @param outHeight
     * @param maxWidth
     * @param maxHeight
     * @return
     */
	private static int computeSimpleSize(int outWidth, int outHeight,
			int maxWidth, int maxHeight) {
		int inSampleSize =1;
		
		if(outWidth>maxWidth||outHeight>maxHeight){
			   final int heightRate = Math.round((float) outHeight / (float) maxHeight);
	            final int widthRate = Math.round((float) outWidth / (float) maxWidth);
	            inSampleSize = heightRate < widthRate ? heightRate : widthRate;
		}
		if (inSampleSize % 2 != 0) {
            inSampleSize -= 1;
        }
        return inSampleSize <= 1 ? 1 : inSampleSize;
	 
	}

	private static <T> void checkParam(T param){
    	if(param==null)
    	{
    		throw new NullPointerException();
    	}
    }
}