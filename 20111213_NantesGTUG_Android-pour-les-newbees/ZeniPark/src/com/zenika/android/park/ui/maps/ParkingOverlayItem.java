package com.zenika.android.park.ui.maps;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;
import com.zenika.android.park.model.pojo.Parking;

public class ParkingOverlayItem extends OverlayItem {

	private Context	mContext;
	private Parking	mParking;

	private ParkingOverlayItem(Context context, GeoPoint point, Parking parking) {
		super(point, parking.getName(), null);
		mContext = context;
		mParking = parking;
	}

	@Override
	public Drawable getMarker(int stateBitset) {
		switch (mParking.getState()) {
			case SURE_NOT:
				return ParkingOverlay.getRedMarker(mContext);
			case MAY_NOT:
				return ParkingOverlay.getOrangeMarker(mContext);
			case IS:
				return ParkingOverlay.getGreenMarker(mContext);
			default:
				return null;
		}
	}

	public Parking getParking() {
		return mParking;
	}

	public static ParkingOverlayItem newInstance(Context context, Parking p) {
		double lat = p.getLat() * 1E6;
		double lon = p.getLon() * 1E6;
		GeoPoint point = new GeoPoint((int) lat, (int) lon);
		return new ParkingOverlayItem(context, point, p);
	}

}
