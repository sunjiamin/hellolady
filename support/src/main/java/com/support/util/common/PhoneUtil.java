package com.support.util.common;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目名称：hellolady
 * 类描述：
 * 创建人：Jiamin.Sun
 * 创建时间：5/5/2016 10:47 AM
 * 修改人：Jiamin.Sun
 * 修改时间：5/5/2016 10:47 AM
 * 修改备注：
 */
public class PhoneUtil {

    /**
     * 获取IME标识 手机唯一的标识
     * @return
     */
    public static String getPhoneIME(Context context){
        TelephonyManager TelephonyMgr = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
        return TelephonyMgr.getDeviceId();
    }

    /**
     * 判断手机号码是否合法
     * @param phoneNumber	需要判断的手机号码
     * @return	如果传入的手机号码是合法的，则返回 true, 否则返回 false
     */
    public static boolean isPhoneNumberValid(String phoneNumber)
    {
        // 如果传入的手机号码为空，则返回 false
        if( phoneNumber == null )
            return false;
        // 如果手机号码长度为 0，则返回 false
        phoneNumber = phoneNumber.trim();
        if( phoneNumber.length() == 0 )
            return false;

        boolean isValid = false;

        String expression = "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";
        CharSequence inputStr = phoneNumber;

        Pattern pattern = Pattern.compile(expression);

        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches() ) {
            isValid = true;
        }

        return isValid;
    }
}
