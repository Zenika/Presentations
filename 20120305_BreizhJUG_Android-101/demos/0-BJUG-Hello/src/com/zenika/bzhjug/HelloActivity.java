package com.zenika.bzhjug;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.zenika.bzhjug.demo0.R;

public class HelloActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button goButton = (Button) findViewById(R.id.go_button);
		// the call to another method here aims to easily compare with AndroidAnnotation version
		goButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				clickGoButton();
			}
		});
	}
	
	public void clickGoButton() {
		Toast.makeText(getApplicationContext(), "Go clicked", Toast.LENGTH_SHORT).show();
	}
}