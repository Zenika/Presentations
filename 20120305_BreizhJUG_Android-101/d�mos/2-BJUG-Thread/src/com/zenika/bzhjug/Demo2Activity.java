package com.zenika.bzhjug;

import android.content.Intent;
import android.view.View;

public class Demo2Activity extends ParentActivity {

	public void imageClicked(View v) {
		startService(new Intent(getApplicationContext(), NormalService.class));
	}
}