package com.congnhanvienthong;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import api.GetDonViQuanLyApi;
import webservice.WebProtocol;

public class ActivitySplash extends ActivityBaseToDisplay {
	GetDonViQuanLyApi getLoaiNhaTramApi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		context = ActivitySplash.this;
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent i = new Intent(ActivitySplash.this, ActivityLoginTD.class);
				startActivity(i);
				finish();
			}
		}, 2000);
		// getLoaiNhaTramApi = new GetDonViQuanLyApi();
		// Task task = new Task();
		// task.execute(getLoaiNhaTramApi);

	}

	@Override
	public void onsucces(WebProtocol task) {
		// TODO Auto-generated method stub
		super.onsucces(task);
		System.out.println(getLoaiNhaTramApi.result);
		Intent i = new Intent(ActivitySplash.this, ActivityLoginTD.class);
		startActivity(i);
		finish();

	}
}
