package com.zxgzero.remotememory.Util.toolutil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.zxgzero.remotememory.R;


/**
 * Created by zxg on 2015/6/2.
 */
public class SkipActivityUtils {
	/**
	 * 用于Activity的跳转
	 * 
	 * @param mActivity
	 *            原Activity
	 * @param mClass
	 *            待跳转Activity
	 */
	public static void skipActivity(Activity mActivity, Class mClass) {
		Intent intent = new Intent(mActivity, mClass);
		mActivity.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		mActivity.startActivity(intent);
	}

	/**
	 * 用于Activity的跳转，带请求码
	 * 
	 * @param mActivity
	 *            原Activity
	 * @param mClass
	 *            待跳转Activity
	 */
	public static void skipActivity(Activity mActivity, Class mClass, int requestCode) {
		Intent intent = new Intent(mActivity, mClass);
		mActivity.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		mActivity.startActivityForResult(intent, requestCode);
	}

	/**
	 * 用于Activity的跳转
	 * 
	 * @param mActivity
	 *            原Activity
	 * @param mClass
	 *            待跳转Activity
	 */
	public static void skipActivityWithData(Activity mActivity, Class mClass, Bundle mBundle) {
		Intent intent = new Intent(mActivity, mClass);
		intent.putExtras(mBundle);
		mActivity.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		mActivity.startActivity(intent);
	}

	/**
	 * 用于Activity的跳转,带参数，带请求码
	 * 
	 * @param mActivity
	 *            原Activity
	 * @param mClass
	 *            待跳转Activity
	 */
	public static void skipActivityWithData(Activity mActivity, Class mClass, Bundle mBundle, int requestCode) {
		Intent intent = new Intent(mActivity, mClass);
		intent.putExtras(mBundle);
		mActivity.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		mActivity.startActivityForResult(intent, requestCode);
		;
	}
}
