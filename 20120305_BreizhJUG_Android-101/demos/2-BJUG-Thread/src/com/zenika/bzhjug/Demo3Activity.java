package com.zenika.bzhjug;

import android.content.Intent;
import android.view.View;

public class Demo3Activity extends ParentActivity {

	public void imageClicked(View v) {
		startService(new Intent(getApplicationContext(), NormalService.class));
		sleep(5000);
	}
}