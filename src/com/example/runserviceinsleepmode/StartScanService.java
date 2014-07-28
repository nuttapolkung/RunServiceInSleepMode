package com.example.runserviceinsleepmode;

import java.util.Timer;
import java.util.TimerTask;

import javax.crypto.spec.PSource;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class StartScanService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		
		return null;
	}
	
	@Override
	public void onDestroy() {
		
		if(timer!=null)
		{
			timer.cancel();
			timer = null;
		}
		stopForeground(true);
		super.onDestroy();
	}
	
	private  Timer timer;
	private int loop = 0;
	private StartScanService ctx;
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		ctx = this;
		
		 timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				Log.d("RunServiceInSleepMode", "Run" + loop);
				
				loop ++;
				if(loop>=5)
				{
					
					post();
					timer.cancel();
				}
			}
		}, 1000, 5000);
		
		return START_NOT_STICKY;
	}
	
	
	
	private void post()
	{
		Intent intent2 = new Intent(ctx, MainActivity.class);
		PendingIntent pi = PendingIntent.getActivity(ctx, 0, intent2,   PendingIntent.FLAG_UPDATE_CURRENT);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx);

		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setTicker("App info string");
		builder.setContentIntent(pi);
		builder.setOngoing(true);
		long[] vibraPattern = {0, 500, 250, 500 };
		builder.setVibrate(vibraPattern);
		builder.setOnlyAlertOnce(true);
		builder.setAutoCancel(true);
		Notification notification = builder.build();
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		// optionally set a custom view
		startForeground(1234, notification);
	}

}
