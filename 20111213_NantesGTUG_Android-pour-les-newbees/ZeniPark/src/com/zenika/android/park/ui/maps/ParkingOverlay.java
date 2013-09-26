package com.zenika.android.park.ui.maps;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.google.android.maps.ItemizedOverlay;
import com.zenika.android.park.R;
import com.zenika.android.park.model.pojo.Parking;
import com.zenika.android.park.model.pojo.Parking.Status;

public class ParkingOverlay extends ItemizedOverlay<ParkingOverlayItem> {

	private static SoftReference<Drawable>	sOrangeMarker;
	private static SoftReference<Drawable>	sGreenMarker;
	private static SoftReference<Drawable>	sRedMarker;

	private List<ParkingOverlayItem>		mItems;
	private Context							mContext;
	private Drawable						mMarker;

	private ParkingOverlay(Context context, Drawable marker) {
		super(boundCenterBottom(marker));
		mItems = new ArrayList<ParkingOverlayItem>();
		mContext = context;
		mMarker = marker;
	}

	public void addItem(ParkingOverlayItem item) {
		mItems.add(item);
		populate();
	}

	public void addAllItems(Collection<ParkingOverlayItem> items) {
		mItems.addAll(items);
		populate();
	}

	public void clear() {
		mItems.clear();
		populate();
	}

	@Override
	protected ParkingOverlayItem createItem(int index) {
		return mItems.get(index);
	}

	@Override
	public int size() {
		return mItems.size();
	}

	@Override
	protected boolean onTap(int index) {
		ParkingOverlayItem item = mItems.get(index);
		Parking p = item.getParking();
		AlertDialog.Builder dBuilder = new AlertDialog.Builder(mContext)
		.setIcon(mMarker)
		.setTitle(p.getName());
		switch (p.getState()) {
			case SURE_NOT:
				if(p.getStatus() != Status.OUVERT) {
					dBuilder.setMessage(R.string.park_closed);
				}
				else {
					dBuilder.setMessage(R.string.park_full);
				}
				break;
			case MAY_NOT:
				dBuilder.setMessage(mContext.getString(R.string.park_may_be_full, p.getDispo()));
				break;
			case IS:
				dBuilder.setMessage(mContext.getString(R.string.park_not_full, p.getDispo()));
				break;
			default:
				dBuilder.setMessage(R.string.park_no_data);
				break;
		}
		dBuilder.show();
		return true;
	}

	public static ParkingOverlay newInstance(Context context) {
		Drawable marker = context.getResources().getDrawable(R.drawable.parking);
		return new ParkingOverlay(context, marker);
	}

	public static Drawable getOrangeMarker(Context context) {
		if(sOrangeMarker == null || sOrangeMarker.get() == null) {
			Drawable d = context.getResources().getDrawable(R.drawable.orange_parking);
			sOrangeMarker = new SoftReference<Drawable>(d);
		}
		return boundCenterBottom(sOrangeMarker.get());
	}

	public static Drawable getGreenMarker(Context context) {
		if(sGreenMarker == null || sGreenMarker.get() == null) {
			Drawable d = context.getResources().getDrawable(R.drawable.green_parking);
			sGreenMarker = new SoftReference<Drawable>(d);
		}
		return boundCenterBottom(sGreenMarker.get());
	}

	public static Drawable getRedMarker(Context context) {
		if(sRedMarker == null || sRedMarker.get() == null) {
			Drawable d = context.getResources().getDrawable(R.drawable.red_parking);
			sRedMarker = new SoftReference<Drawable>(d);
		}
		return boundCenterBottom(sRedMarker.get());
	}

}
