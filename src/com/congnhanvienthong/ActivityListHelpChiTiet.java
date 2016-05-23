package com.congnhanvienthong;

import java.util.ArrayList;
import java.util.Arrays;

import adapter.BaseHelpAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ActivityListHelpChiTiet extends ActivityBaseToDisplay {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setBodyLayout(R.layout.list);
		setHeader("Các chức năng của phần mềm");
		this.context = ActivityListHelpChiTiet.this;
//		isMain = false;
		ListView listView = (ListView) findViewById(R.id.lstView);
		final String[] values = getResources().getStringArray(R.array.Question);

		ArrayList<String> lstQues = new ArrayList<String>();
		lstQues.addAll(Arrays.asList(values));
		BaseHelpAdapter temp = new BaseHelpAdapter(lstQues, this);
		listView.setAdapter(temp);
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ActivityListHelpChiTiet.this,
						ActivityHelp.class);
				i.putExtra("id", arg2 + 3);
				startActivity(i);
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);
				finish();

			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
		Intent iten = new Intent(ActivityListHelpChiTiet.this,
				ActivityHelpMenu.class);
		startActivity(iten);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		finish();
	}

}
