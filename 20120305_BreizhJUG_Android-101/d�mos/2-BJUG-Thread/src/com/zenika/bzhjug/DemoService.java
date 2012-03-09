package com.zenika.bzhjug;

import android.content.Intent;
import android.widget.Toast;


public class DemoService extends ParentService {

	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(getApplicationContext(), "Service started", Toast.LENGTH_SHORT).show();
		sleep(5000);
		return super.onStartCommand(intent, flags, startId);
	}

}
