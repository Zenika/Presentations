package com.zenika.android.tools.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

public class Version {
	
	private static final String TAG = Version.class.getSimpleName();

	public static int getVersionCode(Context context)
	{
		PackageManager pm = context.getPackageManager();
		try {
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			return pi.versionCode;
		} catch (NameNotFoundException e) {
			Log.e(TAG, "getVersionCode", e);
		}
		return -1;
	}

	/**
	 * Get the versionname from the manifest
	 * @param a_Context
	 * @return versionname
	 */
	public static String getVersionName(Context context)
	{
		String versionName = "";
		PackageManager pm = context.getPackageManager();
		try {
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
		} catch (NameNotFoundException e) {
			Log.e(TAG, "getVersionName", e);
		}
		return versionName;
	}

}
