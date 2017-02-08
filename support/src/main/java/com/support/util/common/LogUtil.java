package com.support.util.common;

import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {
	
	private static final String DEFAULT_TAG = "qichat";
	
	public static boolean DEBUG = true;

	private static boolean fileDebug = false;
    private static File logDir;

    public static void enableDebug() {
    	DEBUG = true;
    }
    
    public static void enableFileDebug(File logDir) {
    	if (logDir != null) {
    		LogUtil.logDir = logDir;
        	fileDebug = true;
		}
    }

    public static void setLogDir(File dir) {
        logDir = dir;
    }

	public static void v(String msg) {
		v(DEFAULT_TAG, msg);
	}

	public static void e(String msg) {
		e(DEFAULT_TAG, msg);
	}

	public static void i(String msg) {
		i(DEFAULT_TAG, msg);
	}

	public static void d(String msg) {
		d(DEFAULT_TAG, msg);
	}

	public static void w(String msg) {
		w(DEFAULT_TAG, msg);
	}
	
	public static void v(String tag, String msg) {
		if (DEBUG) {
            Log.v(tag, msg + "");
		}
	}

	public static void e(String tag, String msg) {
		if (DEBUG) {
            Log.e(tag, msg + "");
		}
	}

	public static void i(String tag, String msg) {
		if (DEBUG) {
            Log.i(tag, msg + "");
		}
	}

	public static void d(String tag, String msg) {
		if (DEBUG) {
            Log.d(tag, msg + "");
		}
	}

	public static void w(String tag, String msg) {
		if (DEBUG) {
            Log.w(tag, msg + "");
		}
	}

    public static String getExceptionString(Throwable exception, Date time) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return timeFormat.format(time) + "\r\n" + Log.getStackTraceString(exception) + "\r\n\r\n";
    }

    
    public static void writeLog(String msg){
        if(fileDebug){
            try{
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date time = new Date();
                File logFile = new File(logDir, dateFormat.format(time) + ".log");
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter(logFile, true);
                    fileWriter.write(timeFormat.format(time) + " " + msg + "\r\n");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(fileWriter != null) {
                        try {
                            fileWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
