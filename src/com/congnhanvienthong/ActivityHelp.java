package com.congnhanvienthong;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;

public class ActivityHelp extends ActivityBaseToDisplay {
	Button next, back;
	int i;
	WebView web;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = ActivityHelp.this;
		setBodyLayout(R.layout.activity_help);
		this.context = ActivityHelp.this;
		setHeader("Trợ giúp");
		final String[] values = getResources().getStringArray(R.array.help);
		web = (WebView) body.findViewById(R.id.webview);
		web.getSettings().setSaveFormData(false);
		web.getSettings().setBuiltInZoomControls(false);
		web.getSettings().setSupportZoom(true);
		i = getIntent().getIntExtra("id", 0);
		web.loadUrl("file:///android_asset/" + i + ".html");
		setHeader(values[i]);
		web.getSettings().setJavaScriptEnabled(true);
		next = (Button) body.findViewById(R.id.bttNext);
		back = (Button) body.findViewById(R.id.bttBack);
		next.setVisibility(View.GONE);
		back.setVisibility(View.GONE);
		web.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				next.setVisibility(View.VISIBLE);
				back.setVisibility(View.VISIBLE);
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					public void run() {
						// Do something after 5s = 5000ms
						next.setVisibility(View.GONE);
						back.setVisibility(View.GONE);

					}
				}, 2000);
				return false;
			}
		});

		next.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					if (i > -1 && i < 9) {
						i = i + 1;
						Animation animation = AnimationUtils.loadAnimation(
								getApplicationContext(), R.anim.slide_in_right);
						// web.setAnimation(animation);
						
						String title = values[i];
						setHeader(title);
						web.startAnimation(animation);
						web.loadUrl("file:///android_asset/" + i + ".html");

					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				

			}
		});
		back.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				try {
					if (i > 0 && i < 10) {
						i = i - 1;
						Animation animation = AnimationUtils.loadAnimation(
								getApplicationContext(), R.anim.slide_out_left);
						// web.setAnimation(animation);
						
						String title = values[i];
						setHeader(title);
						web.startAnimation(animation);
						web.loadUrl("file:///android_asset/" + i + ".html");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();

		if (i == 0 || i == 1 || i == 2) {
			Intent iten = new Intent(ActivityHelp.this, ActivityHelpMenu.class);
			startActivity(iten);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			finish();
		}
		if (i > 2) {

			Intent iten = new Intent(ActivityHelp.this,
					ActivityListHelpChiTiet.class);
			startActivity(iten);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			finish();
		}
	}
}
