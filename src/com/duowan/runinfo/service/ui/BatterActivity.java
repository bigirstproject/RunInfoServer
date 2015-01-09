package com.duowan.runinfo.service.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.duowan.runinfo.broadcastreceiver.BatteryBroadcastReceiver;
import com.duowan.runinfo.service.R;

public class BatterActivity extends Activity {
	private static final String CUSTOM_ACTION_BATTERY_CHANGED = "android.intent.action.CUSTOM_MEDIA_MOUNTED";

	private int level;
	private int scale;
	private BroadcastReceiver mReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
				level = intent.getIntExtra("level", 0);
				scale = intent.getIntExtra("scale", 0);
				onBatteryInfoReceiver(level, scale);
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batter_layout);
		findViewById(R.id.btn_battery).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View view) {
						registerReceiver(mReceiver, new IntentFilter(
								Intent.ACTION_BATTERY_CHANGED));
					}
				});

		findViewById(R.id.start_receiver).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View view) {
//						Intent intent = new Intent(BatterActivity.this,
//								BatteryBroadcastReceiver.class);
//						intent.SET
//						intent.setAction(CUSTOM_ACTION_BATTERY_CHANGED);
//						sendBroadcast(intent);
						registerReceiver(new BatteryBroadcastReceiver(), new IntentFilter(
								Intent.ACTION_BATTERY_CHANGED));
					}
				});
	}

	public void onBatteryInfoReceiver(int level, int scale) {
		final Dialog dialog = new Dialog(BatterActivity.this);
		dialog.setTitle("电池电量");
		dialog.setContentView(R.layout.dialog_battery);

		// 创建背景模糊的window，并将对话框放在前景
		Window window = dialog.getWindow();
		window.setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
				WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

		TextView mTextView = (TextView) dialog.findViewById(R.id.tv_battery);
		mTextView.setText("电池剩余电量为:" + level * 100 / scale + "%");

		dialog.findViewById(R.id.btn_battery).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						unregisterReceiver(mReceiver);
						dialog.dismiss();
					}
				});

		dialog.show();
	}
}
