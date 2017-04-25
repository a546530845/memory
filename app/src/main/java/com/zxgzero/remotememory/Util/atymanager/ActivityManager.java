package com.zxgzero.remotememory.Util.atymanager;

import android.app.Activity;

import java.util.Stack;

public class ActivityManager {
    // activity创建的记录，用于异常时的关闭处理
    private Stack<Activity> mActivities = new Stack<Activity>();
    private static ActivityManager mActivityManager;

    public static ActivityManager getInstance() {
        if (null == mActivityManager) {
            mActivityManager = new ActivityManager();
        }
        return mActivityManager;
    }

    /**
     * @param activity
     */
    public void pushActivity(Activity activity) {
        mActivities.push(activity);
    }

    /**
     * 当一个activity销毁时，从activity记录栈移除相应的activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        int index = mActivities.indexOf(activity);
        if (index != -1) {
            mActivities.remove(index);
        }
    }

    ;

    // 关闭所有activity，如果它们存在
    private void finishAllActivityIfExist() {
        if (mActivities != null) {
            while (!mActivities.isEmpty()) {
                mActivities.remove(mActivities.size() - 1).finish();
            }
        }
    }

    /**
     * 退出SDK所有页面
     */
    public void exitApplication() {
        finishAllActivityIfExist();
        System.exit(0);
    }

//	/**
//	 * 关闭除了MainActivity的所有活动
//	 */
//	public void exitAllPageButMainActivity(){
//		for(int i=mActivities.size()-1;i>=0;i--){
//			if(mActivities.get(i) instanceof MainActivity){
//				LogUtil.e("Activity管理=","检测到MainActivity");
//			}else {
//				mActivities.remove(mActivities.size() - 1).finish();
//			}
//
//		}
//	}
//	/**
//	 * 关闭除了LoginActivity的所有活动
//	 */
//	public void exitAllPageButLoginActivity(){
//		for(int i=mActivities.size()-1;i>=0;i--){
//			if(mActivities.get(i) instanceof LoginActivity){
//			}else {
//				mActivities.remove(mActivities.size() - 1).finish();
//			}
//
//		}
//	}
//	/**
//	 * 关闭LoginActivity
//	 */
//	public void exitLoginActivity(){
//		for(int i=mActivities.size()-1;i>=0;i--){
//			if(mActivities.get(i) instanceof LoginActivity){
//				mActivities.remove(i).finish();
//			}
//		}
//	}
}
