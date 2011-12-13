package com.zenika.android.park.ui;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.zenika.android.park.R;
import com.zenika.android.park.app.Conf;
import com.zenika.android.park.app.Intents;
import com.zenika.android.park.model.db.tables.Parkings;
import com.zenika.android.park.model.pojo.Parking;
import com.zenika.android.park.ui.maps.ParkingOverlay;
import com.zenika.android.park.ui.maps.ParkingOverlayItem;

public class MainActivity extends MapActivity {

	private static final String	TAG				= MainActivity.class.getSimpleName();

	private static final int	MENU_REFRESH	= 1;

	private Context				mContext;

	private MapView				mMapView;
	private MapController		mMapController;
	private MyLocationOverlay	mMyLocationOverlay;
	private ParkingOverlay		mParkingOverlay;

	private QueryParkingTask	mQueryParkingTask;
	private ParkingObserver		mParkingObserver;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		if(Conf.DEBUG) Log.v(TAG, "onCreate");

		mContext = getApplicationContext();

		setContentView(R.layout.main);

		mMapView = (MapView) findViewById(R.id.my_map);
		mMapView.setBuiltInZoomControls(true);
		mMapView.setSatellite(true);

		mMapController = mMapView.getController();
		mMapController.setZoom(17);

		mMyLocationOverlay = new MyLocationOverlay(mContext, mMapView);
		mMyLocationOverlay.runOnFirstFix(new Runnable() {

			@Override
			public void run() {
				mMapController.animateTo(mMyLocationOverlay.getMyLocation());
			}
		});

		mParkingOverlay = ParkingOverlay.newInstance(this);

		List<Overlay> overlays = mMapView.getOverlays();
		overlays.clear();
		overlays.add(mMyLocationOverlay);
		overlays.add(mParkingOverlay);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if(Conf.DEBUG) Log.v(TAG, "onResume");
		mMyLocationOverlay.enableMyLocation();
		queryParkings();
		mParkingObserver = new ParkingObserver();
		getContentResolver().registerContentObserver(Parkings.CONTENT_URI, true, mParkingObserver);
		startService(new Intent(Intents.ACTION_START_UPDATE_SERVICE));
	}

	@Override
	protected void onPause() {
		if(Conf.DEBUG) Log.v(TAG, "onPause");
		getContentResolver().unregisterContentObserver(mParkingObserver);
		stopQuery();
		mMyLocationOverlay.disableMyLocation();
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MENU_REFRESH, 0, R.string.menu_update).setIcon(android.R.drawable.ic_menu_recent_history);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(MENU_REFRESH == item.getItemId()) {
			startService(new Intent(Intents.ACTION_START_UPDATE_SERVICE));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void queryParkings() {
		if(mQueryParkingTask == null || Status.FINISHED == mQueryParkingTask.getStatus()) {
			mQueryParkingTask = new QueryParkingTask();
			mQueryParkingTask.execute();
		}
	}

	private void stopQuery() {
		if(mQueryParkingTask != null) {
			mQueryParkingTask.cancel(false);
		}
	}

	private class QueryParkingTask extends AsyncTask<Void, Void, List<ParkingOverlayItem>> {

		@Override
		protected List<ParkingOverlayItem> doInBackground(Void... params) {
			Cursor cursor = getContentResolver().query(Parkings.CONTENT_URI, Parkings.PROJECTION, Parkings.NAME + " NOT NULL", null, null);
			List<ParkingOverlayItem> items = new ArrayList<ParkingOverlayItem>();
			if(cursor.moveToFirst()) {
				do {
					Parking parking = Parking.fromCursor(cursor);
					ParkingOverlayItem item = ParkingOverlayItem.newInstance(mContext, parking);
					items.add(item);
				}
				while (cursor.moveToNext());
			}
			return items;
		}

		@Override
		protected void onPostExecute(List<ParkingOverlayItem> result) {
			mParkingOverlay.clear();
			mParkingOverlay.addAllItems(result);
			mMapView.invalidate();
		}

	}

	private class ParkingObserver extends ContentObserver {

		public ParkingObserver() {
			super(new Handler());
		}

		@Override
		public void onChange(boolean selfChange) {
			queryParkings();
		}

	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}
