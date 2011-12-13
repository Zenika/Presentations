package com.zenika.android.park.service;

import org.springframework.web.client.RestTemplate;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import com.zenika.android.park.R;
import com.zenika.android.park.app.Conf;
import com.zenika.android.park.model.db.tables.Parkings;
import com.zenika.android.park.model.pojo.Parking;
import com.zenika.android.park.model.pojo.ParkingList;

public class UpdateParkingService extends Service {

	private static final String	TAG			= UpdateParkingService.class.getSimpleName();

	private static boolean		sRunning	= false;

	private Context				mContext;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if(Conf.DEBUG) Log.v(TAG, "onStartCommand");
		mContext = getApplicationContext();
		if(!sRunning) {
			if(Conf.DEBUG) Log.v(TAG, "onStartCommand Go");
			new UpdateParkingTask().execute();
			sRunning = true;
		}
		else {
			stopSelf();
		}
		return START_NOT_STICKY;
	}

	private class UpdateParkingTask extends AsyncTask<Void, Void, Integer> {

		@Override
		protected Integer doInBackground(Void... params) {
			RestTemplate template = new RestTemplate();
			String url = mContext.getString(R.string.open_data_url, mContext.getString(R.string.open_data_key));
			ParkingList parkingList = template.getForObject(url, ParkingList.class);
			ContentResolver resolver = mContext.getContentResolver();
			String[] args = new String[3];
			int cpt = 0;
			for(Parking p : parkingList.getParkings()) {
				args[0] = Long.toString(p.getObjId());
				args[1] = Integer.toString(p.getStatus().getCode());
				args[2] = Integer.toString(p.getDispo());
				ContentValues values = Parkings.toContentValues(p);
				if(resolver.update(Parkings.CONTENT_URI, values, Parkings.UPDATE_SELECTION, args) > 0) {
					if(Conf.DEBUG) Log.d(TAG, p.getName() + " updated");
					cpt++;
				}
			}
			return cpt;
		}

		@Override
		protected void onPostExecute(Integer result) {
			Toast.makeText(getApplicationContext(), "MAJ : " + result, Toast.LENGTH_SHORT).show();
			sRunning = false;
			stopSelf();
		}

	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
