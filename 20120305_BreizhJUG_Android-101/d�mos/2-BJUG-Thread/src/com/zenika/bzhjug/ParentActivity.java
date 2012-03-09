package com.zenika.bzhjug;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.zenika.bzhjug.demo2.R;

public abstract class ParentActivity extends Activity {
	
	private static final String EXTRA_DEMO = "_demo";

	private static final int DEMO_0 = 0;
	private static final int DEMO_1 = 1;
	private static final int DEMO_2 = 2;
	private static final int DEMO_3 = 3;
	private static final int DEMO_4 = 4;
	private static final int DEMO_5 = 5;
	private static final int DEMO_6 = 6;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		int n = getIntent().getIntExtra(EXTRA_DEMO, 0);
		TextView tv = (TextView) findViewById(R.id.name);
		tv.setText("Demo Thread "+n);
	}
	
	public abstract void imageClicked(View v);

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0,DEMO_0,0, "Demo 0");
		menu.add(0,DEMO_1,0, "Demo 1");
		menu.add(0,DEMO_2,0, "Demo 2");
		menu.add(0,DEMO_3,0, "Demo 3");
		menu.add(0,DEMO_4,0, "Demo 4");
		menu.add(0,DEMO_5,0, "Demo 5");
		menu.add(0,DEMO_6,0, "Demo 6");
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i = null;
		switch (item.getItemId()) {
			case DEMO_0:
				i = new Intent(getApplicationContext(), NormalActivity.class);
				break;
			case DEMO_1:
				i = new Intent(getApplicationContext(), Demo1Activity.class);
				break;
			case DEMO_2:
				i = new Intent(getApplicationContext(), Demo2Activity.class);
				break;
			case DEMO_3:
				i = new Intent(getApplicationContext(), Demo3Activity.class);
				break;
			case DEMO_4:
				i = new Intent(getApplicationContext(), Demo4Activity.class);
				break;
			case DEMO_5:
				i = new Intent(getApplicationContext(), Demo5Activity.class);
				break;
			case DEMO_6:
				i = new Intent(getApplicationContext(), Demo6Activity.class);
				break;
		}
		if(i != null) {
			i.putExtra(EXTRA_DEMO, item.getItemId());
			startActivity(i);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	


	protected void sleep(long time) {
		try {
			Thread.sleep(time);
		}
		catch(InterruptedException e) {}
	}
}
