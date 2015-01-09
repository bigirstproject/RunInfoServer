package com.duowan.runinfo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

public abstract class BaseService extends Service {

	protected UiHandler mUiHandler;

	public void onCreate() {
		super.onCreate();
		mUiHandler = new UiHandler(getMainLooper());
	};

	protected void handleUiMessage(Message msg) {
	}

	protected void sendUiMessage(Message msg) {
		mUiHandler.sendMessage(msg);
	}

	protected void sendEmptyUiMessage(int what) {
		mUiHandler.sendEmptyMessage(what);
	}

	class UiHandler extends Handler {
		public UiHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			handleUiMessage(msg);
		}

	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
