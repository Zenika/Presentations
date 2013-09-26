package com.zenika.bzhjug;

import android.app.Activity;
import android.os.Bundle;

import com.zenika.bzhjug.demo1.R;

public class HomeInterceptor extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
}