package com.zenika.android.tools.utils;

import java.lang.reflect.Array;


public class Arrays {
	
	public static <E> E[] append(Class<E> clazz, E[] src, E elem){
		int length = src == null ? 0 : src.length;
		@SuppressWarnings("unchecked")
		E[] dest = (E[]) Array.newInstance(clazz, length + 1);
		copy(src, dest);
		dest[length] = elem;
		return dest;
	}
	
	public static <E> void copy(E[] src, E[] dest){
		for(int i = 0; src != null && i < src.length; i++){
			dest[i] = src[i];
		}
	}

}
