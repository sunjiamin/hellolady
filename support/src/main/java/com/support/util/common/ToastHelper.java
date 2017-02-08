package com.support.util.common;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;
public class ToastHelper {
	
	public static Toast mToast = null;
	private static Toast mImgToast = null;
	
	public static void showToast(Context context, String text, int duration) {  
        if(context!=null){
        	if (mToast == null) {  
        		mToast = Toast.makeText(context, text, duration);  
        	} else {  
        		mToast.setText(text);  
        		mToast.setDuration(duration);  
        	}  
        	mToast.show();
        }
    }  
	
	public static void showToast(Context context, String text, int duration,int gravity) {  
		if (mToast == null) {   
			mToast = Toast.makeText(context, text, duration);  
		} else {  
			mToast.setText(text);  
			mToast.setDuration(duration);  
		}  
		mToast.setGravity(gravity, 0, 0);
		mToast.show();  
	}  

	public static void cancelToast(){
		if(mToast !=null){
			mToast.cancel();
		}
	}
	
	public static void showToast(Context ctx,int resId,int gravity){
		if (mImgToast == null) {   
			mImgToast = new Toast(ctx);
			ImageView iv = new ImageView(ctx);
			mImgToast.setView(iv);
		}
		((ImageView)mImgToast.getView()).setImageResource(resId);
		mImgToast.setGravity(gravity, 0, 0);
		mImgToast.show();  
	}
}
