package com.duowan.runinfo.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.duowan.util.LogCat;
import com.duowan.util.ToastShowUtil;

public class ShutdownBroadcastReceiver extends BroadcastReceiver {
	private static final String TAG = ShutdownBroadcastReceiver.class
			.getSimpleName();

	private static final String ACTION_SHUTDOWN = "android.intent.action.ACTION_SHUTDOWN";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(ACTION_SHUTDOWN)) {
			LogCat.d("android.intent.action.ACTION_SHUTDOWN");
			ToastShowUtil.showMsgLong(context,
					"android.intent.action.ACTION_SHUTDOWN");
		}
	}

}
