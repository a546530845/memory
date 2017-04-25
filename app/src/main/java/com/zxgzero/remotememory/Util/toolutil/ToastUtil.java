package com.zxgzero.remotememory.Util.toolutil;

import android.content.Context;
import android.widget.Toast;

/**
 * 显示toast信息
 * 
 * @author Luke
 *
 */
public final class ToastUtil {

	private static Toast toast = null;

	private ToastUtil() {
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * 短时间显示Toast
	 * 
	 * @param context
	 * @param message
	 */
	public final static void showShort(Context context, CharSequence message) {
		if (toast == null) {
			toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		} else {
			toast.setText(message);
		}
		toast.show();
	}

	/**
	 * 短时间显示Toast
	 * 
	 * @param context
	 * @param message
	 */
	public final static void showShort(Context context, int message) {

		if (toast == null) {
			toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		} else {
			toast.setText(context.getResources().getText(message));
		}
		toast.show();
	}

	/**
	 * 长时间显示Toast
	 * 
	 * @param context
	 * @param message
	 */
	public final static void showLong(Context context, CharSequence message) {
		if (toast == null) {
			toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
		} else {
			toast.setText(message);
		}
		toast.show();
	}

	/**
	 * 长时间显示Toast
	 * 
	 * @param context
	 * @param message
	 */
	public final static void showLong(Context context, int message) {
		if (toast == null) {
			toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
		} else {
			toast.setText(context.getResources().getText(message));
		}
		toast.show();

	}

	/**
	 * 自定义显示Toast时间
	 * 
	 * @param context
	 * @param message
	 * @param duration
	 */
	public final static void show(Context context, CharSequence message, int duration) {
		if (toast == null) {
			toast = Toast.makeText(context, message, duration);
		} else {
			toast.setText(message);
		}
		toast.show();
	}

	/**
	 * 自定义显示Toast时间
	 * 
	 * @param context
	 * @param message
	 * @param duration
	 */
	public final static void show(Context context, int message, int duration) {
		if (toast == null) {
			toast = Toast.makeText(context, message, duration);
		} else {
			toast.setText(context.getResources().getText(message));
		}
		toast.show();
	}
}