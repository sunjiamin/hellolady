package com.support.util.common;

import android.content.Context;
import android.os.Environment;
import android.os.IBinder;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Util {
	private static Util util;
	public static int flag = 0;
	private double r = 6371.004;

	private Util() {

	}

	public static Util getInstance() {
		if (util == null) {
			util = new Util();
		}
		return util;
	}

	/**
	 * �ж��Ƿ���sdcard
	 * 
	 * @return
	 */
	public boolean hasSDCard() {
		boolean b = false;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			b = true;
		}
		return b;
	}

	/**
	 * �õ�sdcard·��
	 * 
	 * @return
	 */
	public String getExtPath() {
		String path = "";
		if (hasSDCard()) {
			path = Environment.getExternalStorageDirectory().getPath();
		}
		return path;
	}

	/**
	 * �õ�/data/data/com.d3studio.togetherĿ¼
	 * 
	 * @param mActivity
	 * @return
	 */
	public String getPackagePath(Context mActivity) {
		return mActivity.getFilesDir().toString();
	}

	/**
	 * ���url�õ�ͼƬ
	 * 
	 * @param url
	 * @return
	 */
	public String getImageName(String url) {
		String imageName = "";
		if (url != null) {
			imageName = url.substring(url.lastIndexOf("/") + 1);
		}
		return imageName;
	}

	/**
	 * ��ݾ�γ�Ȼ�ȡ����
	 * 
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @return
	 */
	public String getDistance(double lat1, double lon1, double lat2, double lon2) {
		String string;

		//�ϴ�λ��ʧ��ʱ===  0.0û�취���ٶȵ�ͼ�ĵ����趨��Ψ�ж�ʧ�����û���
		if (lat2 == 0 && lon2 == 0) {
			string = "�������";
		} else {
			double distance = 0;
			distance = 2
					* r
					* Math.asin(Math.sqrt(Math.pow(Math.sin((lat1 - lat2) / 2),
							2)
							+ Math.cos(lat1)
							* Math.cos(lat2)
							* Math.pow(Math.sin((lon1 - lon2) / 2), 2)));
			// ������λС��
			DecimalFormat df = new DecimalFormat("##.##");
			string = df.format(distance)+"km";
		}
		return string;
	}

	/**
	 * �ж��ֻ�������ȷ��
	 * 
	 * @param mobiles
	 * @return
	 */
	public boolean isMobileNumber(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
	/**
	 * �ж��������ȷ��
	 * 
	 * @param mobiles
	 * @return
	 */
	public boolean isEmail(String email) {
		Pattern p = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * ����ȫ����ͬ�����ֻ�����ĸ
	 */
	public boolean equalStr(String numOrStr) {
		boolean flag = true;
		char str = numOrStr.charAt(0);
		for (int i = 0; i < numOrStr.length(); i++) {
			if (str != numOrStr.charAt(i)) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	// ���������������--����
	public boolean isOrderNumeric(String numOrStr) {
		boolean flag = true;
		boolean isNumeric = true;
		for (int i = 0; i < numOrStr.length(); i++) {
			if (!Character.isDigit(numOrStr.charAt(i))) {
				isNumeric = false;
				break;
			}
		}
		if (isNumeric) {
			for (int i = 0; i < numOrStr.length(); i++) {
				if (i > 0) {
					int num = Integer.parseInt(numOrStr.charAt(i) + "");
					int num_ = Integer.parseInt(numOrStr.charAt(i - 1) + "") + 1;
					if (num != num_) {
						flag = false;
						break;
					}
				}
			}
		} else {
			flag = false;
		}
		return flag;
	}

	// ���������������--�ݼ�
	public boolean isOrderNumeric_(String numOrStr) {
		boolean flag = true;
		boolean isNumeric = true;
		for (int i = 0; i < numOrStr.length(); i++) {
			if (!Character.isDigit(numOrStr.charAt(i))) {
				isNumeric = false;
				break;
			}
		}
		if (isNumeric) {
			for (int i = 0; i < numOrStr.length(); i++) {
				if (i > 0) {
					int num = Integer.parseInt(numOrStr.charAt(i) + "");
					int num_ = Integer.parseInt(numOrStr.charAt(i - 1) + "") - 1;
					if (num != num_) {
						flag = false;
						break;
					}
				}
			}
		} else {
			flag = false;
		}
		return flag;

	}
	
	
	// ����֣������������ӿؼ� -- ����ֹtextview
	public static void disableSubControls(ViewGroup viewGroup) {
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			View v = viewGroup.getChildAt(i);
			if (v instanceof ViewGroup) {
				if (v instanceof Spinner) {
					Spinner spinner = (Spinner) v;
					spinner.setClickable(false);
					spinner.setEnabled(false);

				} else if (v instanceof ListView) {
					v.setClickable(false);
					v.setEnabled(false);

				} else {
					disableSubControls((ViewGroup) v);
				}
			} else if (v instanceof EditText) {
				v.setEnabled(false);
				v.setClickable(false);

			} else if (v instanceof Button) {
				v.setEnabled(false);
			}
		}
	}

	// ����֣������������ӿؼ�  -- ����ֹtextview
	public static void ableSubControls(ViewGroup viewGroup) {
		for (int i = 0; i < viewGroup.getChildCount(); i++) {
			View v = viewGroup.getChildAt(i);
			if (v instanceof ViewGroup) {
				if (v instanceof Spinner) {
					Spinner spinner = (Spinner) v;
					spinner.setClickable(true);
					spinner.setEnabled(true);

				} else if (v instanceof ListView) {
					v.setClickable(true);
					v.setEnabled(true);

				} else {
					ableSubControls((ViewGroup) v);
				}
			} else if (v instanceof EditText) {
				v.setEnabled(true);
				v.setClickable(true);

			} else if (v instanceof Button) {
				v.setEnabled(true);
			}
		}
	}
	

	 // �����ֻ����  
	public static void hideIM(Context context,View edt) {  
     try {  
         InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);  
         IBinder windowToken = edt.getWindowToken();  
         if (windowToken != null) {  
             im.hideSoftInputFromWindow(windowToken, 0); 
         }  
     } catch (Exception e) {  
     }  
 }

	/**
	 * 获取当前进程名
	 * @return
	 */
	public static String getProcessName() {
		try {
			File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
			BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
			String processName = mBufferedReader.readLine().trim();
			mBufferedReader.close();
			return processName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
