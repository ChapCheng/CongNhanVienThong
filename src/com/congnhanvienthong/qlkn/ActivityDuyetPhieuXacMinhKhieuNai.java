package com.congnhanvienthong.qlkn;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import webservice.BaseTask;
import webservice.WebProtocol;
import webservice.qlkn.GetNghiepVuXuatXacMinhKhieuNaiWS;
import webservice.qlkn.GetTienTrinhPhieuXacMinhKhieuNaiWS;
import adapter.BaseListViewAdapter;
import adapter.BaseSpinnerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.congnhanvienthong.ActivityBaseToDisplay;
import com.congnhanvienthong.R;

import congnhanvienthong.entity.qlkn.NVKhieuNai;
import congnhanvienthong.entity.qlkn.TienTrinhPhieuXacMinhKhieuNai;
import control.Util;

public class ActivityDuyetPhieuXacMinhKhieuNai extends ActivityBaseToDisplay {
	GetTienTrinhPhieuXacMinhKhieuNaiWS getTienTrinhPhieu;
	GetNghiepVuXuatXacMinhKhieuNaiWS getNghiepVuXuat;
	ArrayList<TienTrinhPhieuXacMinhKhieuNai> alstTienTrinhPhieu = new ArrayList<TienTrinhPhieuXacMinhKhieuNai>();
	ListView listView;
	TextView thongbaoKetQua;
	String soPhieu;
	Spinner mSpinnerNghiepVuKN;
	ArrayList<NVKhieuNai> arrayList = new ArrayList<NVKhieuNai>();
	private DisplayMetrics metrics;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		context = ActivityDuyetPhieuXacMinhKhieuNai.this;
		Intent i = getIntent();
		soPhieu = i.getStringExtra("sophieu");
		setHeader("Lãnh đạo duyệt phiếu xác minh");
		Task task = new Task();
		getTienTrinhPhieu = new GetTienTrinhPhieuXacMinhKhieuNaiWS();
		getTienTrinhPhieu.addParam("p_sophieu", soPhieu);
		task.execute(getTienTrinhPhieu);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

	@Override
	public void onsucces(WebProtocol ws) {
		// TODO Auto-generated method stub
		super.onsucces(ws);
		if (ws.equals(getTienTrinhPhieu)) {
			setBodyLayout(R.layout.activity_duyet_xacminh_khieunai);
			SoapObject lstTienTrinhPhieu = (SoapObject) getTienTrinhPhieu.result;
			int length = lstTienTrinhPhieu.getPropertyCount();
			// khi load mới thì remove toàn bộ dữ liệu cũ đi
			while (alstTienTrinhPhieu.size() > 0) {
				alstTienTrinhPhieu.remove(0);
			}
			if (length == 0) {
				thongbaoKetQua.setText("Không tồn tại lịch sử phiếu xác minh này.");
			}

			for (int i = 0; i < length; i++) {
				SoapObject lstTienTrinhPhieuObj = (SoapObject) lstTienTrinhPhieu.getProperty(i);

				TienTrinhPhieuXacMinhKhieuNai temp = new TienTrinhPhieuXacMinhKhieuNai();
				Util.GetObjectFromSoapObject(temp, lstTienTrinhPhieuObj);
				alstTienTrinhPhieu.add(temp);
			}
			listView = (ListView) body.findViewById(R.id.tientrinhphieu_xacminh_kn_listview);
			listView.setFadingEdgeLength(0);

			thongbaoKetQua = (TextView) body.findViewById(R.id.thongbao_tientrinhphieu_xm_kn);
			listView.setAdapter(new BaseListViewAdapter<TienTrinhPhieuXacMinhKhieuNai>(context, alstTienTrinhPhieu,
					metrics, false));
			Task task2 = new Task();
			getNghiepVuXuat = new GetNghiepVuXuatXacMinhKhieuNaiWS();
			getNghiepVuXuat.addParam("p_sophieu", soPhieu);
			task2.execute(getNghiepVuXuat);

		}

		if (ws.equals(getNghiepVuXuat)) {
			mSpinnerNghiepVuKN = (Spinner) body.findViewById(R.id.nghiepvuduyet_xacminh_kn_spinner);
			SoapObject lstNghiepVuXuat = (SoapObject) getNghiepVuXuat.result;
			int length = lstNghiepVuXuat.getPropertyCount();
			for (int i = 0; i < length; i++) {
				SoapObject nghiepvu = (SoapObject) lstNghiepVuXuat.getProperty(i);
				String id = nghiepvu.getPrimitivePropertyAsString("ID");
				String TenDanhMuc = nghiepvu.getPrimitivePropertyAsString("TenDanhMuc");
				NVKhieuNai danhMucDuyetLDKhieuNai = new NVKhieuNai(id, TenDanhMuc);
				arrayList.add(danhMucDuyetLDKhieuNai);

			}

			BaseSpinnerAdapter<NVKhieuNai> temp = new BaseSpinnerAdapter<NVKhieuNai>(context,
					android.R.layout.simple_spinner_dropdown_item, arrayList);
			temp.name = "Name";
			mSpinnerNghiepVuKN.setAdapter(temp);
			mSpinnerNghiepVuKN.setOnItemSelectedListener(new OnItemSelectedListener() {

				public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long arg3) {
					// TODO Auto-generated method stub

				}

				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub

				}

			});

		}

	}

}
