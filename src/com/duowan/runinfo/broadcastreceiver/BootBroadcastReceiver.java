package com.duowan.runinfo.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.duowan.runinfo.service.RunInfoUitl;
import com.duowan.util.LogCat;
import com.duowan.util.ToastShowUtil;

public class BootBroadcastReceiver extends BroadcastReceiver {

	private static final String TAG = BootBroadcastReceiver.class
			.getSimpleName();

	private static final String ACTION_BOOT = "android.intent.action.BOOT_COMPLETED";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(ACTION_BOOT)) {
			LogCat.d("android.intent.action.BOOT_COMPLETED");
			ToastShowUtil.showMsgLong(context,
					"android.intent.action.BOOT_COMPLETED");
			if (!RunInfoUitl.isRunInfoServiceRunning(context,
					"com.duowan.runinfo.service.RunInfoService")) {
				Intent startService = new Intent(context,
						com.duowan.runinfo.service.RunInfoService.class);
				context.startService(startService);
			}
			// Intent startApp = new Intent(context,
			// com.duowan.runinfo.service.ui.MainActivity.class);
			// context.startActivity(startApp);
		}
	}
}
