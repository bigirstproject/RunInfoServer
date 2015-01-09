package com.duowan.runinfo.service.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.duowan.runinfo.service.R;
import com.duowan.runinfo.service.RunInfoService;
import com.duowan.runinfo.service.RunInfoUitl;

public class MainActivity extends Activity {
	private Button mStartService;
	private Button mStopService;
	private Button mRegisterService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mStartService = (Button) findViewById(R.id.startService);
		mStopService = (Button) findViewById(R.id.stopService);
		mRegisterService = (Button) findViewById(R.id.registerService);
		mStartService.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!RunInfoUitl.isRunInfoServiceRunning(MainActivity.this,
						"com.duowan.runinfo.service.RunInfoService")) {
					Intent intent = new Intent(MainActivity.this,
							RunInfoService.class);
					startService(intent);
				}

			}
		});

		mStopService.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (RunInfoUitl.isRunInfoServiceRunning(MainActivity.this,
						"com.duowan.runinfo.service.RunInfoService")) {
					Intent intent = new Intent(MainActivity.this,
							RunInfoService.class);
					stopService(intent);
				}

			}
		});
		mRegisterService.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,
						BatterActivity.class));

			}
		});
	}

}
