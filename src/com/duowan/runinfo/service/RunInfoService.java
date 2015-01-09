package com.duowan.runinfo.service;

import android.app.ActivityManager.RunningTaskInfo;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.duowan.util.LogCat;
import com.duowan.util.ToastShowUtil;

public class RunInfoService extends BaseBackgroundService {

	public static final String TAG = RunInfoService.class.getSimpleName();

	public final int INTERVAL = 2000;

	public int nums = 0;

	public final int REQUEST_CODE = 1;

	public final int RESPONSE_CODE = 2;

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("DwLog", "onCreate ");
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.d("DwLog", "onBind ");
		sendEmptyBackgroundMessage(REQUEST_CODE);
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		if (intent != null) {
			Log.d("DwLog", "onStart " + intent.toString() + "  : startId = "
					+ startId);
		}
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent != null) {
			Log.d("DwLog", "intent.toString() = " + intent.toString()
					+ "  :  flags = " + flags + "  :  startId = " + startId);
		}
		sendEmptyBackgroundMessage(REQUEST_CODE);
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		if(intent!=null){
			Log.d("DwLog", "onUnbind " + intent.toString());
		}
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		Log.d("DwLog", "onDestroy ");
		super.onDestroy();
	}

	@Override
	protected void handleBackgroundMessage(Message msg) {
		switch (msg.what) {
		case REQUEST_CODE:
			RunningTaskInfo activityRunning = RunInfoUitl
					.isActivityRunning(getApplicationContext());
			if (activityRunning != null) {
				Message message = new Message();
				message.what = RESPONSE_CODE;
				message.obj = activityRunning;
				sendUiMessage(message);
			}
			break;
		default:
			break;
		}
	}

	@Override
	protected void handleUiMessage(Message msg) {
		super.handleUiMessage(msg);
		switch (msg.what) {
		case RESPONSE_CODE:
			if (msg != null && msg.obj instanceof RunningTaskInfo) {
				RunningTaskInfo taskinfo = (RunningTaskInfo) msg.obj;
				LogCat.d("RunningTaskInfo  -> : "
						+ taskinfo.topActivity.getPackageName());
				nums++;
				if (nums >= 5) {
					ToastShowUtil.showMsgLong(
							getApplicationContext(),
							"RunningTaskInfo  -> : "
									+ taskinfo.topActivity.getPackageName());
					nums = 0;
				}
				mBackgroundHandler.sendEmptyMessageDelayed(REQUEST_CODE,
						INTERVAL);
			}
			break;
		default:
			break;
		}
	}

}
