package com.zenika.bzhjug;

import android.app.Activity;
import android.content.Intent;

import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.zenika.bzhjug.demo3.R;

@EActivity(R.layout.main)
public class HelloActivity extends Activity {
	
	@Click
	public void goButton() {
		startService(new Intent(getApplicationContext(), DemoService_.class));
	}
}