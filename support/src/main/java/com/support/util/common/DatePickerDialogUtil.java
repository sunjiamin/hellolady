package com.support.util.common;

import android.app.DatePickerDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
/**
 * DatePickerDialog�����ࣨ��������һ����� 
 * 						���ķ�����hideItem()
 * @author lx
 *
 */
public class DatePickerDialogUtil {
	
	
	/**
	 * ����DatePickerDialogһ������꣬�£��գ�
	 * @param dialog  
	 * @param itemIndex  0����  ��1���� �� 2����
	 */
	public static void  hideItem(DatePickerDialog dialog,int  itemIndex){
				//���� ����
        DatePicker dp = findDatePicker((ViewGroup) dialog.getWindow().getDecorView());
				if (dp != null) {  
				    ((ViewGroup)((ViewGroup) dp.getChildAt(0)).getChildAt(0)).getChildAt(itemIndex).setVisibility(View.GONE);  
				}   
	}
	
	
	/**
	 * ͨ��������������DatePicker����ӿؼ����ꡢ�¡���
	 * @param group
	 * @return
	 */
	private static DatePicker findDatePicker(ViewGroup group) {
        if (group != null) {
            for (int i = 0, j = group.getChildCount(); i < j; i++) {
                View child = group.getChildAt(i);
                if (child instanceof DatePicker) {
                    return (DatePicker) child;
                } else if (child instanceof ViewGroup) {
                    DatePicker result = findDatePicker((ViewGroup) child);
                    if (result != null)
                        return result;
                }
            }
        }
        return null;
    } 

}
