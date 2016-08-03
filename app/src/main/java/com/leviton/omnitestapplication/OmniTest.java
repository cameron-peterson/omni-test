package com.leviton.omnitestapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

import java.io.FileWriter;
import java.io.IOException;

import static com.leviton.omnitestapplication.R.id.button_audio_test;
import static com.leviton.omnitestapplication.R.id.button_color_test;
import static com.leviton.omnitestapplication.R.id.button_exit;
import static com.leviton.omnitestapplication.R.id.button_led_test;
import static com.leviton.omnitestapplication.R.id.button_orientation_test;
import static com.leviton.omnitestapplication.R.layout.activity_omnitest;

/**
 * Main activity for the Omni test application
 * <p>
 * Created by Cam Peterson on 8/1/16.
 * <p>
 * Copyright (c) 2016 Leviton Manufacturing Company, Incorporated
 */
public class OmniTest extends Activity {

    @Override
	protected void onCreate(Bundle savedInstanceState) {

        final boolean[] ledsOn = {false};
		super.onCreate(savedInstanceState);

		setContentView(activity_omnitest);

		// Don't allow the screen to dim while running the test
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		final Button buttonAudioTest =  (Button) findViewById(button_audio_test);
		final Button buttonOrientationTest =  (Button) findViewById(button_orientation_test);
		final Button buttonColorTest =  (Button) findViewById(button_color_test);
        final Button buttonLedTest = (Button) findViewById(button_led_test);
		final Button buttonExit =  (Button) findViewById(button_exit);

		buttonAudioTest.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			    startActivity(new Intent(OmniTest.this, AudioRecordTest.class));

			}
		});

		buttonOrientationTest.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                startActivity(new Intent(OmniTest.this, OrientationTest.class));
			}
		});

		buttonColorTest.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(OmniTest.this, ColorTest.class));
			}
		});

		buttonLedTest.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                if(ledsOn[0]) {
                    //update button text.
                    buttonLedTest.setText(R.string.turn_leds_on);
                    //send command to turn off the leds
                    try {
                        FileWriter fw = new FileWriter("/sys/devices/platform/leds-gpio/leds/red_led/brightness");
                        fw.write(0);
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    //update button text.
                    buttonLedTest.setText(R.string.turn_leds_off);
                    //send command to turn on the leds
                    try {
                        FileWriter fw = new FileWriter("/sys/devices/platform/leds-gpio/leds/red_led/brightness");
                        fw.write(100);
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
				ledsOn[0] = !ledsOn[0];
			}
		});

		buttonExit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
