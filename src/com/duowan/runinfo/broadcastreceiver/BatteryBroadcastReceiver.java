package com.duowan.runinfo.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.duowan.runinfo.service.RunInfoUitl;
import com.duowan.util.LogCat;

public class BatteryBroadcastReceiver extends BroadcastReceiver {
	private static final String ACTION_BATTERY_CHANGED = "android.intent.action.BATTERY_CHANGED";
	
	private static final String CUSTOM_ACTION_BATTERY_CHANGED = "android.intent.action.CUSTOM_MEDIA_MOUNTED";

	@Override
	public void onReceive(Context context, Intent intent) {
//		LogCat.d(ACTION_BATTERY_CHANGED);
//		if (!RunInfoUitl.isRunInfoServiceRunning(context,
//				"com.duowan.runinfo.service.RunInfoService")) {
//			Intent startService = new Intent("com.duowan.runinfo.service.RunInfoService");
//			context.startService(startService);
//		}
		if (intent.getAction().equals(ACTION_BATTERY_CHANGED)) {
			if (!RunInfoUitl.isRunInfoServiceRunning(context,
					"com.duowan.runinfo.service.RunInfoService")) {
				Intent startService = new Intent("com.duowan.runinfo.service.RunInfoService");
				context.startService(startService);
			}
		}

		if (intent.getAction().equals(CUSTOM_ACTION_BATTERY_CHANGED)) {
			LogCat.d(ACTION_BATTERY_CHANGED);
			if (!RunInfoUitl.isRunInfoServiceRunning(context,
					"com.duowan.runinfo.service.RunInfoService")) {
				Intent startService = new Intent("com.duowan.runinfo.service.RunInfoService");
				context.startService(startService);
			}
		}
	}

}
