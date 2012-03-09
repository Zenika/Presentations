package com.zenika.bzhjug;

import android.content.Intent;
import android.view.View;

public class Demo4Activity extends ParentActivity {

	public void imageClicked(View v) {
		startService(new Intent(getApplicationContext(), DemoService.class));
	}
}