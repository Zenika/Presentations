package com.zenika.android.park.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import com.zenika.android.park.app.Conf;
import com.zenika.android.park.model.db.Prefs;
import com.zenika.android.park.utils.CsvUtils;
import com.zenika.android.tools.utils.Version;

public class CsvImportService extends Service {

	private static final String	TAG	= CsvImportService.class.getSimpleName();

	private Context				mContext;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if(Conf.DEBUG) Log.v(TAG, "onStartCommand");
		mContext = getApplicationContext();
		new CsvImportTask().execute();
		return START_NOT_STICKY;
	}

	private class CsvImportTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			if(CsvUtils.importCsv(mContext)) {
				Prefs.setInt(mContext, Prefs.UI_PRFS, Prefs.INIT_V, Version.getVersionCode(mContext));
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			stopSelf();
		}

	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
