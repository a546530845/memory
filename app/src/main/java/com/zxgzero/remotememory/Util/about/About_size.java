package com.zxgzero.remotememory.Util.about;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;

public class About_size {
	/**
	* dp转px
	*/
	public static int dp2px(Context context, float dpValue) {
	    final float scale = context.getResources().getDisplayMetrics().density;
	    return (int) (dpValue * scale + 0.5f);
	}

	/**
	* px转dp
	*/
	public static int px2dp(Context context, float pxValue) {
	    final float scale = context.getResources().getDisplayMetrics().density;
	    return (int) (pxValue / scale + 0.5f);
	}
	/**
	* sp转px
	*/
	public static int sp2px(Context context, float spValue) {
	    final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
	    return (int) (spValue * fontScale + 0.5f);
	}

	/**
	* px转sp
	*/
	public static int px2sp(Context context, float pxValue) {
	    final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
	    return (int) (pxValue / fontScale + 0.5f);
	}
	// 该方法存在于TypedValue
	/**
	* 各种单位转换
	*/
	public static float applyDimension(int unit, float value, DisplayMetrics metrics) {
	    switch (unit) {
	        case TypedValue.COMPLEX_UNIT_PX:
	            return value;
	        case TypedValue.COMPLEX_UNIT_DIP:
	            return value * metrics.density;
	        case TypedValue.COMPLEX_UNIT_SP:
	            return value * metrics.scaledDensity;
	        case TypedValue.COMPLEX_UNIT_PT:
	            return value * metrics.xdpi * (1.0f / 72);
	        case TypedValue.COMPLEX_UNIT_IN:
	            return value * metrics.xdpi;
	        case TypedValue.COMPLEX_UNIT_MM:
	            return value * metrics.xdpi * (1.0f / 25.4f);
	    }
	    return 0;
	}
	/**
	* 在onCreate()即可获取View的宽高
	*/
	public static int[] getViewMeasure(View view) {
	    int widthMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
	    int heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
	    view.measure(widthMeasureSpec, heightMeasureSpec);
	    return new int[]{view.getMeasuredWidth(), view.getMeasuredHeight()};
	}
	// 通知父布局，占用的宽，高；
	/**
	* ListView中提前测量View尺寸，如headerView
	*/
	private void measureView(View view) {
	    ViewGroup.LayoutParams p = view.getLayoutParams();
	    if (p == null) {
	        p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
	                ViewGroup.LayoutParams.WRAP_CONTENT);
	    }
	    int width = ViewGroup.getChildMeasureSpec(0, 0, p.width);
	    int height;
	    int tempHeight = p.height;
	    if (tempHeight > 0) {
	        height = MeasureSpec.makeMeasureSpec(tempHeight,
	                MeasureSpec.EXACTLY);
	    } else {
	        height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
	    }
	    view.measure(width, height);
	}
}
