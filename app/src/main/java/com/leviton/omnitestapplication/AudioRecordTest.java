package com.leviton.omnitestapplication;

/**
 * Created by wqm on 6/30/16.
 */

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;


public class AudioRecordTest extends Activity
{
    private static final String LOG_TAG = "AudioRecordTest";
    private static String mFileName = null;

    private MediaRecorder mRecorder = null;

    private MediaPlayer   mPlayer = null;

    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    public AudioRecordTest() {
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.e(LOG_TAG, mFileName);
        mFileName += "/audiorecordtest.3gp";
        Log.e(LOG_TAG, mFileName);
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
		setContentView(R.layout.activity_audio_record_test);

        final Button recordButton =  (Button) findViewById(R.id.record_button);
		final Button playButton =  (Button) findViewById(R.id.play_button);
		final Button exitButton =  (Button) findViewById(R.id.exit_button);

        final boolean[] mStartPlaying = {true};
        final boolean[] mStartRecording = {true};

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecord(mStartRecording[0]);
                if (mStartRecording[0]) {
                    recordButton.setText(R.string.stop_recording);
                } else {
                    recordButton.setText(R.string.start_recording);
                }
                mStartRecording[0] = !mStartRecording[0];
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlay(mStartPlaying[0]);
                if (mStartPlaying[0]) {
                    playButton.setText(R.string.stop_playing);
                } else {
                    playButton.setText(R.string.start_playing);
                }
                mStartPlaying[0] = !mStartPlaying[0];
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}
