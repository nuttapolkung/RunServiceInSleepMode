package com.example.runserviceinsleepmode;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ScanActivity extends Activity {

	private Timer timer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	protected void onDestroy() {
		
		if(timer!=null)
		{
			timer.cancel();
			timer = null;
		}
		super.onDestroy();
	}
	
	@Override
	protected void onStart() {
		
		timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				Log.d("RunServiceInSleepMode", "Run");
			}
		}, 3000, 5000);
		
		super.onStart();
	}
}
