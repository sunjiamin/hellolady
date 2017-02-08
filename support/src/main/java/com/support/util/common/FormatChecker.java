package com.support.util.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatChecker {
	/**
	 * 验证身份证号
	 *
	 * @param idCard
	 * @return
	 */
	public static boolean checkIDCard(String idCard) {
		boolean flag = false;
		try {
			Pattern regex = Pattern.compile("^(\\d{15}|\\d{17}[\\dx])$");
			Matcher matcher = regex.matcher(idCard);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;

	}

	/**
	 * 验证手机号码
	 *
	 * @param mobileNumber
	 * @return
	 */
	public static boolean checkMobileNumber(String mobileNumber) {
		boolean flag = false;
		try {
			Pattern regex = Pattern
					.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9])|(17[0,7]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
			Matcher matcher = regex.matcher(mobileNumber);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证车牌号
	 *
	 * @param carNO
	 * @return
	 */
	public static boolean checkCarNO(String carNO) {
		boolean flag = false;
		try {
			Pattern regex = Pattern
					.compile("^[\\u4e00-\\u9fa5]{1}[A-Z0-9]{6}$");
			Matcher matcher = regex.matcher(carNO);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	//
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			Pattern regex = Pattern
					.compile("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$");
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
}
