package com.zenika.bzhjug;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

import com.zenika.bzhjug.demo1.R;

public class PickContactDemo extends Activity {

	private static final int	REQUEST_PICK_CONTACT	= 2;

	private int					mCpt;
	private TextView			mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.pick);

		mTextView = (TextView) findViewById(R.id.selected_tv);
		refreshText();
	}

	public void onClickSelect(View v) {
		// picking a picture or picking a contact used 2 different component from 2 different apps
		// but in our we just change the Uri
		Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//		Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
		startActivityForResult(i, REQUEST_PICK_CONTACT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(REQUEST_PICK_CONTACT == requestCode) {
			if(resultCode == RESULT_OK) {
				// I just increment a counter here, but data.getData() gives the Uri of the contact, so we can retrieve the contact data
				mCpt++;
				refreshText();
			}
		}
	}

	private void refreshText() {
		mTextView.setText(this.getString(R.string.contacts_selected, mCpt));
	}

}
