package com.zenika.bzhjug;

import android.view.View;
import android.widget.Toast;

public class Demo1Activity extends ParentActivity {

	public void imageClicked(View v) {
		Toast.makeText(getApplicationContext(), "Image Clicked", Toast.LENGTH_SHORT).show();
		sleep(5000);
	}
}