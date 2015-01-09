package com.duowan.runinfo.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.duowan.runinfo.service.RunInfoUitl;
import com.duowan.util.LogCat;

public class MediaBroadcastReceiver extends BroadcastReceiver {

	private static final String MEDIA_MOUNTED = "android.intent.action.MEDIA_MOUNTED";
	private static final String MEDIA_EJECT = "android.intent.action.MEDIA_EJECT";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(MEDIA_MOUNTED)
				|| intent.getAction().equals(MEDIA_EJECT)) {
			LogCat.d(MEDIA_MOUNTED);
			if (!RunInfoUitl.isRunInfoServiceRunning(context,
					"com.duowan.runinfo.service.RunInfoService")) {
				Intent startService = new Intent(context,
						com.duowan.runinfo.service.RunInfoService.class);
				context.startService(startService);
			}
		}
	}

}
