package com.zenika.android.park.utils;

import static com.zenika.android.park.model.db.tables.Parkings.ID_OBJ;
import static com.zenika.android.park.model.db.tables.Parkings.LAT;
import static com.zenika.android.park.model.db.tables.Parkings.LON;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import com.zenika.android.park.R;
import com.zenika.android.park.app.Conf;
import com.zenika.android.park.model.db.tables.Parkings;


public class CsvUtils {
	
	private static final String TAG = CsvUtils.class.getSimpleName();
	
	public static boolean importCsv(Context context) {
		ContentResolver resolver = context.getContentResolver();
		InputStream inputStream = context.getResources().openRawResource(R.raw.parkings);
		Scanner scanner = new Scanner(inputStream);
		try {
			// skip headers
			scanner.nextLine();
			resolver.delete(Parkings.CONTENT_URI, null, null);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] cells = line.split(";");
				ContentValues cv = new ContentValues(3);
				cv.put(ID_OBJ, cells[0]);
				cv.put(LAT, cells[15]);
				cv.put(LON, cells[14]);
				resolver.insert(Parkings.CONTENT_URI, cv);
			}
			return true;
		}
		catch(Exception e) {
			if(Conf.DEBUG) Log.e(TAG, "doInBackground", e);
		}
		finally {
			scanner.close();
			try {
				inputStream.close();
			}
			catch(IOException e) {
				if(Conf.DEBUG) Log.e(TAG, "doInBackground", e);
			}
		}
		return false;
	}
	
}
