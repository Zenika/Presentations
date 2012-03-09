package com.zenika.bzhjug;

import android.content.Intent;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EService;
import com.googlecode.androidannotations.annotations.UiThread;

@EService
public class DemoService extends ParentService {

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		serviceStarted();
		return super.onStartCommand(intent, flags, startId);
	}

	@UiThread
	protected void serviceStarted() {
		Toast.makeText(getApplicationContext(), "Service started", Toast.LENGTH_SHORT).show();
		backgroundStuff();
	}

	@Background
	protected void backgroundStuff() {
		sleep(5000);
		serviceFinished();
	}

	@UiThread
	protected void serviceFinished() {
		Toast.makeText(getApplicationContext(), "Service finished", Toast.LENGTH_SHORT).show();
	}

}
