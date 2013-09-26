package com.zenika.android.park.model.db.tables;

import static com.zenika.android.park.model.db.ParkContentProvider.BASE_URI;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.zenika.android.park.model.pojo.Parking;
import com.zenika.android.tools.db.AbstractContentProvider.ITable;

public class Parkings implements ITable {

	private static final String		TABLE_NAME			= "parking";
	private static final String		STRING_URI			= BASE_URI + TABLE_NAME;
	public static final Uri			CONTENT_URI			= Uri.parse(STRING_URI);

	public static final String		NAME				= "_name";
	public static final String		ID_OBJ				= "_id_obj";
	public static final String		LAT					= "_lat";
	public static final String		LON					= "_lon";
	public static final String		STATUS				= "_status";
	public static final String		DISPO				= "_dispo";
	public static final String		SEUIL_COMPLET		= "_complet";
	public static final String		HORO				= "_horodatage";

	public static final int			NAME_INDEX			= 1;
	public static final int			ID_OBJ_INDEX		= 2;
	public static final int			LAT_INDEX			= 3;
	public static final int			LON_INDEX			= 4;
	public static final int			STATUS_INDEX		= 5;
	public static final int			DISPO_INDEX			= 6;
	public static final int			SEUIL_COMPLET_INDEX	= 7;
	public static final int			HORO_INDEX			= 8;

	public static final String[]	PROJECTION			= new String[] {_ID, NAME, ID_OBJ, LAT, LON, STATUS, DISPO, SEUIL_COMPLET, HORO};

	public static final String		TABLE_CREATION		= "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + _ID
															+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, " + ID_OBJ + " INTEGER, " + LAT
															+ " DOUBLE, " + LON + " DOUBLE, " + STATUS + " INTEGER, " + DISPO + " INTEGER, "
															+ SEUIL_COMPLET + " INTEGER, " + HORO + " TEXT" + ");";

	public static final String		UPDATE_SELECTION	= ID_OBJ + " = ? AND (" + STATUS + " <> ? OR " + DISPO + " <> ?)";

	@Override
	public String getName() {
		return TABLE_NAME;
	}

	@Override
	public void upgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if(oldVersion == 0) {
			db.execSQL(TABLE_CREATION);
		}
	}

	public static ContentValues toContentValues(Parking p) {
		ContentValues cv = new ContentValues();
		cv.put(ID_OBJ, p.getObjId());
		cv.put(NAME, p.getName());
		cv.put(STATUS, p.getStatus().getCode());
		cv.put(DISPO, p.getDispo());
		cv.put(SEUIL_COMPLET, p.getSeuil());
		cv.put(HORO, p.getHoro());
		cv.put(ID_OBJ, p.getObjId());
		if(p.getLat() != -1 && p.getLon() != -1) {
			cv.put(LAT, p.getLat());
			cv.put(LON, p.getLon());
		}
		return cv;
	}

}
