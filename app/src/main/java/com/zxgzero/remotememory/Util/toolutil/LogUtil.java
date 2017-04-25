package com.zxgzero.remotememory.Util.toolutil;

import android.util.Log;

public class LogUtil {
	private static boolean isDebug = true;
	/*
	 * log.e的输出
	 * 设置为false时，阻断所有输出（error）
	 */
	public static void e(String tag,String msg){
		if(isDebug){
			Log.e(tag, msg);
		}
	}
	/*
	 * log.i的输出
	 * * 设置为false时，阻断所有输出（）
	 */
	public static void i(String tag,String msg){
		if(isDebug){
			Log.i(tag, msg);
		}
	}
	/*
	 * log.d的输出
	 * * 设置为false时，阻断所有输出（）
	 */
	public static void d(String tag,String msg){
		if(isDebug){
			Log.d(tag, msg);
		}
	}
}
