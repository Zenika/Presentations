package com.zenika.android.tools.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PrefsManager {

	public static boolean getBoolean(Context context, String prefsName, String key, boolean defaultValue) {
		SharedPreferences prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
		return prefs.getBoolean(key, defaultValue);
	}

	public static void setBoolean(Context context, String prefsName, String key, boolean value) {
		SharedPreferences prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public static long getLong(Context context, String prefsName, String key, long defaultValue) {
		SharedPreferences prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
		return prefs.getLong(key, defaultValue);
	}

	public static void setLong(Context context, String prefsName, String key, long value) {
		SharedPreferences prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	public static int getInt(Context context, String prefsName, String key, int defaultValue) {
		SharedPreferences prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
		return prefs.getInt(key, defaultValue);
	}

	public static void setInt(Context context, String prefsName, String key, int value) {
		SharedPreferences prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public static float getFloat(Context context, String prefsName, String key, float defaultValue) {
		SharedPreferences prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
		return prefs.getFloat(key, defaultValue);
	}

	public static void setFloat(Context context, String prefsName, String key, float value) {
		SharedPreferences prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putFloat(key, value);
		editor.commit();
	}

	public static String getString(Context context, String prefsName, String key, String defaultValue) {
		SharedPreferences prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
		return prefs.getString(key, defaultValue);
	}

	public static void setString(Context context, String prefsName, String key, String value) {
		SharedPreferences prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putString(key, value);
		editor.commit();
	}
}
