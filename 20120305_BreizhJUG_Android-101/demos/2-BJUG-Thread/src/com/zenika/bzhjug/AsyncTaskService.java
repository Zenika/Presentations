package com.zenika.bzhjug;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;


public class AsyncTaskService extends ParentService {

	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		new AsyncTask<Void, Void, Void>() {
			
			@Override
			protected void onPreExecute() {
				Toast.makeText(getApplicationContext(), "Service started", Toast.LENGTH_SHORT).show();
			}

			@Override
			protected Void doInBackground(Void... params) {
				sleep(5000);
				return null;
			}
			
			@Override
			protected void onPostExecute(Void result) {
				Toast.makeText(getApplicationContext(), "Service finished", Toast.LENGTH_SHORT).show();
			}
			
		}.execute();
		return super.onStartCommand(intent, flags, startId);
	}

}
