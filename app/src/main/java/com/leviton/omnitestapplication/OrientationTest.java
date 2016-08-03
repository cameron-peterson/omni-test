package com.leviton.omnitestapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;


public class OrientationTest extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_orientation_test);
		
		// Don't allow the screen to dim while running the test
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		final Button button1 =  (Button) findViewById(R.id.button_audio_test);
		final Button button2 =  (Button) findViewById(R.id.button_orientation_test);
		final Button button3 =  (Button) findViewById(R.id.button_color_test);
		final Button button4 =  (Button) findViewById(R.id.button4);
		button2.setEnabled(false);
		button3.setEnabled(false);
		button4.setEnabled(false);
		
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				button1.setEnabled(false);
				button2.setEnabled(true);
				button3.setEnabled(false);
				button4.setEnabled(false);
			}
		});
		
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				button1.setEnabled(false);
				button2.setEnabled(false);
				button3.setEnabled(true);
				button4.setEnabled(false);
			}
		});
		
		button3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				button1.setEnabled(false);
				button2.setEnabled(false);
				button3.setEnabled(false);
				button4.setEnabled(true);
			}
		});
		
		button4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
