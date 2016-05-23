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

public class ActivityHelpMenu extends ActivityBaseToDisplay {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = ActivityHelpMenu.this;
		setHeader("Trợ giúp");
		setBodyLayout(R.layout.activity_help_main);

		ListView listView = (ListView) body.findViewById(R.id.lstMain);
		final String[] lstString = { "Về phần mềm", "Giới thiệu chung",
				"Yêu cầu cài đặt", "Các chức năng của phần mềm" };
		ArrayList<String> lstQues = new ArrayList<String>();
//		icon_activity = R.drawable.help;
		lstQues.addAll(Arrays.asList(lstString));
		BaseHelpAdapter temp = new BaseHelpAdapter(lstQues, this);
		listView.setAdapter(temp);
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if (arg2 == 0) {
					Intent i = new Intent(ActivityHelpMenu.this, ActivityHelp.class);
					i.putExtra("id",0);
					startActivity(i);
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
					finish();
					
				}
				if (arg2 == 1 || arg2 == 2) {
					Intent i = new Intent(ActivityHelpMenu.this, ActivityHelp.class);
					i.putExtra("id", arg2);
					startActivity(i);
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
					finish();
				}
				if (arg2 == 3) {
					Intent i = new Intent(ActivityHelpMenu.this, ActivityListHelpChiTiet.class);
					startActivity(i);
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
					finish();
				}

			}
		});

	}
	@Override
	public void onBackPressed() {
		
	}

}
