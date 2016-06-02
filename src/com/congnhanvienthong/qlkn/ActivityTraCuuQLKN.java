package com.congnhanvienthong.qlkn;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import webservice.BaseTask;
import webservice.WebProtocol;
import webservice.qlkn.GetTraCuuKhieuNaiTask;
import adapter.BaseListViewAdapter;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;

import congnhanvienthong.entity.qlkn.LichSuKhieuNai;
import control.Util;

public class ActivityTraCuuQLKN extends ActivityBaseToDisplay {
	GetTraCuuKhieuNaiTask getTraCuuKhieuNai;
	Button mButtonTraCuuKN;
	EditText maTraCuu;
	TextView ketQuaTraCuu, thongbaoKetQua;
	ArrayList<LichSuKhieuNai> alstLichSuKhieuNai = new ArrayList<LichSuKhieuNai>();
	ListView listView;
	private DisplayMetrics metrics;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		setBodyLayout(R.layout.activity_tracuu_quanlykieunai);
		setFootLayout(R.layout.foot_tracuu_khieunai);
		mButtonTraCuuKN = (Button) foot.findViewById(R.id.btt_tracuu_kn);
		maTraCuu = (EditText) body.findViewById(R.id.ma_tra_cuu_qlkn);
		context = ActivityTraCuuQLKN.this;
		setHeader("Tra cứu khiếu nại");
		listView = (ListView) body.findViewById(R.id.lylichkhieunai_listview);
		listView.setFadingEdgeLength(0);
		thongbaoKetQua = (TextView) body.findViewById(R.id.thongbao_ketquatracuu);
		mButtonTraCuuKN.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Task task = new Task();
				getTraCuuKhieuNai = new GetTraCuuKhieuNaiTask();
				getTraCuuKhieuNai.addParam("socv", maTraCuu.getText().toString().trim());
				task.execute(getTraCuuKhieuNai);
			}
		});

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onsucces(WebProtocol ws) {
		// TODO Auto-generated method stub
		super.onsucces(ws);
		if (ws.equals(getTraCuuKhieuNai)) {
			SoapObject lstLichSuKhieuNai = (SoapObject) getTraCuuKhieuNai.result;
			int length = lstLichSuKhieuNai.getPropertyCount();
			// khi load mới thì remove toàn bộ dữ liệu cũ đi
			while (alstLichSuKhieuNai.size() > 0) {
				alstLichSuKhieuNai.remove(0);
			}
			if (length == 0) {
				thongbaoKetQua.setText("Không tìm thấy kết quả nào phù hợp với yêu cầu.");
			} else {
				thongbaoKetQua.setText("Tổng số có " + String.valueOf(length) + " số công văn khiếu nại phù hợp.");
			}
			// chạy vòng lặp, tạo dữ liệu và đổ vào list để sử dụng
			for (int i = 0; i < length; i++) {
				SoapObject lstLichSuKhieuNaiObj = (SoapObject) lstLichSuKhieuNai.getProperty(i);
				LichSuKhieuNai temp = new LichSuKhieuNai();
				Util.GetObjectFromSoapObject(temp, lstLichSuKhieuNaiObj);
				alstLichSuKhieuNai.add(temp);

			}

			listView.setAdapter(new BaseListViewAdapter<LichSuKhieuNai>(context, alstLichSuKhieuNai, metrics, false));
			listView.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> arg0, View view1, int arg2, long arg3) {
				}
			});
		}
	}

}
