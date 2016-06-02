package com.congnhanvienthong.qlkn;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import webservice.BaseTask;
import webservice.WebProtocol;
import webservice.qlkn.GetPhieuDuyetXacMinhKhieuNaiTask;
import adapter.BaseListViewAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;

import congnhanvienthong.entity.qlkn.PhieuDuyetXacMinhKhieuNai;
import control.Util;

public class ActivityPhieuDuyetXacMinhKhieuNai extends ActivityBaseToDisplay {
	GetPhieuDuyetXacMinhKhieuNaiTask getPhieuDuyetXM;
	ArrayList<PhieuDuyetXacMinhKhieuNai> alstPhieuDuyetXM = new ArrayList<PhieuDuyetXacMinhKhieuNai>();
	ListView listView;
	TextView thongbaoKetQua;
	private DisplayMetrics metrics;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		setBodyLayout(R.layout.activity_phieuduyet_xacminh_khieunai);

		context = ActivityPhieuDuyetXacMinhKhieuNai.this;
		setHeader("Duyệt phiếu xác minh");
		listView = (ListView) body.findViewById(R.id.phieuduyet_xacminh_kn_listview);
		thongbaoKetQua = (TextView) body.findViewById(R.id.thongbao_phieuduyet_xm_kn);
		Task task = new Task();
		getPhieuDuyetXM = new GetPhieuDuyetXacMinhKhieuNaiTask();
		getPhieuDuyetXM.addParam("userName", Util.userName.trim());
		task.execute(getPhieuDuyetXM);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onsucces(WebProtocol ws) {
		// TODO Auto-generated method stub
		super.onsucces(ws);
		if (ws.equals(getPhieuDuyetXM)) {
			SoapObject lstPhieuDuyetXM = (SoapObject) getPhieuDuyetXM.result;
			int length = lstPhieuDuyetXM.getPropertyCount();
			// khi load mới thì remove toàn bộ dữ liệu cũ đi
			while (alstPhieuDuyetXM.size() > 0) {
				alstPhieuDuyetXM.remove(0);
			}
			if (length == 0) {
				thongbaoKetQua.setText("Hiện tại không có yêu cầu xác minh nào cần duyệt lãnh đạo.");
			} else {
				thongbaoKetQua.setText("Tổng số có " + String.valueOf(length) + " số phiếu cần duyệt.");
			}

			for (int i = 0; i < length; i++) {

				SoapObject lstPhieuDuyetXMObj = (SoapObject) lstPhieuDuyetXM.getProperty(i);
				PhieuDuyetXacMinhKhieuNai temp = new PhieuDuyetXacMinhKhieuNai();
				Util.GetObjectFromSoapObject(temp, lstPhieuDuyetXMObj);
				alstPhieuDuyetXM.add(temp);

			}
			listView.setAdapter(
					new BaseListViewAdapter<PhieuDuyetXacMinhKhieuNai>(context, alstPhieuDuyetXM, metrics, false));
			listView.setFadingEdgeLength(0);
			listView.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> arg0, View view1, int arg2, long arg3) {

					Intent intent = new Intent(ActivityPhieuDuyetXacMinhKhieuNai.this,
							ActivityDuyetPhieuXacMinhKhieuNai.class);
					intent.putExtra("sophieu", alstPhieuDuyetXM.get(arg2).getSoPhieu());
					startActivity(intent);

				}
			});

		}
	}

}
