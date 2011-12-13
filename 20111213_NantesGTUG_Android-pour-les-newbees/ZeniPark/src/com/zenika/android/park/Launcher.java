package com.zenika.android.park;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.zenika.android.park.model.db.Prefs;
import com.zenika.android.park.service.CsvImportService;
import com.zenika.android.park.ui.MainActivity;
import com.zenika.android.tools.utils.Version;


public class Launcher extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Context context = getApplicationContext();
		
		if(Prefs.getInt(context, Prefs.UI_PRFS, Prefs.INIT_V, 0) < Version.getVersionCode(context)) {
			startService(new Intent(context, CsvImportService.class));
		}
		
		startActivity(new Intent(context, MainActivity.class));
		finish();
	}

}
