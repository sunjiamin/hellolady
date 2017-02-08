//package com.support.util.common;
//
//
//
//import android.app.Dialog;
//import android.content.Context;
//import android.graphics.drawable.BitmapDrawable;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup.LayoutParams;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.ImageView;
//import android.widget.PopupWindow;
//
//import com.sun.support.R;
//
///**
// *
// * @author Test
// *
// */
//public class ShowLoadWindowUtil {
//
//	public static PopupWindow mPopupWindow;
//
//	public static Dialog dialog;
//
//
//	public static  PopupWindow  show(Context context,View parentView,boolean OutsideTouchable){
//		if(mPopupWindow !=null){
//			mPopupWindow.dismiss();
//		}
//		LayoutInflater inflater = LayoutInflater.from(context);
//		View layout = inflater.inflate(R.layout.mydialog, null);
//
//
//		ImageView iv = (ImageView) layout.findViewById(R.id.iv_loading_anim);
//		Animation anim = AnimationUtils.loadAnimation(context, R.anim.loading_rotate);
//		iv.setAnimation(anim);
//		anim.start();
//
//		mPopupWindow = new PopupWindow(layout, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//		mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
//		mPopupWindow.setOutsideTouchable(OutsideTouchable);
//
//		mPopupWindow.showAtLocation(parentView, Gravity.CENTER, 0,0);
//
//
//		return  mPopupWindow;
//	}
//
//
//
//
//	public  static void showLoadDialog(float amount,Context context,boolean cancelble){
//		dialog = new Dialog(context, R.style.CustomProgressDialog);
//        dialog.getWindow().setDimAmount(amount);
//		LayoutInflater inflater = LayoutInflater.from(context);
//		View layout = inflater.inflate(R.layout.dialog_loading_first_coming, null);
//        dialog.setContentView(layout);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setCancelable(cancelble);
//		Animation anim = AnimationUtils.loadAnimation(context, R.anim.loading_rotate);
//		ImageView iv = (ImageView) layout.findViewById(R.id.iv_loading_first);
//		iv.setAnimation(anim);
//		anim.start();
//        dialog.show();
//	}
//	public static void  showLoadDialog(float amount,Context context){
//		showLoadDialog(amount, context, true);
//	}
//
//	public static void dismiss(){
//		if(mPopupWindow !=null&&mPopupWindow.isShowing()){
//			mPopupWindow.dismiss();
//		}
//		if(dialog !=null&&dialog.isShowing()){
//            dialog.dismiss();
//		}
//	}
//
//}
