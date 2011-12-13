package com.zenika.android.park.model.pojo;

import static com.zenika.android.park.model.db.tables.Parkings.DISPO_INDEX;
import static com.zenika.android.park.model.db.tables.Parkings.HORO_INDEX;
import static com.zenika.android.park.model.db.tables.Parkings.ID_OBJ_INDEX;
import static com.zenika.android.park.model.db.tables.Parkings.LAT_INDEX;
import static com.zenika.android.park.model.db.tables.Parkings.LON_INDEX;
import static com.zenika.android.park.model.db.tables.Parkings.NAME_INDEX;
import static com.zenika.android.park.model.db.tables.Parkings.SEUIL_COMPLET_INDEX;
import static com.zenika.android.park.model.db.tables.Parkings.STATUS_INDEX;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import android.database.Cursor;
import android.util.Log;

@Root(strict = false)
public class Parking {

	public static final SimpleDateFormat	DATE_FORMAT	= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	private static final String				TAG			= Parking.class.getSimpleName();
	private static final long				DELAY		= TimeUnit.SECONDS.toMillis(15 * 60);

	@Element(name = "IdObj")
	private long							mObjId		= -1;
	@Element(name = "Grp_nom")
	private String							mName		= null;
	@Element(name = "Grp_statut")
	private int								mStatus;
	@Element(name = "Grp_disponible")
	private int								mDispo		= -1;
	@Element(name = "Grp_complet")
	private int								mSeuil		= -1;
	@Element(name = "Grp_horodatage")
	private String							mHoro		= null;
	private float							mLat		= -1;
	private float							mLon		= -1;

	public String getName() {
		return mName;
	}

	public long getObjId() {
		return mObjId;
	}

	public float getLat() {
		return mLat;
	}

	public float getLon() {
		return mLon;
	}

	public Status getStatus() {
		return Status.fromCode(mStatus);
	}

	public int getDispo() {
		return mDispo;
	}

	public int getSeuil() {
		return mSeuil;
	}

	public String getHoro() {
		return mHoro;
	}

	public State getState() {
		try {
			Date parkDate = DATE_FORMAT.parse(getHoro());
			Date nowMinusDelay = new Date(System.currentTimeMillis() - DELAY);
			if(parkDate.after(nowMinusDelay)) {
				if(getStatus() != Status.OUVERT || getDispo() <= 0) return State.SURE_NOT;
				if(getDispo() <= getSeuil()) return State.MAY_NOT;
				return State.IS;
			}
		}
		catch(Exception e) {
			Log.w(TAG, e.getLocalizedMessage(), e);
		}
		return State.OLD;
	}

	public static Parking fromCursor(Cursor cursor) {
		if(cursor.isBeforeFirst() || cursor.isAfterLast() || cursor.isClosed()) throw new IllegalArgumentException();
		Parking p = new Parking();
		p.mLat = cursor.getFloat(LAT_INDEX);
		p.mLon = cursor.getFloat(LON_INDEX);
		p.mName = cursor.getString(NAME_INDEX);
		p.mStatus = cursor.getInt(STATUS_INDEX);
		p.mObjId = cursor.getLong(ID_OBJ_INDEX);
		p.mDispo = cursor.getInt(DISPO_INDEX);
		p.mSeuil = cursor.getInt(SEUIL_COMPLET_INDEX);
		p.mHoro = cursor.getString(HORO_INDEX);
		return p;
	}

	public enum Status {
		INVALIDE(0), FERME(1), ABONNES(2), OUVERT(5);

		private int	mCode;

		Status(int code) {
			mCode = code;
		}

		public int getCode() {
			return mCode;
		}

		public static Status fromCode(int code) {
			switch (code) {
				case 1:
					return FERME;
				case 2:
					return ABONNES;
				case 5:
					return OUVERT;
				default:
					return INVALIDE;
			}
		}
	}

	public enum State {
		OLD, SURE_NOT, MAY_NOT, IS;
	}

}
