package com.zenika.android.tools.utils;

public class Nulls {

	public static <T> T replaceNull(T potentialNull, T notNullReplacement) {
		if(notNullReplacement == null) throw new IllegalArgumentException("second parameter must not be null");
		return potentialNull == null ? notNullReplacement : potentialNull;
	}

	public static String replaceNullOrEmpty(String potentialNullOrEmpty, String notNullNorEmptynotNullReplacement) {
		return replaceNull(potentialNullOrEmpty, notNullNorEmptynotNullReplacement).length() == 0
				? notNullNorEmptynotNullReplacement
				: potentialNullOrEmpty;
	}

}
