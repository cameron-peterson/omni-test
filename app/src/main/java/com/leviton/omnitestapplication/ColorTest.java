package com.leviton.omnitestapplication;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.view.WindowManager;


public class ColorTest extends Activity {

	private final static String TAG = "ColorTest";
	private Iterator<Entry<String, Integer>> colorIterator = null;
	private boolean orientationTest = false;
	private boolean finished = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_color_test);

		// Don't allow the screen to dim while running the test
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		((TextView) findViewById(R.id.leviton_text)).setText(R.string.color_header);
		HashMap<String, Integer> colors = getColors();
		colorIterator = colors.entrySet().iterator();
		if (colorIterator.hasNext()) {
			transitionColor();
		}
		new View(this);
		findViewById(R.id.container).setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					if (colorIterator.hasNext()) {
						transitionColor();
					} else if (!finished) {
						findViewById(R.id.container).setBackgroundColor(0xFFFFFFFF);
						((TextView) findViewById(R.id.leviton_text)).setText(R.string.end_of_test);
						((TextView) findViewById(R.id.color_text)).setText("");
						finished = true;
					} else {
						Log.d(TAG, "Finished running ColorTest");
						finish();
					}
					return true;
				}
				return false;
			}
		});
	}

	private void transitionColor() {
		Entry<String, Integer> e = colorIterator.next();
		findViewById(R.id.container).setBackgroundColor(e.getValue());
		((TextView) findViewById(R.id.color_text)).setText(e.getKey());
		if (e.getKey().equals("Black")) {
			((TextView) findViewById(R.id.leviton_text)).setTextColor(0xFFFFFFFF);
			((TextView) findViewById(R.id.color_text)).setTextColor(0xFFFFFFFF);
		} else {
			((TextView) findViewById(R.id.leviton_text)).setTextColor(0xFF000000);
			((TextView) findViewById(R.id.color_text)).setTextColor(0xFF000000);
		}
		Log.d(TAG, "Set background color to: " + e.getKey());
	}

	private HashMap<String, Integer> getColors() {

		//Create arrays for color names and values
		String[] names = {"White", "Red", "Green", "Blue", "Black"};
		int[] colors = {0xFFFFFFFF, 0xFFFF0000, 0xFF00FF00, 0xFF0000FF, 0xFF000000};


		HashMap<String, Integer> colorPairs = new HashMap<>();

		for (int i = 0; i < colors.length; i++) {
			colorPairs.put(names[i], colors[i]);
		}
		return colorPairs;
	}
}