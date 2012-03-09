package com.zenika.bzhjug;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public abstract class ParentService extends Service {

	protected void sleep(long time) {
		try {
			Thread.sleep(time);
		}
		catch(InterruptedException e) {}
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
