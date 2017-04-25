package com.zxgzero.remotememory.Util.about;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AboutApp {
	/**
	 * 安装指定路径下的Apk
	 */
	public static void installApk(String filePath, Context mActivity) {
//		Intent intent = new Intent();
//		intent.setAction("android.intent.action.VIEW");
//		intent.addCategory("android.intent.category.DEFAULT");
//		intent.setDataAndType(Uri.fromFile(new File(filePath)),
//				"application/vnd.android.package-archive");
//		mActivity.startActivity(intent);

		Intent installIntent = new Intent();
		installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		installIntent.setAction(Intent.ACTION_VIEW);
		installIntent.setDataAndType(Uri.fromFile(new File(filePath)), "application/vnd.android.package-archive");
		mActivity.startActivity(installIntent);
	}

	/**
	 * 卸载指定包名的App
	 */
	public void uninstallApp(String packageName, Activity mActivity) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.DELETE");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.setData(Uri.parse("package:" + packageName));
		mActivity.startActivityForResult(intent, 0);
	}

	/**
	 * 获取App名称
	 */
	public static String getAppName(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			int labelRes = packageInfo.applicationInfo.labelRes;
			return context.getResources().getString(labelRes);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取当前App版本名
	 */
	public static String getVersonName(Context context) {
		String versionName = null;
		PackageManager pm = context.getPackageManager();
		PackageInfo info = null;
		try {
			info = pm.getPackageInfo(context.getApplicationContext()
					.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		if (info != null) {
			versionName = info.versionName;
		}
		return versionName;
	}

	/**
	 * 获取当前App版本Code
	 */
	public static int getVersionCode(Context context) {
		int versionCode = 0;
		PackageManager pm = context.getPackageManager();
		PackageInfo info = null;
		try {
			info = pm.getPackageInfo(context.getApplicationContext()
					.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		if (info != null) {
			versionCode = info.versionCode;
		}
		return versionCode;
	}
	/**
	 * 获取兴业版本号
	 * 也可以获取任何一个知道包名的app版本号
	 * @param context
	 * @return
	 */
	public static int getVersionCodeXinye(Context context) {
		int versionCode = 0;
		PackageManager pm = context.getPackageManager();
		PackageInfo info = null;
		try {
			info = pm.getPackageInfo("com.landicorp.android.cibpay", 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		if (info != null) {
			versionCode = info.versionCode;
		}
		return versionCode;
	}

	/**
	 * 打开指定包名的App
	 */
	public void openOtherApp(String packageName, Activity mActivity) {
		PackageManager manager = mActivity.getPackageManager();
		Intent launchIntentForPackage = manager
				.getLaunchIntentForPackage(packageName);
		if (launchIntentForPackage != null) {
			mActivity.startActivity(launchIntentForPackage);
		}
	}

	/**
	 * 打开指定包名的App应用信息界面
	 */
	public void showAppInfo(String packageName, Activity mActivity) {
		Intent intent = new Intent();
		intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
		intent.setData(Uri.parse("package:" + packageName));
		mActivity.startActivity(intent);
	}

	/**
	 * 获取App信息的一个封装类(包名、版本号、应用信息、图标、名称等)
	 */
	public class AppEnging {
		public List<AppInfo> getAppInfos(Context context) {
			List<AppInfo> list = new ArrayList<AppInfo>();
			// 获取应用程序信息
			// 包的管理者
			PackageManager pm = context.getPackageManager();
			// 获取系统中安装到所有软件信息
			List<PackageInfo> installedPackages = pm.getInstalledPackages(0);
			for (PackageInfo packageInfo : installedPackages) {
				// 获取包名
				String packageName = packageInfo.packageName;
				// 获取版本号
				String versionName = packageInfo.versionName;
				// 获取application
				ApplicationInfo applicationInfo = packageInfo.applicationInfo;
				int uid = applicationInfo.uid;
				// 获取应用程序的图标
				Drawable icon = applicationInfo.loadIcon(pm);
				// 获取应用程序的名称
				String name = applicationInfo.loadLabel(pm).toString();
				// 是否是用户程序
				// 获取应用程序中相关信息,是否是系统程序和是否安装到SD卡
				boolean isUser;
				int flags = applicationInfo.flags;
				if ((applicationInfo.FLAG_SYSTEM & flags) == applicationInfo.FLAG_SYSTEM) {
					// 系统程序
					isUser = false;
				} else {
					// 用户程序
					isUser = true;
				}
				// 是否安装到SD卡
				boolean isSD;
				if ((applicationInfo.FLAG_EXTERNAL_STORAGE & flags) == applicationInfo.FLAG_EXTERNAL_STORAGE) {
					// 安装到了SD卡
					isSD = true;
				} else {
					// 安装到手机中
					isSD = false;
				}
				// 添加到bean中
				AppInfo appInfo = new AppInfo(name, icon, packageName,
						versionName, isSD, isUser);
				// 将bean存放到list集合
				list.add(appInfo);
			}
			return list;
		}
	}

	// 封装软件信息的bean类
	class AppInfo {
		// 名称
		private String name;
		// 图标
		private Drawable icon;
		// 包名
		private String packagName;
		// 版本号
		private String versionName;
		// 是否安装到SD卡
		private boolean isSD;
		// 是否是用户程序
		private boolean isUser;

		public AppInfo() {
			super();
		}

		public AppInfo(String name, Drawable icon, String packagName,
				String versionName, boolean isSD, boolean isUser) {
			super();
			this.name = name;
			this.icon = icon;
			this.packagName = packagName;
			this.versionName = versionName;
			this.isSD = isSD;
			this.isUser = isUser;
		}
	}

	/**
	 * 需添加<uses-permission android:name="android.permission.GET_TASKS"/>
	 * 并且必须是系统应用该方法才有效 判断当前App处于前台还是后台
	 */
	public static boolean isApplicationBackground(final Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		@SuppressWarnings("deprecation")
		List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
		if (!tasks.isEmpty()) {
			ComponentName topActivity = tasks.get(0).topActivity;
			if (!topActivity.getPackageName().equals(context.getPackageName())) {
				return true;
			}
		}
		return false;
	}
}
