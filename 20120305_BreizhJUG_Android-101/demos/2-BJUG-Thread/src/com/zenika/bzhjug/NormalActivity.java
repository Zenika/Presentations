package com.zenika.bzhjug;

import android.view.View;
import android.widget.Toast;

public class NormalActivity extends ParentActivity {

	@Override
	public void imageClicked(View v) {
		Toast.makeText(getApplicationContext(), "Image Clicked", Toast.LENGTH_SHORT).show();
	}

}
