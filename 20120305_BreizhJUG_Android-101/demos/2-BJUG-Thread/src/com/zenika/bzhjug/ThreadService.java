package com.zenika.bzhjug;

import android.content.Intent;
import android.widget.Toast;

public class ThreadService extends ParentService {

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		new Thread() {

			public void run() {
				Toast.makeText(getApplicationContext(), "Service started", Toast.LENGTH_SHORT).show();
				ThreadService.this.sleep(5000);
			}
		}.start();
		return super.onStartCommand(intent, flags, startId);
	}

}
